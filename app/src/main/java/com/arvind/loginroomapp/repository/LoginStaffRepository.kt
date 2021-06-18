package com.arvind.loginroomapp.repository

import com.arvind.loginroomapp.model.LoginStaffUser
import com.arvind.loginroomapp.storage.db.LoginAppDatabadse
import javax.inject.Inject

class LoginStaffRepository @Inject constructor(private val db: LoginAppDatabadse) {

    // insert login staff
    suspend fun insert(loginStaffUser: LoginStaffUser) = db.getLoginDao().insertStaff(
        loginStaffUser
    )

    // insert login staff
    suspend fun insertlogin(loginStaffUser: LoginStaffUser) = db.getLoginDao().insertloginStaff(
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

    // get login staff details by ID
    fun getByID(id: Int) = db.getLoginDao().getLoginStaffByID(id)

    //all get data
    fun getAllLlginstaff() = db.getLoginDao().getAllStaff()

    //get login
    fun getLogin(email: String, password: String) = db.getLoginDao().getLoginStaff(email, password)

    //get email
    fun getLoginEmail(email: String) = db.getLoginDao().getLoginFindByEmail(email)

}