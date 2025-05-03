package io.github.akhsaul.fleetgpstrack.controller

import io.github.akhsaul.fleetgpstrack.model.LoginRequest
import io.github.akhsaul.fleetgpstrack.model.LoginResponse
import io.github.akhsaul.fleetgpstrack.model.RestResponse
import io.github.akhsaul.fleetgpstrack.service.JwtAuthService
import io.github.akhsaul.fleetgpstrack.service.JwtService
import io.github.akhsaul.fleetgpstrack.util.RestResponseBuilder
import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/auth")
class JwtAuthController(
    private val jwtAuthService: JwtAuthService,
    private val jwtService: JwtService
) : RestResponseBuilder() {

    @PostMapping("/login")
    fun login(
        @Valid
        loginRequest: LoginRequest
    ): ResponseEntity<LoginResponse> {
        log.debug("Login request: {}", loginRequest)

        return if (jwtAuthService.authenticate(loginRequest.username!!, loginRequest.password!!)) {
            val token = jwtService.generateToken(loginRequest.username)

            ResponseEntity.ok().body(
                LoginResponse(
                    status = "success",
                    message = "successfully login",
                    token = token
                )
            )
        } else {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                LoginResponse(
                    status = "error",
                    message = "invalid username or password"
                )
            )
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleArgumentNotValid(ex: MethodArgumentNotValidException): ResponseEntity<RestResponse> {
        val message = ex.fieldErrors.joinToString(separator = " and "){
            "${it.field} ${it.defaultMessage}"
        }
        return badRequest(message)
    }

    companion object {
        private val log = LoggerFactory.getLogger(JwtAuthController::class.java)
    }
}