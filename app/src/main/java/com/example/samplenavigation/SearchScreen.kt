package com.example.samplenavigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.samplenavigation.ui.theme.SearchViewModel

@Composable
fun SearchScreen(
    onClickSingleTop: (String) -> Unit,
    onClickNavigate: (String) -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
){
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        val text = viewModel.queryArgument
        Column {
            Text(text = text.orEmpty())
            Button(onClick = { onClickSingleTop((0..100).random().toString()) }) {
                Text(text = "launchSingletop = true")
            }
            Button(onClick = { onClickNavigate((0..100).random().toString()) }) {
                Text(text = "launchSingletop = false")
            }
        }
    }
}