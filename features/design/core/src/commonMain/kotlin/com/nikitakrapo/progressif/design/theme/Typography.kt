package com.nikitakrapo.progressif.design.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.nikitakrapo.progressf.design.core.Res
import com.nikitakrapo.progressf.design.core.SpaceGrotesk_Bold
import com.nikitakrapo.progressf.design.core.SpaceGrotesk_Light
import com.nikitakrapo.progressf.design.core.SpaceGrotesk_Medium
import com.nikitakrapo.progressf.design.core.SpaceGrotesk_Regular
import com.nikitakrapo.progressf.design.core.SpaceGrotesk_SemiBold
import org.jetbrains.compose.resources.Font

internal object Typography {

    @Suppress("unused")
    val Roboto = FontFamily.Default

    val SpaceGrotesk @Composable get() = FontFamily(
        Font(Res.font.SpaceGrotesk_Light, FontWeight.Light),
        Font(Res.font.SpaceGrotesk_Regular, FontWeight.Normal),
        Font(Res.font.SpaceGrotesk_SemiBold, FontWeight.SemiBold),
        Font(Res.font.SpaceGrotesk_Medium, FontWeight.Medium),
        Font(Res.font.SpaceGrotesk_Bold, FontWeight.Bold),
    )

    val AppTypography @Composable get() = Typography(
        labelLarge = TextStyle(
            fontFamily = SpaceGrotesk,
            fontWeight = FontWeight.Medium,
            letterSpacing = 0.sp,
            lineHeight = 20.sp,
            fontSize = 14.sp,
        ),
        labelMedium = TextStyle(
            fontFamily = SpaceGrotesk,
            fontWeight = FontWeight.Medium,
            letterSpacing = 0.1.sp,
            lineHeight = 16.sp,
            fontSize = 12.sp,
        ),
        labelSmall = TextStyle(
            fontFamily = SpaceGrotesk,
            fontWeight = FontWeight.Medium,
            letterSpacing = 0.1.sp,
            lineHeight = 16.sp,
            fontSize = 11.sp,
        ),
        bodyLarge = TextStyle(
            fontFamily = SpaceGrotesk,
            fontWeight = FontWeight.W400,
            letterSpacing = 0.sp,
            lineHeight = 24.sp,
            fontSize = 16.sp,
        ),
        bodyMedium = TextStyle(
            fontFamily = SpaceGrotesk,
            fontWeight = FontWeight.W400,
            letterSpacing = 0.sp,
            lineHeight = 20.sp,
            fontSize = 14.sp,
        ),
        bodySmall = TextStyle(
            fontFamily = SpaceGrotesk,
            fontWeight = FontWeight.W400,
            letterSpacing = 0.1.sp,
            lineHeight = 16.sp,
            fontSize = 12.sp,
        ),
        headlineLarge = TextStyle(
            fontFamily = SpaceGrotesk,
            fontWeight = FontWeight.W400,
            letterSpacing = 0.sp,
            lineHeight = 40.sp,
            fontSize = 32.sp,
        ),
        headlineMedium = TextStyle(
            fontFamily = SpaceGrotesk,
            fontWeight = FontWeight.W400,
            letterSpacing = 0.sp,
            lineHeight = 36.sp,
            fontSize = 28.sp,
        ),
        headlineSmall = TextStyle(
            fontFamily = SpaceGrotesk,
            fontWeight = FontWeight.W400,
            letterSpacing = 0.sp,
            lineHeight = 32.sp,
            fontSize = 24.sp,
        ),
        displayLarge = TextStyle(
            fontFamily = SpaceGrotesk,
            fontWeight = FontWeight.W400,
            letterSpacing = 0.sp,
            lineHeight = 64.sp,
            fontSize = 57.sp,
        ),
        displayMedium = TextStyle(
            fontFamily = SpaceGrotesk,
            fontWeight = FontWeight.W400,
            letterSpacing = 0.sp,
            lineHeight = 52.sp,
            fontSize = 45.sp,
        ),
        displaySmall = TextStyle(
            fontFamily = SpaceGrotesk,
            fontWeight = FontWeight.W400,
            letterSpacing = 0.sp,
            lineHeight = 44.sp,
            fontSize = 36.sp,
        ),
        titleLarge = TextStyle(
            fontFamily = SpaceGrotesk,
            fontWeight = FontWeight.W400,
            letterSpacing = 0.sp,
            lineHeight = 28.sp,
            fontSize = 22.sp,
        ),
        titleMedium = TextStyle(
            fontFamily = SpaceGrotesk,
            fontWeight = FontWeight.Medium,
            letterSpacing = 0.sp,
            lineHeight = 24.sp,
            fontSize = 16.sp,
        ),
        titleSmall = TextStyle(
            fontFamily = SpaceGrotesk,
            fontWeight = FontWeight.Medium,
            letterSpacing = 0.sp,
            lineHeight = 20.sp,
            fontSize = 14.sp,
        ),
    )
}
