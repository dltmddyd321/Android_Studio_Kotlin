package com.example.picapp.dependencyinjection

import com.example.picapp.repositories.EditImageRepository
import com.example.picapp.repositories.EditImageRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    factory<EditImageRepository> { EditImageRepositoryImpl(androidContext()) }
}