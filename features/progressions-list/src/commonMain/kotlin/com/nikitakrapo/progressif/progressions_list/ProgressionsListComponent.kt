package com.nikitakrapo.progressif.progressions_list

import kotlinx.coroutines.flow.StateFlow

interface ProgressionsListComponent {

    val state: StateFlow<ProgressionsListState>
}