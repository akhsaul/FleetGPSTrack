package io.github.akhsaul.fleetgpstrack.repository

import io.github.akhsaul.fleetgpstrack.model.GpsLog
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.Instant
import java.util.*

@Repository
interface GpsLogRepo : JpaRepository<GpsLog, Long> {
    fun findTopByVehicleIdOrderByTimestampDesc(vehicleId: Long): Optional<GpsLog>
    fun findByVehicleIdAndTimestampBetween(vehicleId: Long, from: Instant, to: Instant): List<GpsLog>
    fun findByVehicleId(vehicleId: Long): List<GpsLog>
    @Modifying
    @Query("DELETE FROM GpsLog g WHERE g.timestamp < :offset")
    fun deleteByTimestampBefore(offset: Instant): Int
}