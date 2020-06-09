package ru.endroad.feature.search.state

import ru.endroad.shared.serial.entity.Serial

internal sealed class ScreenState {
	object Loading : ScreenState()
	class Data(val serialList: List<Serial>) : ScreenState()
	object EmptyData : ScreenState()
	class Error(val errorMessage: String) : ScreenState()
}