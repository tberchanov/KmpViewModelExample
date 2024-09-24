package org.example.project.di

import org.example.project.presentation.viewModel.ExampleViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal actual fun getNativeModule() = module {
    viewModel { ExampleViewModel() }
}