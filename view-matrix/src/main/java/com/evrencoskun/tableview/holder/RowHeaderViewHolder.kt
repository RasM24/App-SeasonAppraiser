package com.evrencoskun.tableview.holder

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.evrencoskun.tableview.R
import com.evrencoskun.tableview.inflate
import kotlinx.android.synthetic.main.table_episode_item.view.*

class RowHeaderViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.table_episode_item)) {

	fun bind(episodeNumber: Int) {
		itemView.cell_episode_number.text = "E$episodeNumber"
	}
}