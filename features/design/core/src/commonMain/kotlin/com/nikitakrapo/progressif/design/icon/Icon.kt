package com.nikitakrapo.progressif.design.icon

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

sealed interface Icon {

    @Composable
    fun resolve(): Painter

    data class DrawableRes(
        private val drawableResource: DrawableResource
    ) : Icon {

        @Composable
        override fun resolve(): Painter {
            return painterResource(drawableResource)
        }
    }
}
