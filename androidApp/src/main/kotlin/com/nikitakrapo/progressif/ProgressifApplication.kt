package com.nikitakrapo.progressif

import android.app.Application
import com.nikitakrapo.progressif.di.AppDi
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

class ProgressifApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Napier.base(DebugAntilog())
        AppDi.start()
    }
}