package ru.endroad.feature.detail.view

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mikepenz.fastadapter.items.ModelAbstractItem
import ru.endroad.feature.detail.R
import ru.endroad.shared.serial.entity.Episode

class EpisodeItem(episode: Episode) : ModelAbstractItem<Episode, EpisodeItem.ViewHolder>(episode) {

	override val type = R.id.episode_item

	override val layoutRes = R.layout.detail_episode_item

	override fun getViewHolder(v: View) = ViewHolder(v)

	override fun bindView(holder: ViewHolder, payloads: List<Any>) {
		super.bindView(holder, payloads)
		holder.itemView.setBackgroundColorByRating(model.rating)
		holder.rating.text = model.rating.toString()
	}

	class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
		val rating: TextView = view.findViewById(R.id.episode_rating)
	}

	private fun View.setBackgroundColorByRating(rating: Double): Int = when {
		rating > 9.8 -> resources.getColor(R.color.cell_rating_ideal)
		rating > 8.9 -> resources.getColor(R.color.cell_rating_awesome)
		rating > 7.9 -> resources.getColor(R.color.cell_rating_good)
		rating > 6.4 -> resources.getColor(R.color.cell_rating_normal)
		rating > 5.1 -> resources.getColor(R.color.cell_rating_bad)
		rating > 0.0 -> resources.getColor(R.color.cell_rating_awful)
		else -> resources.getColor(R.color.cell_rating_none)
	}
}