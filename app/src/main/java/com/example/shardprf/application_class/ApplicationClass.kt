package com.example.shardprf.application_class

import android.app.Application
import android.content.Context
import com.example.shardprf.shared_preferences.AppPreferences

class ApplicationClass: Application() {
    lateinit var context: Context
    companion object {
        private lateinit var instace: ApplicationClass

        lateinit var appPreference1: AppPreferences
        fun getAppPreference(): AppPreferences {
            return appPreference1
        }

        fun the(): ApplicationClass {
            return instace
        }
    }

    override fun onCreate() {
        super.onCreate()
        instace = this
        context = applicationContext
        appPreference1 = AppPreferences.init(context, "MyAppName")!!

    }
}