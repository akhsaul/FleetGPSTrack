package io.github.akhsaul.fleetgpstrack

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class FleetGpsTrackApplication

fun main(args: Array<String>) {
    runApplication<FleetGpsTrackApplication>(*args)
}
