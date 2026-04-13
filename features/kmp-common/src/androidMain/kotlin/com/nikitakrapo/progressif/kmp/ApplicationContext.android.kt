package com.nikitakrapo.progressif.kmp

import android.content.Context

actual class ApplicationContext(val context: Context)

fun Context.toKmpContext() = ApplicationContext(this)
