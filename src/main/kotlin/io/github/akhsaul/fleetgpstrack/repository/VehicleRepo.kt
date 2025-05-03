package io.github.akhsaul.fleetgpstrack.repository

import io.github.akhsaul.fleetgpstrack.model.Vehicle
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface VehicleRepo : JpaRepository<Vehicle, Long>