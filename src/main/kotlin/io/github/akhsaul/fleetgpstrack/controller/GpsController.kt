package io.github.akhsaul.fleetgpstrack.controller

import io.github.akhsaul.fleetgpstrack.utils.RestResponseBuilder
import io.github.akhsaul.fleetgpstrack.model.GpsLog
import io.github.akhsaul.fleetgpstrack.model.GpsLogRequest
import io.github.akhsaul.fleetgpstrack.model.RestResponse
import io.github.akhsaul.fleetgpstrack.service.GpsLogService
import io.github.akhsaul.fleetgpstrack.service.VehicleService
import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/gps")
@Validated
class GpsController(private val gpsLogService: GpsLogService, private val vehicleService: VehicleService) :
    RestResponseBuilder() {
    @PostMapping()
    fun saveGpsLog(
        @Valid
        @RequestBody
        gpsLogBody: GpsLogRequest
    ): ResponseEntity<RestResponse> {
        log.debug("Received GPS log: {}", gpsLogBody)

        val vehicle = vehicleService.findById(gpsLogBody.vehicleId!!)
        if (vehicle == null) {
            return notFound("Vehicle not found")
        }

        val gpsLog = GpsLog(
            vehicleId = vehicle.id!!,
            latitude = gpsLogBody.latitude!!,
            longitude = gpsLogBody.longitude!!,
            speed = gpsLogBody.speed!!,
            timestamp = gpsLogBody.timestamp!!,
            speedViolation = gpsLogBody.speed > 100
        )

        gpsLogService.saveLog(gpsLog)
        return created("GPS log received successfully")
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleArgumentNotValid(ex: MethodArgumentNotValidException): ResponseEntity<RestResponse> {
        val message = ex.fieldErrors.joinToString(separator = " and "){
            "${it.field} ${it.defaultMessage}"
        }
        return badRequest(message)
    }

    companion object {
        private val log = LoggerFactory.getLogger(GpsController::class.java)
    }
}
