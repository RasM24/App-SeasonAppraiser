package ru.endroad.tableview.adapter.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.endroad.tableview.holder.RowHeaderViewHolder

class RowHeaderRecyclerViewAdapter : AbstractRecyclerViewAdapter<Int>() {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
		RowHeaderViewHolder(parent)

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		(holder as? RowHeaderViewHolder)?.bind(getItem(position))
	}

	override fun getItemViewType(position: Int): Int = 2
}