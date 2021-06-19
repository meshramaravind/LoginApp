package com.arvind.loginroomapp.utils

import com.arvind.loginroomapp.model.LoginUser


sealed class DetailState {
    object Loading : DetailState()
    object Empty : DetailState()
    data class Success(val loginUser: LoginUser) : DetailState()
    data class Error(val exception: Throwable) : DetailState()
}
