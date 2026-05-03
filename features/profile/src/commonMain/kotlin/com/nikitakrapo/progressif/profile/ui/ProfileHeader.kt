package com.nikitakrapo.progressif.profile.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikitakrapo.progressif.design.theme.PreviewTheme

@Composable
internal fun ProfileHeader(
    displayName: String,
    modifier: Modifier = Modifier,
) {
    Card(modifier = modifier) {
        Text(
            text = displayName,
            modifier = Modifier.padding(16.dp),
        )
    }
}

@Preview
@Composable
private fun ProfileHeaderPreview() {
    PreviewTheme {
        ProfileHeader(displayName = "Alice")
    }
}
