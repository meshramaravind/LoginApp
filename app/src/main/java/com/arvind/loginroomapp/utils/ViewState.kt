package com.arvind.notewakeup.utils

import com.arvind.loginroomapp.model.StaffUser

sealed class ViewState {
    object Loading : ViewState()
    object Empty : ViewState()
    data class Success(val staffUser: List<StaffUser>) : ViewState()
    data class Error(val exception: Throwable) : ViewState()
}
