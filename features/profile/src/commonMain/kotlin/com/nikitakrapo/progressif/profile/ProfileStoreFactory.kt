package com.nikitakrapo.progressif.profile

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import com.nikitakrapo.progressif.auth.user.AuthState
import com.nikitakrapo.progressif.auth.user.User
import com.nikitakrapo.progressif.auth.user.UserRepository
import com.nikitakrapo.progressf.strings.Res
import com.nikitakrapo.progressf.strings.common_error_unknown
import com.nikitakrapo.progressf.strings.profile_logout_item_text
import com.nikitakrapo.progressif.profile.ProfileStore.Label
import com.nikitakrapo.progressif.strings.Text
import kotlinx.coroutines.launch

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
                    val user = (userRepository.state.value as? AuthState.SignedIn)?.user
                    val sections = buildSections(user)
                    dispatch(Msg.SectionsUpdated(sections))
                }
                onIntent<ProfileStore.Intent.ShowLogoutConfirmation> {
                    dispatch(Msg.ShowLogoutConfirmation)
                }
                onIntent<ProfileStore.Intent.DismissLogoutConfirmation> {
                    dispatch(Msg.DismissLogoutConfirmation)
                }
                onIntent<ProfileStore.Intent.AcceptLogoutConfirmation> {
                    dispatch(Msg.DismissLogoutConfirmation)
                    launch {
                        val result = userRepository.logout()
                        result.fold(
                            onFailure = {
                                val snackbar = Text.StringRes(Res.string.common_error_unknown)
                                publish(ProfileStore.Label.ShowSnackbar(snackbar))
                            },
                            onSuccess = {},
                        )
                    }
                }
            },
            reducer = { msg: Msg ->
                when (msg) {
                    is Msg.SectionsUpdated -> copy(sections = msg.sections)
                    Msg.ShowLogoutConfirmation -> copy(isLogoutConfirmationShown = true)
                    Msg.DismissLogoutConfirmation -> copy(isLogoutConfirmationShown = false)
                }
            },
        ) {}

    private sealed interface Msg {
        data class SectionsUpdated(val sections: List<ProfileSection>) : Msg
        data object ShowLogoutConfirmation : Msg
        data object DismissLogoutConfirmation : Msg
    }
}

internal fun buildSections(user: User?): List<ProfileSection> = listOf(
    ProfileSection.Header(
        displayName = user?.displayName ?: "",
    ),
    ProfileSection.Button(
        label = Text.StringRes(Res.string.profile_logout_item_text),
        intent = ProfileStore.Intent.ShowLogoutConfirmation,
    ),
)
