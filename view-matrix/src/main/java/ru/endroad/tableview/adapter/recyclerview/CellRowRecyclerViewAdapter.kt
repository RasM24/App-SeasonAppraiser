package ru.endroad.tableview.adapter.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.endroad.tableview.holder.CellViewHolder
import ru.endroad.shared.serial.entity.Episode

class CellRowRecyclerViewAdapter : AbstractRecyclerViewAdapter<Episode?>() {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
		CellViewHolder(parent)

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		getItem(position)?.let { (holder as? CellViewHolder)?.bind(it) }
	}

	override fun getItemViewType(position: Int): Int = if (getItem(position) == null) -1 else 0
}