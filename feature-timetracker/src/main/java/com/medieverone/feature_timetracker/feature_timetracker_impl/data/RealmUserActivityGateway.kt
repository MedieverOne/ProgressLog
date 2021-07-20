package com.medieverone.feature_timetracker.feature_timetracker_impl.data

import com.medieverone.core_db.core_db_api.DbClient
import com.medieverone.feature_timetracker.feature_timetracker_impl.data.entities.RealmTimeLogEntity
import com.medieverone.feature_timetracker.feature_timetracker_impl.data.entities.RealmUserActivityEntity
import com.medieverone.feature_timetracker.feature_timetracker_impl.domain.entities.UserActivityEntity
import com.medieverone.feature_timetracker.feature_timetracker_impl.gateways.LocalUserActivitiesGateway
import io.realm.Realm

class RealmUserActivityGateway(
    private val database: DbClient
) : LocalUserActivitiesGateway {

    override suspend fun addUserActivity(userActivityEntity: UserActivityEntity) {
            database.getRealm().executeTransaction { realm ->
                realm.insertOrUpdate(RealmUserActivityEntity.fromDomain(userActivityEntity))
            }
//            if (realm.where(RealmUserActivityEntity::class.java)
//                    .equalTo("tag", userActivityEntity.tag)
//                    .findFirst() == null) {
//                realm.insertOrUpdate(RealmUserActivityEntity.fromDomain(userActivityEntity))
//            } else {
//                throw IllegalArgumentException("This UserActivity: ${userActivityEntity.tag} is exist")
//            }
    }

    override suspend fun getUserActivity(activityTag: String): UserActivityEntity {
        val result = database.getRealm().where(RealmUserActivityEntity::class.java)
            .equalTo("tag", activityTag)
            .findFirst() ?: throw IllegalArgumentException("$activityTag doesn't exist")
        return result.toDomain()
    }

    override suspend fun getAllUserActivities(): List<UserActivityEntity> {
        val result = database.getRealm().where(RealmUserActivityEntity::class.java)
            .findAll() ?: throw IllegalStateException("There's no activities")
        return result.map { it.toDomain() }
    }

    override suspend fun deleteUserActivity(activityTag: String) {
        database.getRealm().executeTransaction { realm ->
            val activity = realm.where(RealmUserActivityEntity::class.java)
                .equalTo("tag", activityTag)
                .findFirst()
            if (activity != null) {
                activity.deleteFromRealm()
                activity.id?.let { deleteTimeLogsOfActivity(realm, activityId = it) }
            }
        }
    }

    private fun deleteTimeLogsOfActivity(realm: Realm, activityId: Int) {
        realm.where(RealmTimeLogEntity::class.java)
            .equalTo("activityId", activityId)
            .findAll()
            ?.deleteAllFromRealm()
    }
}