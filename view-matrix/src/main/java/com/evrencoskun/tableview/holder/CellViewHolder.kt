package com.evrencoskun.tableview.holder

import android.content.Context
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.RecyclerView
import com.evrencoskun.tableview.R
import com.evrencoskun.tableview.inflate
import kotlinx.android.synthetic.main.table_cell_item.view.*
import ru.endroad.shared.serial.entity.Episode

//TODO негибкий вариант, попробовать что-то поменять
class CellViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.table_cell_item)) {

	fun bind(episode: Episode) {
		itemView.cell_rating.text = episode.rating.toString()
		itemView.setBackgroundColor(itemView.context.getColorBackground(episode.rating))
	}

	@ColorInt
	private fun Context.getColorBackground(rating: Double) = when {
		rating >= 9.9 -> resources.getColor(R.color.cell_rating_ideal)
		rating >= 9.0 -> resources.getColor(R.color.cell_rating_awesome)
		rating >= 8.0 -> resources.getColor(R.color.cell_rating_good)
		rating >= 6.5 -> resources.getColor(R.color.cell_rating_normal)
		rating >= 5.0 -> resources.getColor(R.color.cell_rating_bad)
		rating >= 2.0 -> resources.getColor(R.color.cell_rating_awful)
		else -> resources.getColor(R.color.cell_rating_none)
	}
}