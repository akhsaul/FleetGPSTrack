package io.github.akhsaul.fleetgpstrack.controller

import io.github.akhsaul.fleetgpstrack.util.RestResponseBuilder
import io.github.akhsaul.fleetgpstrack.model.RestResponse
import io.github.akhsaul.fleetgpstrack.service.GpsLogService
import io.github.akhsaul.fleetgpstrack.service.VehicleService
import org.slf4j.LoggerFactory
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.Instant

@RestController
@RequestMapping("/api/vehicles")
@Validated
class VehicleController(
    private val vehicleService: VehicleService,
    private val gpsLogService: GpsLogService
) :
    RestResponseBuilder() {
    @GetMapping("/{id}/last-location")
    fun getLastLocation(
        @PathVariable
        id: Long,
    ): ResponseEntity<RestResponse> {
        log.debug("id: {}", id)

        val vehicle = vehicleService.findById(id)
        if (vehicle == null) {
            return notFound("Vehicle not found")
        }

        val gpsLog = gpsLogService.getLog(vehicle.id!!)
        var data: List<Any?>? = null
        if (gpsLog != null) {
            data = listOf(gpsLog)
        }

        return ok("successfully get last location", data)
    }

    @GetMapping("/{id}/history")
    fun getGpsHistory(
        @PathVariable
        id: Long,
        @RequestParam("from")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        from: Instant?,
        @RequestParam("to")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        to: Instant?,
    ): ResponseEntity<RestResponse> {
        log.debug("id: {} from: {} to: {}", id, from, to)

        val listGpsLog = when {
            from != null && to != null -> {
                gpsLogService.getHistory(id, from, to)
            }

            from != null -> {
                gpsLogService.getHistory(id, from, Instant.now())
            }

            to != null -> {
                gpsLogService.getHistory(id, Instant.EPOCH, to)
            }

            else -> {
                // get all history
                gpsLogService.getAllHistory(id)
            }
        }
        return ok("successfully get history", listGpsLog)
    }

    companion object {
        private val log = LoggerFactory.getLogger(VehicleController::class.java)
    }
}