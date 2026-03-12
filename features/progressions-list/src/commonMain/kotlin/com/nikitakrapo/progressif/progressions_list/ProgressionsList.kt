package com.nikitakrapo.progressif.progressions_list

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nikitakrapo.progressif.design.theme.ProgressifTheme
import com.nikitakrapo.progressif.design.theme.spacing

@Composable
fun ProgressionsList(
    component: ProgressionsListComponent,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .safeContentPadding(),
    ) {
        items(100) {
            ListItem(
                headlineContent = {
                    Text("Progression $it")
                },
            )
            Spacer(modifier = Modifier.height(ProgressifTheme.spacing.betweenComponents))
        }
    }
}