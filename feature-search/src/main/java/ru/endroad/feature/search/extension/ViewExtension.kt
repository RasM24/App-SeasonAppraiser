package ru.endroad.feature.search.extension

import android.widget.SearchView
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.callbackFlow

//TODO возможно вынести
internal val SearchView.textChangeFlow: Flow<String>
	get() = callbackFlow<String> {
		val textWatcher = object : SearchView.OnQueryTextListener {
			override fun onQueryTextSubmit(query: String) = false
			override fun onQueryTextChange(s: String): Boolean {
				offer(s)
				return true
			}
		}
		setOnQueryTextListener(textWatcher)
		awaitClose { }
	}.buffer(Channel.CONFLATED)