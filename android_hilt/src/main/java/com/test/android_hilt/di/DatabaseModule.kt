package com.test.android_hilt.di

import android.content.Context
import androidx.room.Room
import com.test.android_hilt.data.AppDatabase
import com.test.android_hilt.data.LogDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2022/08/23 1:28 오후
 * @desc
 */

// ApplicationComponent 는 Dagger 2.30 부터 Deprecated 됨.
// 2.31 부터 SingletonComponent 로 바뀜.
@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    fun provideLogDao(database: AppDatabase): LogDao{
        return database.logDao()
    }

    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase{
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "logging.db"
        ).build()
    }
}