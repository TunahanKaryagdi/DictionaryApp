package com.tunahankaryagdi.dictionaryapp.util



sealed class WordTypes (var type : String){
    object Noun : WordTypes("Noun")
    object Verb : WordTypes("Verb")
    object Adverb : WordTypes("Adverb")
    object Expression : WordTypes("Expression")
    object Adjective : WordTypes("Adjective")
}