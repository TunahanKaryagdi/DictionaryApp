package com.tunahankaryagdi.dictionaryapp.presentation.screens.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tunahankaryagdi.dictionaryapp.data.model.Word
import com.tunahankaryagdi.dictionaryapp.data.repository.WordRepository
import com.tunahankaryagdi.dictionaryapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val repository: WordRepository) : ViewModel() {


    private var initialWords: List<Word> = emptyList()


    var wordList by mutableStateOf<List<Word>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf("")
        private set

    var searchText by mutableStateOf("")
        private set



    //TODO
     init {
         loadWords()
        println("init çalıştı")
     }


    private fun loadWords() {

        val a = repository.getAllWords()
        viewModelScope.launch {

            a.collect {
                when (it) {
                    is Resource.Success -> {
                        wordList = it.data ?: emptyList()
                        initialWords = it.data ?: emptyList()
                        isLoading = false
                    }
                    is Resource.Error -> {
                        isLoading = false
                        errorMessage = it.message ?: "error message"
                    }
                    is Resource.Loading -> {
                        isLoading = true
                    }
                }
            }
        }
    }

    fun searchWord(searchWord: String) {

        searchText = searchWord
        wordList = initialWords.filter {
            it.word.contains(searchWord)
        }.toList()
    }


    fun deleteWord(word: Word) {
        viewModelScope.launch {
            repository.deleteWord(word)
            loadWords()
        }
    }
}