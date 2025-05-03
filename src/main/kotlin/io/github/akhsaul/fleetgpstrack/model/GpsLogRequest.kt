package io.github.akhsaul.fleetgpstrack.model

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.DecimalMax
import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.PastOrPresent
import jakarta.validation.constraints.Positive
import java.math.BigDecimal
import java.time.Instant

data class GpsLogRequest(

    @field:NotNull()
    @field:Positive
    @JsonProperty("vehicle_id")
    val vehicleId: Long?,

    @field:NotNull()
    @field:DecimalMax("90")
    @field:DecimalMin("-90")
    val latitude: BigDecimal?,

    @field:NotNull()
    @field:DecimalMax("180")
    @field:DecimalMin("-180")
    val longitude: BigDecimal?,

    @field:NotNull()
    @field:Max(500)
    @field:Positive
    val speed: Int?,

    @field:NotNull()
    @field:PastOrPresent()
    val timestamp: Instant?
)

