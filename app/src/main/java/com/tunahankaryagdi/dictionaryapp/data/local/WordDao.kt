package com.tunahankaryagdi.dictionaryapp.data.local

import androidx.room.*
import com.tunahankaryagdi.dictionaryapp.data.model.Word


@Dao
interface WordDao {


    @Query("SELECT * FROM words")
    suspend fun getAllWords() : List<Word>

    @Query("SELECT * FROM words WHERE word LIKE :key OR description LIKE :key")
    suspend fun getWordsByKey(key : String) : List<Word>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWord(vararg word : Word)

    @Delete
    suspend fun deleteWord(word: Word)

}