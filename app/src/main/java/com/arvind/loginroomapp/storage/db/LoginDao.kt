package com.arvind.loginroomapp.storage.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.arvind.loginroomapp.model.LoginStaffUser
import kotlinx.coroutines.flow.Flow

@Dao
interface LoginDao {
    // used to insert new staff
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStaff(loginStaffUser: LoginStaffUser)

    // used to update existing staff
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateStaff(loginStaffUser: LoginStaffUser)

    // used to delete transaction
    @Delete
    suspend fun deleteStaff(loginStaffUser: LoginStaffUser)

    // get all saved transaction list
    @Query("SELECT * FROM all_loginroom ORDER by createdAt DESC")
    fun getAllStaff(): LiveData<List<LoginStaffUser>>
}