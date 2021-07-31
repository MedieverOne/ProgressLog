package com.medieverone.feature_activities.feature_activities_impl.data.entities

import com.medieverone.core_db.core_db_impl.utils.RealmInt
import com.medieverone.feature_activities.feature_activities_impl.domain.entities.ActivityEntity
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class RealmActivityEntity : RealmObject() {

    @PrimaryKey
    var id: Int = 0

    var name: String = ""
    var timeInMillis: Long = 0
    var logsIds: RealmList<RealmInt>? = null

    fun toDomain(): ActivityEntity {
        return ActivityEntity(
            id = this.id,
            name = this.name,
            timeInMillis = this.timeInMillis,
            logsIds = this.logsIds?.map { it.value }
        )
    }

    companion object {
        fun fromDomain(entity: ActivityEntity): RealmActivityEntity {
            return RealmActivityEntity().apply {
                val realmIdsList = RealmList<RealmInt>()
                entity.logsIds?.forEach {
                    val intObject = realm.createObject(RealmInt::class.java)
                    intObject.value = it
                    realmIdsList.add(intObject)
                }
                this.id = entity.id
                this.name = entity.name
                this.timeInMillis = entity.timeInMillis
                this.logsIds = realmIdsList
            }

        }
    }



}