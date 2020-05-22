package ru.endroad.feature.detail.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import ru.endroad.config.feature.entity.FeatureConfig
import ru.endroad.shared.serial.domain.GetSerialDetailUseCase
import ru.endroad.shared.serial.entity.Serial

class SeriesDetailViewModel(
	private val getSeriesDetail: GetSerialDetailUseCase,
	private val configuration: FeatureConfig
) : ViewModel() {

	val seriesDetail: LiveData<Serial> = liveData {
		val series = getSeriesDetail()
		emit(series)
	}
}