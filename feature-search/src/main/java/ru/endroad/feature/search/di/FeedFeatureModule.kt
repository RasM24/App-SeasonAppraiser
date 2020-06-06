package ru.endroad.feature.search.di

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.endroad.feature.search.presenter.SearchViewModel

val searchFeatureModule = module {
	viewModel { SearchViewModel(get(), get()) }
}