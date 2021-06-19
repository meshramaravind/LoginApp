package com.arvind.loginroomapp.storage.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.arvind.loginroomapp.model.LoginUser
import kotlinx.coroutines.flow.Flow

@Dao
interface LoginDao {
    // used to insert new staff
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStaff(loginUser: LoginUser)

    // used to insert login staff
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertloginStaff(loginUser: LoginUser)

    // used to update existing staff
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateStaff(loginUser: LoginUser)

    // used to delete staff
    @Delete
    suspend fun deleteStaff(loginUser: LoginUser)

    // get all saved staff list
    @Query("SELECT * FROM all_loginroom ORDER by id DESC")
    fun getAllStaff(): LiveData<List<LoginUser>>

    // get all login staff list
    @Query("SELECT * FROM all_loginroom WHERE email = :email AND password = :password")
    fun getLoginUser(email: String, password: String): LiveData<LoginUser>

    // get login email
    @Query("SELECT * FROM all_loginroom WHERE email = :email")
    fun getLoginFindByEmail(email: String): Boolean

    // get single staff by id
    @Query("SELECT * FROM all_loginroom WHERE id = :id")
    fun getLoginStaffByID(id: Int): Flow<LoginUser>
}