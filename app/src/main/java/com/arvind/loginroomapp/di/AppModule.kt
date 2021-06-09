package com.arvind.loginroomapp.di

import android.app.Application
import com.arvind.loginroomapp.storage.datastore.UIModeDataStore
import com.arvind.loginroomapp.storage.db.LoginAppDatabadse
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Singleton

@InstallIn(ActivityComponent::class)
@Module
class AppModule {

    @Singleton
    @Provides
    fun providePreferenceManager(application: Application): UIModeDataStore {
        return UIModeDataStore(application.applicationContext)
    }

    @Singleton
    @Provides
    fun provideNoteDatabase(application: Application): LoginAppDatabadse {
        return LoginAppDatabadse.invoke(application.applicationContext)
    }

}