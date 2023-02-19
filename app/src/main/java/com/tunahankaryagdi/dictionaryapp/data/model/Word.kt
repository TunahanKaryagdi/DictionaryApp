package com.tunahankaryagdi.dictionaryapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tunahankaryagdi.dictionaryapp.util.WordTypes


@Entity(tableName = "words")
data class Word(
    @PrimaryKey(autoGenerate = true) val id : Int = 0,
    val word : String,
    val description : String,
    var type : String
)