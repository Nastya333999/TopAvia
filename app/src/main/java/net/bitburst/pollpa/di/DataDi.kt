package net.bitburst.pollpa.di

import net.bitburst.pollpa.data.Repo
import net.bitburst.pollpa.data.RepoImpl
import net.bitburst.pollpa.view.spl.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {
    single<Repo> { RepoImpl(get()) }

    viewModel() { MainViewModel(get(), get()) }

}