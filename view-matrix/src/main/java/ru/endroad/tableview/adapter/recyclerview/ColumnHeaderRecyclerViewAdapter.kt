package ru.endroad.tableview.adapter.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.*
import ru.endroad.tableview.holder.ColumnHeaderViewHolder

class ColumnHeaderRecyclerViewAdapter : AbstractRecyclerViewAdapter<Int>() {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
		ColumnHeaderViewHolder(parent)

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		(holder as? ColumnHeaderViewHolder)?.bind(getItem(position))
	}

	override fun getItemViewType(position: Int): Int = 1
}