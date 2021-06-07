package com.arvind.loginroomapp.utils

import com.arvind.loginroomapp.model.StaffUser


sealed class DetailState {
    object Loading : DetailState()
    object Empty : DetailState()
    data class Success(val staffUser: StaffUser) : DetailState()
    data class Error(val exception: Throwable) : DetailState()
}
