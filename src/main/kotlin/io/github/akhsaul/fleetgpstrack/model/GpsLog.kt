package io.github.akhsaul.fleetgpstrack.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal
import java.time.Instant


@Entity
@Table(name = "gps_log")
data class GpsLog(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "vehicle_id",nullable = false)
    val vehicleId: Long,

    @Column(nullable = false)
    val latitude: BigDecimal,

    @Column(nullable = false)
    val longitude: BigDecimal,

    @Column(nullable = false)
    val speed: Int,

    @Column(nullable = false)
    val timestamp: Instant,

    @Column(nullable = false)
    val speedViolation: Boolean = false
)


