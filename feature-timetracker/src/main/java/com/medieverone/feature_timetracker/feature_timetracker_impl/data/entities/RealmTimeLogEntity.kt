package com.medieverone.feature_timetracker.feature_timetracker_impl.data.entities

import com.medieverone.feature_timetracker.feature_timetracker_impl.domain.entities.TimeLogEntity
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class RealmTimeLogEntity: RealmObject() {

    @PrimaryKey
    var id: Int? = null

    var time: Long = 0L
    var date: Date = Date()
    var comment: String = ""

    fun toDomain(): TimeLogEntity {
        return TimeLogEntity(
            time = this.time,
            date = this.date,
            comment = this.comment
        )
    }

    companion object {
        fun fromDomain(timeLogEntity: TimeLogEntity): RealmTimeLogEntity {
            return RealmTimeLogEntity().apply {
                time = timeLogEntity.time
                date = timeLogEntity.date
                comment = timeLogEntity.comment
            }
        }
    }
}