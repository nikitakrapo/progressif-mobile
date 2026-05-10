package com.nikitakrapo.progressif.design.utils.annotatedstring

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle

fun buildAnnotatedString(
    vararg parts: Pair<String, SpanStyle?>
): AnnotatedString {
    return buildAnnotatedString {
        parts.forEach { part ->
            part.second?.let {
                withStyle(it) {
                    append(part.first)
                }
            } ?: run {
                append(part.first)
            }
        }
    }
}