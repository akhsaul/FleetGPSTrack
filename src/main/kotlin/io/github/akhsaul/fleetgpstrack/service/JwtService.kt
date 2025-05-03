package io.github.akhsaul.fleetgpstrack.service

import io.github.akhsaul.fleetgpstrack.config.JwtConfig
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*
import kotlin.time.DurationUnit
import kotlin.time.toDuration

@Service
class JwtService(private val jwtConfig: JwtConfig) {
    private val secretKey = Keys.hmacShaKeyFor(jwtConfig.secret.encodeToByteArray())

    fun generateToken(username: String): String {
        return Jwts.builder()
            .subject(username)
            .issuedAt(Date())
            .expiration(
                Date(
                    System.currentTimeMillis() + jwtConfig.expirationInDay
                        .toDuration(DurationUnit.DAYS).inWholeMilliseconds
                )
            )
            .signWith(secretKey)
            .compact()
    }

    fun validateToken(token: String, username: String): Boolean {
        return runCatching {
            val payload = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token).payload
            val isExpired = payload.expiration.before(Date())

            payload.subject == username && !isExpired
        }.getOrElse {
            log.error("JWT Fail", it)
            false
        }
    }

    fun getUsernameFromToken(token: String): String? {
        return runCatching {
            val payload = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token).payload

            payload.subject
        }.getOrElse {
            log.error("JWT Fail", it)
            null
        }
    }

    companion object {
        private val log = LoggerFactory.getLogger(JwtService::class.java)
    }
}