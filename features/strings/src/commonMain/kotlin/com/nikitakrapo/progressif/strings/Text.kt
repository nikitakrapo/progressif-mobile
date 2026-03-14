package com.nikitakrapo.progressif.strings

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

sealed interface Text {

    @Composable
    fun resolve(): String

    data class StringRes(
        val resource: StringResource,
    ) : Text {

        @Composable
        override fun resolve(): String {
            return stringResource(resource)
        }
    }

    data class Raw(
        val value: String,
    ) : Text {

        @Composable
        override fun resolve(): String {
            return value
        }
    }
}