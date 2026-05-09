package com.nikitakrapo.progressif

import android.app.Application
import com.nikitakrapo.progressif.di.AppDi
import com.nikitakrapo.progressif.firebase.FirebaseApp
import com.nikitakrapo.progressif.kmp.toKmpContext
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

class ProgressifApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Napier.base(DebugAntilog())
        Napier.d { "Application created" }
        FirebaseApp.initialize(this.toKmpContext())
        AppDi.start(applicationContext = this.toKmpContext())
    }
}