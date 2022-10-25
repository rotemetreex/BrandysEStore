package com.rotemyanco.brandysestore

import android.app.Application
import com.rotemyanco.brandysestore.database.MagicAliExpressDB
import com.rotemyanco.brandysestore.retrofit.MagicAliExpressAPIRepo
import com.rotemyanco.brandysestore.retrofit.MagicAliExpressAPIService


class App : Application() {


    companion object {
        private lateinit var instance: App
        private val db: MagicAliExpressDB by lazy {
            MagicAliExpressDB.create(instance)
        }

        private val service: MagicAliExpressAPIService by lazy {
            MagicAliExpressAPIService.create()
        }

        val repo: MagicAliExpressAPIRepo by lazy {
            MagicAliExpressAPIRepo(instance, db.getCategoryDao(), db.getProductDao(), service)
        }

    }


    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}