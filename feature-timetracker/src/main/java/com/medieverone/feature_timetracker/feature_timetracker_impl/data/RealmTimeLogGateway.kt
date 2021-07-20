package com.medieverone.feature_timetracker.feature_timetracker_impl.data

import com.medieverone.core_db.core_db_api.DbClient
import com.medieverone.feature_timetracker.feature_timetracker_impl.data.entities.RealmTimeLogEntity
import com.medieverone.feature_timetracker.feature_timetracker_impl.data.entities.RealmUserActivityEntity
import com.medieverone.feature_timetracker.feature_timetracker_impl.domain.entities.TimeLogEntity
import com.medieverone.feature_timetracker.feature_timetracker_impl.gateways.LocalTimeLogGateway
import io.realm.Realm

class RealmTimeLogGateway(
    private val dbClient: DbClient
) : LocalTimeLogGateway {

    override suspend fun addTimeLog(timeLogEntity: TimeLogEntity) {
        dbClient.getRealm().executeTransaction { realm ->
            val currentIdNum = realm.where(RealmTimeLogEntity::class.java).max("id")
            val nextId: Int = (currentIdNum?.toInt()?.plus(1)) ?: 1
            val realmEntity = RealmTimeLogEntity.fromDomain(timeLogEntity)
            realmEntity.id = nextId
            if (realmEntity.activityId != null) {
                addTimeToActivity(realm, realmEntity.activityId!!, realmEntity.time)
            }
            realm.insertOrUpdate(realmEntity)
        }
    }

    override suspend fun getAllTimeLogItems(): List<TimeLogEntity> {
        val result = dbClient.getRealm().where(RealmTimeLogEntity::class.java)
            .findAll() ?: throw IllegalStateException("There's no TimeLogEntity in Realm")
        return result.map { it.toDomain() }
    }

    private fun addTimeToActivity(realm: Realm, activityId: Int, timeInMillis: Long) {
            val activity = realm.where(RealmUserActivityEntity::class.java)
                .equalTo("id", activityId)
                .findFirst()
            if (activity != null) {
                activity.summaryTimeInMillis += timeInMillis
            }
    }
}