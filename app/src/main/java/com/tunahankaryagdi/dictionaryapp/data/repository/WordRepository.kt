package com.tunahankaryagdi.dictionaryapp.data.repository

import com.tunahankaryagdi.dictionaryapp.data.local.WordDao
import com.tunahankaryagdi.dictionaryapp.data.model.Word
import com.tunahankaryagdi.dictionaryapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class WordRepository @Inject constructor(private val dao: WordDao) {

    fun getAllWords(): Flow<Resource<List<Word>>> {

        return flow {
            try {
                emit(Resource.Loading())
                emit(Resource.Success(dao.getAllWords()))
            } catch (e: Exception) {
                emit(Resource.Error(e.localizedMessage))
            }
        }

    }


    suspend fun deleteWord(word: Word) {
        dao.deleteWord(word)
    }

    suspend fun insertWord(word: Word) {
        dao.insertWord(word)
    }
}