package ru.endroad.feature.feed.di

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.endroad.feature.feed.presenter.FeedViewModel

val feedFeatureModule = module {
	viewModel { FeedViewModel(get(), get(), get(), get()) }

//	viewModel<FeedViewModel>()
}