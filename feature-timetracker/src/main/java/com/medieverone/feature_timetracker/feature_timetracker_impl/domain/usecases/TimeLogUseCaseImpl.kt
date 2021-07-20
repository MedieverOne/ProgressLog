package com.medieverone.feature_timetracker.feature_timetracker_impl.domain.usecases

import com.medieverone.feature_timetracker.feature_timetracker_impl.domain.entities.TimeLogEntity
import com.medieverone.feature_timetracker.feature_timetracker_impl.gateways.LocalTimeLogGateway

class TimeLogUseCaseImpl(
    private val local: LocalTimeLogGateway
): TimeLogUseCase {

    override suspend fun saveTimeLog(timeLogItem: TimeLogEntity) =
        local.addTimeLog(timeLogItem)

    override suspend fun getAllTimeLogItems() =
        local.getAllTimeLogItems()
}