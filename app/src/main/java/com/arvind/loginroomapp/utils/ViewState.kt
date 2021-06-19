package com.arvind.loginroomapp.utils

import com.arvind.loginroomapp.model.LoginUser

sealed class ViewState {
    object Loading : ViewState()
    object Empty : ViewState()
    data class Success(val loginUser: List<LoginUser>) : ViewState()
    data class Error(val exception: Throwable) : ViewState()
}
