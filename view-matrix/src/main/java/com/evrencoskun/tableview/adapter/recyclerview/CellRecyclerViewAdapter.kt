package com.evrencoskun.tableview.adapter.recyclerview

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import com.evrencoskun.tableview.listener.VerticalScrollTableListener
import ru.endroad.shared.serial.entity.Episode

class CellRecyclerViewAdapter(
	private val context: Context,
	private val verticalItemDecoration: ItemDecoration?,
	private val getVerticalScrollTableListener: () -> VerticalScrollTableListener,
	private val getFirstVisibleColumnNumber: () -> Int,
	private val getRowPositionOffset: () -> Int
) : AbstractRecyclerViewAdapter<List<Episode?>>() {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val recyclerView = RecyclerView(context).apply {
			verticalItemDecoration?.let(::addItemDecoration)
			setHasFixedSize(true)
			addOnScrollListener(getVerticalScrollTableListener())
			layoutManager = LinearLayoutManager(context, VERTICAL, false)
			adapter = CellRowRecyclerViewAdapter()
		}
		return CellRowViewHolder(recyclerView)
	}

	override fun onBindViewHolder(holder: ViewHolder, yPosition: Int) {
		val viewAdapter = (holder as? CellRowViewHolder)
			?.recyclerView
			?.adapter as? CellRowRecyclerViewAdapter

		viewAdapter?.setItems(getItem(yPosition))
	}

	override fun onViewAttachedToWindow(holder: ViewHolder) {
		super.onViewAttachedToWindow(holder)
		val viewHolder = holder as CellRowViewHolder

		val number = getFirstVisibleColumnNumber()
		val offset = getRowPositionOffset()
		(viewHolder.recyclerView.layoutManager as? LinearLayoutManager)
			?.scrollToPositionWithOffset(number, offset)
	}

	internal class CellRowViewHolder(val recyclerView: RecyclerView) : ViewHolder(recyclerView)
}