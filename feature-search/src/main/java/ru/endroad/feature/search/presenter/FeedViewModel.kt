package ru.endroad.feature.search.presenter

import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView
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

	fun set(textView: TextView) {
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

	private val TextView.textChangeFlow: Flow<String>
		get() = callbackFlow<String> {
			val textWatcher = object : TextWatcher {
				override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
				override fun afterTextChanged(s: Editable?) {}
				override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
					offer(s?.toString() ?: "")
				}
			}
			addTextChangedListener(textWatcher)
			awaitClose { removeTextChangedListener(textWatcher) }
		}.buffer(Channel.CONFLATED)
}