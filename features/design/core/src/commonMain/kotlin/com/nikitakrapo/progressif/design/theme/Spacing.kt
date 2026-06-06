package com.nikitakrapo.progressif.design.theme

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.dp

@Suppress("UnusedReceiverParameter")
val ProgressifTheme.spacing get() = Spacing

object Spacing {

    val screen = PaddingValues(horizontal = 16.dp, vertical = 16.dp)

    val vertical = Vertical

    val horizontal = Horizontal

    object Vertical {

        val buttonToText = 12.dp
        val componentToButton = 32.dp
        val betweenComponents = 16.dp
    }

    object Horizontal {


    }
}