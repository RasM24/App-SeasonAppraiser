package ru.endroad.tableview.holder

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.endroad.tableview.inflate
import kotlinx.android.synthetic.main.table_episode_item.view.*
import ru.endroad.tableview.R

class RowHeaderViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.table_episode_item)) {

	fun bind(episodeNumber: Int) {
		itemView.cell_episode_number.text = "E$episodeNumber"
	}
}