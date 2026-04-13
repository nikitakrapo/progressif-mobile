package com.nikitakrapo.progressif.firebase

import com.nikitakrapo.progressif.kmp.ApplicationContext

// TODO: move to common module, not auth
expect object FirebaseApp {

    fun initialize(applicationContext: ApplicationContext)
}