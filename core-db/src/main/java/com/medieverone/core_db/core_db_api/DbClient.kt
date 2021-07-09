package com.medieverone.core_db.core_db_api

import io.realm.Realm

interface DbClient {

    fun getRealm(): Realm
}