package com.nikitakrapo.progressif.ui.errors

import com.nikitakrapo.progressf.strings.Res
import com.nikitakrapo.progressf.strings.error_connection_message
import com.nikitakrapo.progressf.strings.error_server_message
import com.nikitakrapo.progressf.strings.error_unknown_message
import com.nikitakrapo.progressif.domain.models.error.FetchError
import com.nikitakrapo.progressif.strings.Text

fun FetchError.getUserMessage(): Text {
    return when (this) {
        FetchError.Network.Connectivity -> Text.StringRes(Res.string.error_connection_message)
        FetchError.Network.Server -> Text.StringRes(Res.string.error_server_message)
        FetchError.Network.Unknown -> Text.StringRes(Res.string.error_unknown_message)
    }
}