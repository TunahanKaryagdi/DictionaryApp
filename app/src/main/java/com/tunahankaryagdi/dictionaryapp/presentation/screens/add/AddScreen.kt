package com.tunahankaryagdi.dictionaryapp.presentation.theme

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment
import com.tunahankaryagdi.dictionaryapp.R
import com.tunahankaryagdi.dictionaryapp.presentation.screens.add.AddViewModel
import com.tunahankaryagdi.dictionaryapp.presentation.util.largeSize
import com.tunahankaryagdi.dictionaryapp.presentation.util.mediumSize
import com.tunahankaryagdi.dictionaryapp.presentation.util.smallSize

@Composable
fun AddScreen(
    viewModel: AddViewModel = hiltViewModel(),
    navController : NavController
) {






    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(mediumSize)


    ) {

        FlowRow(
            mainAxisAlignment = MainAxisAlignment.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            viewModel.radioOptions.forEach {
                CustomRadioButton(
                    text = it.type,
                    onClick = { viewModel.selectedValue = it.type },
                    isSelected = it.type == viewModel.selectedValue
                )
                Spacer(modifier = Modifier.width(largeSize))
            }

        }

        Spacer(modifier = Modifier.height(largeSize))

        CustomTextField(
            text = viewModel.word,
            onValueChange = { viewModel.word = it },
            hint = stringResource(id = R.string.word)
        )

        Spacer(modifier = Modifier.height(mediumSize))

        CustomTextField(
            text = viewModel.description,
            onValueChange = { viewModel.description = it },
            hint = stringResource(id = R.string.description)
        )

        Spacer(modifier = Modifier.height(mediumSize))

        Button(
            onClick = {
                viewModel.save()
            }
        ) {
            Text(text = stringResource(id = R.string.save))
        }


    }


}


@Composable
fun CustomRadioButton(
    text: String,
    onClick: () -> Unit,
    isSelected: Boolean
) {

    if (isSelected) {
        TextButton(
            onClick = onClick,
            modifier = Modifier
                .border(2.dp, MaterialTheme.colors.primary, RoundedCornerShape(smallSize))
        ) {

            Text(text = text)
        }
    } else {
        TextButton(
            onClick = onClick,
        ) {

            Text(text = text)
        }
    }
}

@Composable
fun CustomTextField(
    text: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    hint: String
) {

    TextField(
        modifier = modifier
            .fillMaxWidth(),
        value = text,
        onValueChange = onValueChange,
        placeholder = {
            Text(text = hint)
        }
    )

}