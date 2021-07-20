package com.medieverone.feature_timetracker.feature_timetracker_impl.gateways

import com.medieverone.feature_timetracker.feature_timetracker_impl.domain.entities.TimeLogEntity

interface LocalTimeLogGateway {

    suspend fun addTimeLog(timeLogEntity: TimeLogEntity)

    suspend fun getAllTimeLogItems(): List<TimeLogEntity>
}