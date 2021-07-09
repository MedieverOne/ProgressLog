package com.medieverone.core_db.core_db_api

import com.medieverone.module_injector.BaseApi

interface CoreDbApi : BaseApi {

    fun dbClient(): DbClient
}