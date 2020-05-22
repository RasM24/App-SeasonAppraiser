package ru.endroad.feature.detail.di

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.endroad.feature.detail.presenter.SeriesDetailViewModel

val seriesDetailFeatureModule = module {
	viewModel { (seriesId: String) -> SeriesDetailViewModel(get(), get(), seriesId) }
}