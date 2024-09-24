package org.example.project.di

import org.koin.core.module.Module
import org.koin.dsl.module

val sharedModule = module {
    includes(getNativeModule())
}

internal expect fun getNativeModule(): Module
