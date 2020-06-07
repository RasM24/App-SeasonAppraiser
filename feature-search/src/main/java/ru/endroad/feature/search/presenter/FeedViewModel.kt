package ru.endroad.feature.search.presenter

import android.widget.SearchView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.endroad.feature.search.router.SearchSeriesRouter
import ru.endroad.shared.serial.domain.GetSerialListByNameUseCase
import ru.endroad.shared.serial.entity.Serial

class SearchViewModel(
	private val getSerialListByNameUseCase: GetSerialListByNameUseCase,
	private val searchSeriesRouter: SearchSeriesRouter
) : ViewModel() {
	//TODO обработчик ошибок
	val serialList = MutableLiveData<List<Serial>>()

	fun set(textView: SearchView) {
		viewModelScope.launch {
			textView.textChangeFlow
				.filter { it.length > 2 }
				.debounce(100)
				.collect {
					val serial = getSerialListByNameUseCase(it)
					serialList.value = serial
				}
		}
	}

	fun openSerial(id: String) = searchSeriesRouter.openSeries(id)

	private val SearchView.textChangeFlow: Flow<String>
		get() = callbackFlow<String> {
			val textWatcher = object : SearchView.OnQueryTextListener {
				override fun onQueryTextSubmit(query: String) = false
				override fun onQueryTextChange(s: String): Boolean {
					offer(s)
					return true
				}
			}
			setOnQueryTextListener(textWatcher)
			awaitClose {  }
		}.buffer(Channel.CONFLATED)
}