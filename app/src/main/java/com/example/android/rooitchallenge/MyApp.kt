package com.example.android.rooitchallenge

import android.app.Application
import coil.decode.withInterruptibleSource
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import io.realm.Realm

@HiltAndroidApp
class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)

    }
}