package io.github.akhsaul.fleetgpstrack.service

import org.springframework.stereotype.Service

@Service
class JwtAuthService {
    private val user = "Akhsaul" to "AkhsaulPassword"

    fun authenticate(username: String, password: String): Boolean {
        return user.first == username && user.second == password
    }
}
