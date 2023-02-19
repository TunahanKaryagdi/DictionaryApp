package com.tunahankaryagdi.dictionaryapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tunahankaryagdi.dictionaryapp.data.model.Word


@Database(entities = [Word::class],version = 3)
abstract class WordDatabase : RoomDatabase() {
    abstract fun wordDao () : WordDao
}