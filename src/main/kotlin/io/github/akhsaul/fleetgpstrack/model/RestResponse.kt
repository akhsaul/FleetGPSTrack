package io.github.akhsaul.fleetgpstrack.model

data class RestResponse(
    val status: String,
    val message: String,
    val data: List<Any?>? = null
)
