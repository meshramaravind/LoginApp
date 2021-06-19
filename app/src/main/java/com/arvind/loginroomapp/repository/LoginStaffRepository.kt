package com.arvind.loginroomapp.repository

import com.arvind.loginroomapp.model.LoginUser
import com.arvind.loginroomapp.storage.db.LoginAppDatabadse
import javax.inject.Inject

class LoginStaffRepository @Inject constructor(private val db: LoginAppDatabadse) {

    // insert login staff
    suspend fun insert(loginUser: LoginUser) = db.getLoginDao().insertStaff(
        loginUser
    )

    // insert login staff
    suspend fun insertlogin(loginUser: LoginUser) = db.getLoginDao().insertloginStaff(
        loginUser
    )

    // update login
    suspend fun update(loginUser: LoginUser) = db.getLoginDao().updateStaff(
        loginUser
    )

    // delete login
    suspend fun delete(loginUser: LoginUser) = db.getLoginDao().deleteStaff(
        loginUser
    )

    // get login staff details by ID
    fun getByID(id: Int) = db.getLoginDao().getLoginStaffByID(id)

    //all get data
    fun getAllLlginstaff() = db.getLoginDao().getAllStaff()

    //get login
    fun getLogin(email: String, password: String) = db.getLoginDao().getLoginUser(email, password)

    //get email
    fun getLoginEmail(email: String) = db.getLoginDao().getLoginFindByEmail(email)


}