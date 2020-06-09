package ru.endroad.feature.search.state

sealed class ModelEvent {
	class ClickOnSerial(val id: String) : ModelEvent()
	class Search(val query: String) : ModelEvent()
}