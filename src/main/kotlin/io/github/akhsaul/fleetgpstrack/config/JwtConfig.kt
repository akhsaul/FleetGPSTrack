package io.github.akhsaul.fleetgpstrack.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class JwtConfig {
    @Value("\${jwt.secret}")
    lateinit var secret: String

    @Value("\${jwt.expirationInDay}")
    var expirationInDay: Long = 1
}