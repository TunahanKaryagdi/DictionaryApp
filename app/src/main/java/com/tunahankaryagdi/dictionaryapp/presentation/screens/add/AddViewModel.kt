package com.tunahankaryagdi.dictionaryapp.presentation.screens.add

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tunahankaryagdi.dictionaryapp.data.repository.WordRepository
import com.tunahankaryagdi.dictionaryapp.data.model.Word
import com.tunahankaryagdi.dictionaryapp.util.WordTypes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddViewModel @Inject constructor(private val repository: WordRepository) : ViewModel() {


    val radioOptions = listOf(
        WordTypes.Adverb,
        WordTypes.Adjective,
        WordTypes.Noun,
        WordTypes.Expression,
        WordTypes.Verb
    )

    var selectedValue by mutableStateOf(radioOptions[0].type)
    var word by mutableStateOf("")
    var description by mutableStateOf("")


    fun save() {
        if (word.isNotBlank() && description.isNotBlank()) {

            viewModelScope.launch {
                repository.insertWord(
                    Word(
                        word = word,
                        description = description,
                        type = selectedValue
                    )
                )
            }.invokeOnCompletion {
                if (it == null) {

                    selectedValue = radioOptions[0].type
                    word = ""
                    description = ""
                    return@invokeOnCompletion
                }

            }
        }
    }



}