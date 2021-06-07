package com.arvind.loginroomapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.text.DateFormat

@Entity(tableName = "LoginRoom")
@Parcelize
data class StaffUser(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val designation: String,
    val salary: Double,
    val createdAt: Long = System.currentTimeMillis(),
) : Parcelable {
    val createdAtDateFormat: String
        get() = DateFormat.getDateTimeInstance()
            .format(createdAt) // Date Format: Jan 11, 2021, 11:30 AM
}
