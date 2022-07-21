package com.example.runningtracker.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.room.Room
import com.example.runningtracker.db.RunDao
import com.example.runningtracker.db.RunningDatabase
import com.example.runningtracker.other.Constants.KEY_FRIST_TIME_TOGGLE
import com.example.runningtracker.other.Constants.KEY_NAME
import com.example.runningtracker.other.Constants.KEY_WEIGHT
import com.example.runningtracker.other.Constants.RUNNING_DATABASE_NAME
import com.example.runningtracker.other.Constants.SHARED_PREFERENCES_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
@Singleton
@Provides
   fun  provideRunnningDatabase(@ApplicationContext app: Context)= Room.databaseBuilder(
    app,
    RunningDatabase::class.java,
    RUNNING_DATABASE_NAME)
    .build()

    @Singleton
    @Provides
    fun provideRunDao(db: RunningDatabase)= db.getRunDao()

 @Singleton
 @Provides
 fun provideSharedPreferences(@ApplicationContext app: Context) =app.getSharedPreferences(SHARED_PREFERENCES_NAME , MODE_PRIVATE)

 @Singleton
 @Provides
 fun provideName(SharedPref: SharedPreferences) = SharedPref.getString(KEY_NAME, "") ?: ""

 @Singleton
 @Provides
 fun provideWeight(SharedPref: SharedPreferences) = SharedPref.getFloat(KEY_WEIGHT, 47f)

 @Singleton
 @Provides
 fun provideFristTimeToggle(SharedPref: SharedPreferences) = SharedPref.getBoolean(KEY_FRIST_TIME_TOGGLE , true)
}