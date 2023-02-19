package com.tunahankaryagdi.dictionaryapp.presentation.screens.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tunahankaryagdi.dictionaryapp.R
import com.tunahankaryagdi.dictionaryapp.data.model.Word
import com.tunahankaryagdi.dictionaryapp.presentation.util.cardHeight
import com.tunahankaryagdi.dictionaryapp.presentation.util.expandCardHeight
import com.tunahankaryagdi.dictionaryapp.presentation.util.mediumSize
import com.tunahankaryagdi.dictionaryapp.presentation.util.smallSize


@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {


    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(mediumSize)
            .wrapContentSize()
            .scrollable(rememberScrollState(), orientation = Orientation.Vertical)
    ) {


        CustomSearchBar(
            text = viewModel.searchText,
            onValueChange = {
                viewModel.searchWord(it)
            },
        )

        if (!viewModel.isLoading) {
            if (viewModel.wordList.isEmpty()) {
                EmptyContent()
            } else {
                WordList(
                    wordList = viewModel.wordList,
                    onDeleteClick = {
                        viewModel.deleteWord(it)
                    }
                )
            }
        } else {
            Loading()
        }

    }

}


@Composable
fun CustomSearchBar(
    modifier: Modifier = Modifier,
    text: String,
    onValueChange: (String) -> Unit
) {

    Surface(
        elevation = 10.dp,
        color = Color.White,
        modifier = modifier
            .fillMaxWidth()


    ) {
        OutlinedTextField(
            value = text,
            onValueChange = onValueChange,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(id = R.string.search_bar)
                )
            },
            singleLine = true,
        )
    }
}


@Composable
fun WordList(
    modifier: Modifier = Modifier,
    wordList: List<Word>,
    onDeleteClick: (Word) -> Unit
) {
    println(wordList)
    LazyColumn {
        items(wordList) { word ->
            WordCard(word = word, onDeleteClick = onDeleteClick)
        }
    }
}


@Composable
fun WordCard(
    modifier: Modifier = Modifier,
    word: Word,
    onDeleteClick: (Word) -> Unit
) {
    var isExpand by remember {
        mutableStateOf(false)
    }

    Card(
        elevation = smallSize,
        modifier = modifier
            .fillMaxWidth()
            .padding(smallSize)
            .clickable {
                isExpand = !isExpand
            }
    ) {

        if (isExpand) {

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(expandCardHeight)
                    .padding(smallSize)

            ) {

                Column() {
                    Text(
                        text = word.word,
                        style = MaterialTheme.typography.h2
                    )
                    Text(
                        text = word.description,
                        style = MaterialTheme.typography.body1
                    )
                }

                Column {
                    Text(
                        text = word.type,
                        color = MaterialTheme.colors.primary
                    )
                    IconButton(onClick = {
                        onDeleteClick(word)
                    }) {
                        Icon(
                            imageVector = Icons.Default.Delete, contentDescription = stringResource(
                                id = R.string.delete_word
                            )
                        )
                    }
                }


            }
        } else {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(
                        cardHeight
                    )
                    .padding(smallSize)

            ) {

                Text(
                    text = word.word,
                    style = MaterialTheme.typography.h2
                )

                Text(
                    text = word.type,
                    color = MaterialTheme.colors.primary
                )

            }
        }

    }
}


@Composable
fun EmptyContent(
    modifier: Modifier = Modifier,
) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
    ) {
        Text(
            stringResource(
                id = R.string.empty_list
            ),
            style = MaterialTheme.typography.h1
        )
    }
}


@Composable
fun Loading(
    modifier: Modifier = Modifier
) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}