package com.arvind.loginroomapp.utils

import com.arvind.loginroomapp.model.LoginStaffUser


sealed class DetailState {
    object Loading : DetailState()
    object Empty : DetailState()
    data class Success(val loginStaffUser: LoginStaffUser) : DetailState()
    data class Error(val exception: Throwable) : DetailState()
}
