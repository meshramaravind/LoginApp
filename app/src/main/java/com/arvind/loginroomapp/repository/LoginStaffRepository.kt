package com.arvind.loginroomapp.repository

import com.arvind.loginroomapp.model.LoginStaffUser
import com.arvind.loginroomapp.storage.db.LoginAppDatabadse
import javax.inject.Inject

class LoginStaffRepository @Inject constructor(private val db: LoginAppDatabadse) {

    // insert login staff
    suspend fun insert(loginStaffUser: LoginStaffUser) = db.getLoginDao().insertStaff(
        loginStaffUser
    )

    // update login
    suspend fun update(loginStaffUser: LoginStaffUser) = db.getLoginDao().updateStaff(
        loginStaffUser
    )

    // delete login
    suspend fun delete(loginStaffUser: LoginStaffUser) = db.getLoginDao().deleteStaff(
        loginStaffUser
    )

    //all get data
    fun getAllLlginstaff() = db.getLoginDao().getAllStaff()
}