package io.github.akhsaul.fleetgpstrack.repository

import io.github.akhsaul.fleetgpstrack.model.GpsLog
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GpsLogRepo : JpaRepository<GpsLog, Long>