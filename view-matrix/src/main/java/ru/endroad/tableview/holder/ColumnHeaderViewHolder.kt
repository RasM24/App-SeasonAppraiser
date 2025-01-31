package ru.endroad.tableview.holder

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.endroad.tableview.inflate
import kotlinx.android.synthetic.main.table_season_item.view.*
import ru.endroad.tableview.R

class ColumnHeaderViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.table_season_item)) {

	fun bind(seasonNumber: Int) {
		itemView.cell_season_number.text = "S$seasonNumber"
	}
}