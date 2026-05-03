package com.nikitakrapo.progressif.strings

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.getString
import org.jetbrains.compose.resources.stringResource

sealed interface Text {

    @Composable
    fun resolve(): String

    // TODO: monitor resources API, maybe there will be synchronous Android Resources analogue
    suspend fun resolveAsync(): String

    data class StringRes(
        val resource: StringResource,
    ) : Text {

        @Composable
        override fun resolve(): String {
            return stringResource(resource)
        }

        override suspend fun resolveAsync(): String {
            return getString(resource)
        }
    }

    data class Raw(
        val value: String,
    ) : Text {

        @Composable
        override fun resolve(): String {
            return value
        }

        override suspend fun resolveAsync(): String {
            return value
        }
    }
}