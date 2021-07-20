package com.medieverone.feature_timetracker.feature_timetracker_impl.domain.usecases

import com.medieverone.feature_timetracker.feature_timetracker_impl.domain.entities.TimeLogEntity

interface TimeLogUseCase {

    suspend fun saveTimeLog(timeLogItem: TimeLogEntity)

    suspend fun getAllTimeLogItems(): List<TimeLogEntity>
}