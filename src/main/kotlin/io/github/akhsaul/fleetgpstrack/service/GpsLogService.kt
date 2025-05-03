package io.github.akhsaul.fleetgpstrack.service

import io.github.akhsaul.fleetgpstrack.model.GpsLog
import io.github.akhsaul.fleetgpstrack.repository.GpsLogRepo
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.Instant
import kotlin.jvm.optionals.getOrNull


@Service
class GpsLogService(private val repo: GpsLogRepo) {

    fun saveLog(log: GpsLog) {
        repo.save(log)
    }

    fun getLog(vehicleId: Long): GpsLog? {
        return repo.findTopByVehicleIdOrderByTimestampDesc(vehicleId).getOrNull()
    }

    fun getHistory(vehicleId: Long, from: Instant, to: Instant): List<GpsLog> {
        return repo.findByVehicleIdAndTimestampBetween(vehicleId, from, to)
    }

    fun getAllHistory(vehicleId: Long): List<GpsLog> {
        return repo.findByVehicleId(vehicleId)
    }

    companion object {
        private val log = LoggerFactory.getLogger(GpsLogService::class.java)
    }
}
