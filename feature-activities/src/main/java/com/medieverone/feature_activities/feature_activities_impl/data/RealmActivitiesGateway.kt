package com.medieverone.feature_activities.feature_activities_impl.data

import com.medieverone.core_db.core_db_api.DbClient
import com.medieverone.feature_activities.feature_activities_impl.data.entities.RealmActivityEntity
import com.medieverone.feature_activities.feature_activities_impl.data.entities.RealmTimeLogEntity
import com.medieverone.feature_activities.feature_activities_impl.domain.entities.ActivityEntity
import com.medieverone.feature_activities.feature_activities_impl.domain.entities.TimeLogEntity
import com.medieverone.feature_activities.feature_activities_impl.gateway.LocalActivitiesGateway
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

class RealmActivitiesGateway(
    val dbClient: DbClient
) : LocalActivitiesGateway {

    private val realm
        get() = dbClient.getRealm()


    override suspend fun addActivity(activityEntity: ActivityEntity) {
        realm.executeTransaction { realm ->
            if (realm.where(RealmActivityEntity::class.java)
                .equalTo("name", activityEntity.name)
                .findAll() != null) {
                throw IllegalArgumentException("Realm already has activity ${activityEntity.name}")
            }
            val realmEntity = if (activityEntity.id == 0) {
                val nextId = realm.where(RealmActivityEntity::class.java)
                    .max("id")
                    ?.toInt()
                    ?.plus(1)
                    ?: 1
                RealmActivityEntity.fromDomain(activityEntity).apply {
                    id = nextId
                }
            } else {
                RealmActivityEntity.fromDomain(activityEntity)
            }
            realm.insertOrUpdate(realmEntity)
        }
    }

    override suspend fun deleteActivity(activityId: Int) {
        realm.executeTransaction { realm ->
            realm.where(RealmActivityEntity::class.java).equalTo("id", activityId)
                .findFirst()?.deleteFromRealm()
        }
    }

    override suspend fun getActivities(): List<ActivityEntity> {
        val result = realm.where(RealmActivityEntity::class.java)
            .findAll()
            ?: throw IllegalStateException("Realm Activities is empty")
        return realm.copyFromRealm(result).map { it.toDomain() }
    }

    override suspend fun getActivityTimeLog(activityId: Int): List<TimeLogEntity> {
        val activityEntity = realm.where(RealmActivityEntity::class.java)
            .equalTo("id", activityId)
            .findFirst()
            ?: throw IllegalArgumentException("Realm has not activity with id: $activityId")
        val timeLogIds = activityEntity.logsIds?.map { it.value }
            ?: return emptyList()

        val timeLog: ArrayList<TimeLogEntity> = arrayListOf()

        timeLogIds.forEach {
            val timeLogItem = realm.where(RealmTimeLogEntity::class.java)
                .equalTo("id", it)
                .findFirst()
            if (timeLogItem != null) {
                timeLog.add(timeLogItem.toDomain())
            }
        }

        return timeLog
    }
}