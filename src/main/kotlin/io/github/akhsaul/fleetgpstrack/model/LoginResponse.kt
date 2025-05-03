package io.github.akhsaul.fleetgpstrack.model

data class LoginResponse(
    val status: String,
    val message: String,
    val token: String? = null
)