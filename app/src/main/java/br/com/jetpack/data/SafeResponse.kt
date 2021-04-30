package br.com.jetpack.data

import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

/**
 *Created by humbertokalex
 */

/*
    Esta é uma classe que fiz para encapsular as chamadas asyncronas, seja para um database local
    como o room ou para um serviço de api.
    Aqui você pode mapear retornos de rede que você ache relevante e retornar um pouco mais refinado
    para sua viewModel. Ex: WrongPassword - Quando o usuario retorna a senha errada voce pode ja ter ações
    padronizadas especificas prontas pra lidar com isso, então melhor ja ter o retorno sabendo oque é,
    do que um erro genérico.
 */

sealed class SafeResponse<out T> {
    data class Success<out T>(val value: T) : SafeResponse<T>()
    data class GenericError(val code: Int? = null, val error: Response<*>? = null) :
        SafeResponse<Nothing>()

    object NetworkError : SafeResponse<Nothing>()
}

suspend fun <T> safeRequest(request: suspend () -> T): SafeResponse<T> {
    return try {
        SafeResponse.Success(request())
    } catch (throwable: Throwable) {
        return when (throwable) {
            is IOException -> {
                SafeResponse.NetworkError
            }
            is HttpException -> {
                SafeResponse.GenericError(throwable.code(), throwable.response())
            }
            else -> {
                SafeResponse.GenericError(null, null)
            }
        }
    }
}
