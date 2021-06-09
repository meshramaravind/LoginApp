package com.arvind.loginroomapp.utils

import com.arvind.loginroomapp.model.LoginStaffUser

sealed class ViewState {
    object Loading : ViewState()
    object Empty : ViewState()
    data class Success(val loginStaffUser: List<LoginStaffUser>) : ViewState()
    data class Error(val exception: Throwable) : ViewState()
}
