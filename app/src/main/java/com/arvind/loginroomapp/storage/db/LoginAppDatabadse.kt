package com.arvind.loginroomapp.storage.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.arvind.loginroomapp.model.LoginStaffUser

@Database(
    entities = [LoginStaffUser::class], version = 1,
    exportSchema = false
)
abstract class LoginAppDatabadse : RoomDatabase() {

    abstract fun getLoginDao(): LoginDao

    companion object {
        @Volatile
        private var instance: LoginAppDatabadse? = null
        private val LOCK = Any()

        // Check for DB instance if not null then get or insert or else create new DB Instance
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {

            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            LoginAppDatabadse::class.java,
            "login.db"
        ).build()
    }

}