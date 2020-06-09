package ru.endroad.feature.search.presenter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.endroad.feature.search.router.SearchSeriesRouter
import ru.endroad.feature.search.state.ModelEvent
import ru.endroad.feature.search.state.ScreenState
import ru.endroad.mvi.core.viewmodel.PresenterMvi
import ru.endroad.shared.serial.domain.GetSerialListByNameUseCase

internal typealias SearchScreenPresenter = PresenterMvi<ScreenState, ModelEvent>

internal class SearchViewModel(
	private val getSerialListByNameUseCase: GetSerialListByNameUseCase,
	private val searchSeriesRouter: SearchSeriesRouter
) : ViewModel(), SearchScreenPresenter {

	override val state = MutableLiveData<ScreenState>()

	override fun reduce(event: ModelEvent) {
		when (event) {
			is ModelEvent.ClickOnSerial -> searchSeriesRouter.openSeries(event.id)
			is ModelEvent.Search -> viewModelScope.launch { event.reduce() }
		}
	}

	private suspend fun ModelEvent.Search.reduce() {
		val serialList = getSerialListByNameUseCase(query)
		state.value = ScreenState.Data(serialList)
	}
}