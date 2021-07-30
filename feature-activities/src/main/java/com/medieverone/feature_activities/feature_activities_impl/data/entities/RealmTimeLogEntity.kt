package com.medieverone.feature_activities.feature_activities_impl.data.entities

import com.medieverone.feature_activities.feature_activities_impl.domain.entities.TimeLogEntity
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class RealmTimeLogEntity : RealmObject() {

    @PrimaryKey
    var id: Int = 0

    var comment: String = ""
    var timeInMillis: Long = 0L

    fun toDomain(): TimeLogEntity {
        return TimeLogEntity(
            id = this.id,
            comment = this.comment,
            timeInMillis = this.timeInMillis
        )
    }

    companion object {
        fun fromDomain(entity: TimeLogEntity): RealmTimeLogEntity {
            return RealmTimeLogEntity().apply {
                this.id = entity.id
                this.comment = entity.comment
                this.timeInMillis = entity.timeInMillis
            }
        }
    }
}