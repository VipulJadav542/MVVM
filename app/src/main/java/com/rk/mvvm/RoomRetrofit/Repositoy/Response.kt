package com.rk.mvvm.RoomRetrofit.Repositoy

import android.view.textclassifier.ConversationActions.Message
import java.lang.Error

sealed class Response<T>(val data: T? = null, val errorMessage: String? = null) {
    class Loading<T> : Response<T>()
    class Success<T>(data: T? = null) : Response<T>(data = data)
    class Error<T>(error: String) : Response<T>(errorMessage = error)
}