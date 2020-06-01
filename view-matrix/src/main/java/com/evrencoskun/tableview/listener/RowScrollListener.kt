package com.evrencoskun.tableview.listener

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE

class RowScrollListener(
	private val onRowScroll: (Int) -> Unit
) : RecyclerView.OnScrollListener() {

	private var state: Int = SCROLL_STATE_IDLE

	override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
		super.onScrolled(recyclerView, dx, dy)
		if (state != SCROLL_STATE_IDLE) onRowScroll(dy)
	}

	override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
		super.onScrollStateChanged(recyclerView, newState)
		state = newState
	}
}