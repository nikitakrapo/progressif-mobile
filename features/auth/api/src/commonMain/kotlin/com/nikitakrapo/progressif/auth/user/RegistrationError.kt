package com.nikitakrapo.progressif.auth.user

data class RegistrationError(
    val usernameError: UsernameError? = null,
    val emailError: EmailError? = null,
    val passwordError: PasswordError? = null,
) {

    enum class UsernameError {
        AlreadyInUse,
        ;
    }

    enum class EmailError {
        Invalid,
        AlreadyInUse,
        ;
    }

    enum class PasswordError {
        Weak,
        ;
    }
}
