package org.example.project.di

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.cancel
import org.example.project.presentation.viewModel.BaseViewModel
import org.example.project.presentation.viewModel.ExampleViewModel
import org.koin.core.module.Module
import org.koin.core.scope.Scope
import org.koin.core.scope.ScopeCallback
import org.koin.dsl.module


internal actual fun getNativeModule() = module {
//    factory { ExampleViewModel() }
    viewModelScoped { ExampleViewModel() }
}

inline fun <reified T : BaseViewModel> Module.viewModelScoped(crossinline createViewModel: Scope.() -> T) {
    scope<T> {
        scoped<T> {
            val viewModel = createViewModel()
            registerCallback(object : ScopeCallback {
                override fun onScopeClose(scope: Scope) {
                    viewModel.viewModelScope.cancel()
                    viewModel.clear()
                }
            })
            viewModel
        }
    }
}