package io.github.akhsaul.fleetgpstrack.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class GpsLogConfig {
    @Value("\${gps.log.retention.days}")
    val retentionDays: Long = 30
}