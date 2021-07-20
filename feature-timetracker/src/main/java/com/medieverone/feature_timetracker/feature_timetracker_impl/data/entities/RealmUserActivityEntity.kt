package com.medieverone.feature_timetracker.feature_timetracker_impl.data.entities

import com.medieverone.feature_timetracker.feature_timetracker_impl.domain.entities.UserActivityEntity
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class RealmUserActivityEntity : RealmObject() {

    @PrimaryKey
    var id: Int? = null
    var tag: String = ""
    var summaryTimeInMillis: Long = 0L


    fun toDomain(): UserActivityEntity {
        return UserActivityEntity(
            id = this.id ?: 1,
            tag = this.tag,
            summaryTimeInMillis = this.summaryTimeInMillis
        )
    }

    companion object {
        fun fromDomain(userActivityEntity: UserActivityEntity): RealmUserActivityEntity {
            return RealmUserActivityEntity().apply {
                id = userActivityEntity.id
                tag = userActivityEntity.tag
                summaryTimeInMillis = userActivityEntity.summaryTimeInMillis
            }
        }
    }
}