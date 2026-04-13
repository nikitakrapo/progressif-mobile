package com.nikitakrapo.progressif.firebase

import com.nikitakrapo.progressif.kmp.ApplicationContext
import com.google.firebase.FirebaseApp as AndroidFirebaseApp

actual object FirebaseApp {

    actual fun initialize(applicationContext: ApplicationContext) {
        AndroidFirebaseApp.initializeApp(applicationContext.context)
    }
}