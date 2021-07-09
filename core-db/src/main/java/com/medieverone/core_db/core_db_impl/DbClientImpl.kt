package com.medieverone.core_db.core_db_impl

import android.content.Context
import com.medieverone.core_db.core_db_api.DbClient
import com.medieverone.core_db.core_db_impl.Constants.REALM_NAME
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Inject

internal class DbClientImpl @Inject constructor(
    private val context: Context
): DbClient {

    override fun getRealm(): Realm {
        Realm.init(context)
        RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .name(REALM_NAME)
            .build()
            .let(Realm::setDefaultConfiguration)
        return Realm.getDefaultInstance()
    }
}