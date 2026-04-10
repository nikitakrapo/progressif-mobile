package com.nikitakrapo.progressif.design.icon

import com.nikitakrapo.progressf.design.core.Res
import com.nikitakrapo.progressf.design.core.arrow_back_24
import com.nikitakrapo.progressf.design.core.close_24
import com.nikitakrapo.progressf.design.core.person_24
import com.nikitakrapo.progressf.design.core.skateboarding_24
import com.nikitakrapo.progressf.design.core.trending_up_24
import com.nikitakrapo.progressif.design.theme.ProgressifTheme
import org.jetbrains.compose.resources.DrawableResource

val ProgressifTheme.icons: Icons get() = ProgressifIcons

interface Icons {
    val arrowBack: DrawableResource
    val cross: DrawableResource
    val progressions: DrawableResource
    val profile: DrawableResource
    val skateboarding: DrawableResource
}

internal object ProgressifIcons : Icons {
    override val arrowBack: DrawableResource = Res.drawable.arrow_back_24
    override val cross: DrawableResource = Res.drawable.close_24
    override val progressions: DrawableResource = Res.drawable.trending_up_24
    override val profile: DrawableResource = Res.drawable.person_24
    override val skateboarding: DrawableResource = Res.drawable.skateboarding_24
}

