package com.evrencoskun.tableview.holder

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.evrencoskun.tableview.R
import com.evrencoskun.tableview.inflate
import kotlinx.android.synthetic.main.table_season_item.view.*

class ColumnHeaderViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.table_season_item)) {

	fun bind(seasonNumber: Int) {
		itemView.cell_season_number.text = "S$seasonNumber"
	}
}