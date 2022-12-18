package net.bitburst.pollpa.di

import net.bitburst.pollpa.data.Repository
import net.bitburst.pollpa.data.RepositoryImpl
import net.bitburst.pollpa.view.spl.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {
    single<Repository> { RepositoryImpl(get()) }

    viewModel() { MainViewModel(get(), get()) }

}