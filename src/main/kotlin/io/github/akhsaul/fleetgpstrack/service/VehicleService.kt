package io.github.akhsaul.fleetgpstrack.service

import io.github.akhsaul.fleetgpstrack.model.Vehicle
import io.github.akhsaul.fleetgpstrack.repository.VehicleRepo
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class VehicleService(private val repo: VehicleRepo) {
    fun findById(id: Long): Vehicle? = repo.findById(id).getOrNull()
}