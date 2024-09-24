package org.example.project.presentation.viewModel

import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

private const val TAG = "ExampleViewModel"

class ExampleViewModel : BaseViewModel() {
    private val _counterFlow = MutableStateFlow(0)
    val counterFlow: Flow<Int> = _counterFlow

    private var counterJob: Job? = null

    fun startCounter() {
        Logger.d(TAG) { "startCounter" }
        var counter = 0
        counterJob = viewModelScope.launch(Dispatchers.IO) {
            while (true) {
                Logger.d(TAG) { "Emitting: $counter" }
                _counterFlow.emit(counter)
                delay(1000)
                counter++
            }
        }
    }

    override fun onCleared() {
        Logger.d(TAG) { "onCleared" }
        super.onCleared()
        counterJob?.cancel()
    }
}