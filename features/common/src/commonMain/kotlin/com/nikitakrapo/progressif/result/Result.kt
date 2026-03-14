@file:OptIn(ExperimentalContracts::class)

package com.nikitakrapo.progressif.result

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract


sealed class Result<out D, out E> {

    fun isSuccess(): Boolean {
        contract {
            returns(true) implies (this@Result is Success)
            returns(false) implies (this@Result is Success)
        }
        return this@Result is Success<D>
    }

    fun isFailure(): Boolean {
        contract {
            returns(true) implies (this@Result is Failure)
            returns(false) implies (this@Result is Failure)
        }
        return this@Result is Failure<E>
    }

    fun dataOrNull(): D? {
        contract {
            returnsNotNull() implies (this@Result is Success)
        }
        return dataOrElse { null }
    }

    inline fun <C> fold(onSuccess: (data: D) -> C, onFailure: (error: E) -> C): C {
        contract {
            callsInPlace(onSuccess, InvocationKind.AT_MOST_ONCE)
            callsInPlace(onFailure, InvocationKind.AT_MOST_ONCE)
        }
        return when (this) {
            is Success -> onSuccess(data)
            is Failure -> onFailure(error)
        }
    }

    inline fun <D1> mapSuccess(f: (success: D) -> D1): Result<D1, E> {
        contract {
            callsInPlace(f, InvocationKind.AT_MOST_ONCE)
        }
        return when (this) {
            is Success -> Success(f(data))
            is Failure -> Failure(error)
        }
    }

    inline fun <E1> mapFailure(f: (E) -> E1): Result<D, E1> {
        contract {
            callsInPlace(f, InvocationKind.AT_MOST_ONCE)
        }
        return when (this) {
            is Success -> Success(data)
            is Failure -> Failure(f(error))
        }
    }

    data class Success<D>(
        val data: D,
    ) : Result<D, Nothing>()

    data class Failure<E>(
        val error: E,
    ) : Result<Nothing, E>()
}

inline infix fun <D, E> Result<D, E>.dataOrElse(default: (E) -> D): D {
    contract {
        callsInPlace(default, InvocationKind.AT_MOST_ONCE)
    }
    return when (this) {
        is Result.Success -> data
        is Result.Failure -> default(error)
    }
}