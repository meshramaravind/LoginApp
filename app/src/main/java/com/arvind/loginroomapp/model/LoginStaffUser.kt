package com.arvind.loginroomapp.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.text.DateFormat

@Entity(tableName = "all_loginroom")
@Parcelize
data class LoginStaffUser(
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "designationType")
    var designationType: String,
    @ColumnInfo(name = "salary")
    var salary: Double,
    @ColumnInfo(name = "email")
    var email: String,
    @ColumnInfo(name = "password")
    var password: String,
    @ColumnInfo(name = "confirmpassword")
    var confirmpassword: String,
    @ColumnInfo(name = "date")
    var date: String,
    @ColumnInfo(name = "createdAt")
    var createdAt: Long = System.currentTimeMillis(),
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,
) : Parcelable {
    val createdAtDateFormat: String
        get() = DateFormat.getDateTimeInstance()
            .format(createdAt) // Date Format: Jan 11, 2021, 11:30 AM
}
