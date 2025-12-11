package com.example.caiomansho

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

///////////////////////////////////////////////////////
//                                                   //
//              DO NOT MODIFY THIS FILE              //
//                                                   //
///////////////////////////////////////////////////////
@HiltAndroidApp
class AndroidApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: AndroidApp
            private set

        fun getString(resId: Int): String {
            return instance.getString(resId)
        }
    }
}
