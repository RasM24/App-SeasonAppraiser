package ru.endroad.feature.detail.view

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
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
		holder.itemView.background = ColorDrawable(resolveBackgroundColor(model.rating))
		holder.rating.text = model.rating.toString()
	}

	class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
		val rating: TextView = view.findViewById(R.id.episode_rating)
	}

	//TODO вынести в UseCase
	private fun resolveBackgroundColor(rating: Double): Int = when {
		rating > 8 -> Color.parseColor("00FF00")
		rating > 7 -> Color.parseColor("ADFF2F")
		rating > 5 -> Color.parseColor("FFA500")
		rating > 3 -> Color.parseColor("B22222")
		else       -> Color.parseColor("808080")
	}
}