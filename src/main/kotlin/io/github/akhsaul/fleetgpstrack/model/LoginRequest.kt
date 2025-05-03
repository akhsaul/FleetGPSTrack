package io.github.akhsaul.fleetgpstrack.model

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class LoginRequest(

    @field:NotNull()
    @field:NotBlank()
    val username: String?,

    @field:NotNull()
    @field:NotBlank()
    val password: String?
)