package io.github.akhsaul.fleetgpstrack.utils

import io.github.akhsaul.fleetgpstrack.model.RestResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

abstract class RestResponseBuilder {
    protected fun badRequest(message: String): ResponseEntity<RestResponse> {
        return ResponseEntity.badRequest().body(
            RestResponse(
                status = "error",
                message = message
            )
        )
    }

    protected fun created(message: String): ResponseEntity<RestResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(
            RestResponse(
                status = "success",
                message = message
            )
        )
    }

    protected fun notFound(message: String): ResponseEntity<RestResponse> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            RestResponse(
                status = "error",
                message = message
            )
        )
    }

    protected fun ok(message: String, data: List<Any?>? = null): ResponseEntity<RestResponse> {
        return ResponseEntity.ok().body(
            RestResponse(
                status = "success",
                message = message,
                data
            )
        )
    }
}