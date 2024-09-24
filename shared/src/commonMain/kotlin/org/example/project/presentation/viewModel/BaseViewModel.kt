package org.example.project.presentation.viewModel

import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    fun clear() {
        onCleared()
    }
}