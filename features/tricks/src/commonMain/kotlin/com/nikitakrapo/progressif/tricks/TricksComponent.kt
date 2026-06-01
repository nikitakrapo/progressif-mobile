package com.nikitakrapo.progressif.tricks

import kotlinx.coroutines.flow.StateFlow

interface TricksComponent {

    val state: StateFlow<TricksState>

    fun onRefresh()
}
