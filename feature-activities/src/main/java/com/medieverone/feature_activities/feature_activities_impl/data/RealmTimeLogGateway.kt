package com.medieverone.feature_activities.feature_activities_impl.data

import com.medieverone.core_db.core_db_api.DbClient
import com.medieverone.core_db.core_db_impl.utils.RealmInt
import com.medieverone.feature_activities.feature_activities_impl.data.entities.RealmActivityEntity
import com.medieverone.feature_activities.feature_activities_impl.data.entities.RealmTimeLogEntity
import com.medieverone.feature_activities.feature_activities_impl.domain.entities.TimeLogEntity
import com.medieverone.feature_activities.feature_activities_impl.gateway.LocalTimeLogGateway
import java.lang.IllegalArgumentException

class RealmTimeLogGateway(
    val dbClient: DbClient
) : LocalTimeLogGateway {

    private val realm
        get() = dbClient.getRealm()

    override suspend fun getTimeLog(): List<TimeLogEntity> {
        val result = realm.where(RealmTimeLogEntity::class.java).findAll()
        return realm.copyFromRealm(result).map {
            it.toDomain()
        }
    }

    override suspend fun saveTimeLogItem(activityId: Int, timeLogEntity: TimeLogEntity) {
        realm.executeTransaction { realm ->
            val activity = realm.where(RealmActivityEntity::class.java)
                .findFirst()
                ?: throw IllegalArgumentException("Realm has not activity: $activityId")
            val timeLogIds = arrayListOf<RealmInt>()

            timeLogIds.add(RealmInt().apply { value = timeLogEntity.id })
            with(activity) {
                logsIds?.let { timeLogIds.addAll(it) }
                logsIds = timeLogIds
                timeInMillis += timeLogEntity.timeInMillis
            }

            val realmTimeLogEntity = RealmTimeLogEntity.fromDomain(timeLogEntity)
                .apply {
                id = realm.where(RealmTimeLogEntity::class.java)
                    .max("id")
                    ?.toInt()
                    ?.plus(1)
                    ?: 1
            }

            realm.insertOrUpdate(realmTimeLogEntity)
        }
    }

    override suspend fun deleteTimeLogItem(activityId: Int, timeLogId: Int) {
        realm.executeTransaction { realm ->
            val activity = realm.where(RealmActivityEntity::class.java)
                .equalTo("id", activityId)
                .findFirst()
                ?: throw IllegalArgumentException("Realm has not activity: $activityId")
            val timeLogItem = realm.where(RealmTimeLogEntity::class.java)
                .equalTo("id", timeLogId)
                .findFirst()
                ?: throw IllegalArgumentException("Realm has not timeLogitem: $timeLogId")
            val timeLogIds = arrayListOf<RealmInt>()

            activity.logsIds?.let { timeLogIds.addAll(it) }
            timeLogIds.remove(RealmInt().apply { value = timeLogId })

            with(activity) {
                logsIds = timeLogIds
                timeInMillis -= timeLogItem.timeInMillis
            }

            realm.where(RealmTimeLogEntity::class.java)
                .equalTo("id", timeLogId)
                .findFirst()
                ?.deleteFromRealm()
        }
    }
}