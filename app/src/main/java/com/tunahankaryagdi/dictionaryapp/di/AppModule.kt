package com.tunahankaryagdi.dictionaryapp.di

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.tunahankaryagdi.dictionaryapp.data.local.WordDao
import com.tunahankaryagdi.dictionaryapp.data.local.WordDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideWordDatabase(@ApplicationContext appContext : Context) : WordDatabase{
        return Room.databaseBuilder(
            appContext,
            WordDatabase::class.java,
            "Dictionary"
        )
            .fallbackToDestructiveMigration()
            .build()
    }


    @Provides
    @Singleton
    fun provideWordDao(wordDatabase: WordDatabase) : WordDao{
        return  wordDatabase.wordDao()
    }
}