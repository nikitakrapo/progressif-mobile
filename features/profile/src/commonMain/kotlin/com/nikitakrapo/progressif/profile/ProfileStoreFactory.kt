package com.nikitakrapo.progressif.profile

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import com.nikitakrapo.progressif.auth.user.User
import com.nikitakrapo.progressif.auth.user.UserRepository
import com.nikitakrapo.progressf.strings.Res
import com.nikitakrapo.progressf.strings.profile_logout_item_text
import com.nikitakrapo.progressif.strings.Text

class ProfileStoreFactory(
    private val storeFactory: StoreFactory,
    private val userRepository: UserRepository,
) {

    fun create(): ProfileStore =
        object : ProfileStore, Store<ProfileStore.Intent, ProfileState, ProfileStore.Label> by storeFactory.create(
            name = "ProfileStore",
            initialState = ProfileState(),
            executorFactory = coroutineExecutorFactory {
                onIntent<ProfileStore.Intent.Refresh> {
                    val sections = buildSections(userRepository.user.value)
                    dispatch(Msg.SectionsUpdated(sections))
                }
                onIntent<ProfileStore.Intent.ShowLogoutConfirmation> {
                    dispatch(Msg.ShowLogoutConfirmation)
                }
            },
            reducer = { msg: Msg ->
                when (msg) {
                    is Msg.SectionsUpdated -> copy(sections = msg.sections)
                    Msg.ShowLogoutConfirmation -> copy(isLogoutConfirmationShown = true)
                }
            },
        ) {}

    private sealed interface Msg {
        data class SectionsUpdated(val sections: List<ProfileSection>) : Msg
        data object ShowLogoutConfirmation : Msg
    }
}

internal fun buildSections(user: User?): List<ProfileSection> = listOf(
    ProfileSection.Header(displayName = user?.displayName ?: ""),
    ProfileSection.Button(
        label = Text.StringRes(Res.string.profile_logout_item_text),
        intent = ProfileStore.Intent.ShowLogoutConfirmation,
    ),
)
