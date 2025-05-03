package io.github.akhsaul.fleetgpstrack.service

import io.github.akhsaul.fleetgpstrack.config.GpsLogConfig
import io.github.akhsaul.fleetgpstrack.model.GpsLog
import io.github.akhsaul.fleetgpstrack.repository.GpsLogRepo
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.temporal.ChronoUnit
import kotlin.jvm.optionals.getOrNull

@Service
class GpsLogService(private val repo: GpsLogRepo, private val gpsLogConfig: GpsLogConfig) {
    @Scheduled(cron = "0 0 0 * * *")
    fun cleanupOldLog() {
        val retentionDate = Instant.now().minus(gpsLogConfig.retentionDays, ChronoUnit.DAYS)
        val total = repo.deleteByTimestampBefore(retentionDate)
        log.info("Total gps log deleted: {}", total)
    }

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
