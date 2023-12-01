package com.example.samplenavigation.ui.theme

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val queryArgument: String? = savedStateHandle["query"]
}