package org.example.project.di

import org.example.project.presentation.viewModel.ExampleViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin

class DiComponent : KoinComponent {

    fun exampleViewModel(key: String) = getKoin()
        .getOrCreateScope<ExampleViewModel>(key)
        .get<ExampleViewModel>()

    fun clearExampleViewModel(key: String) {
        getKoin().getScopeOrNull(key)?.close()
    }
}

fun setupDi() {
    startKoin {
        modules(sharedModule)
    }
}