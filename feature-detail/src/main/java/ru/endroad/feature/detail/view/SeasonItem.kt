package ru.endroad.feature.detail.view

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.IModelItem
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.items.ModelAbstractItem
import ru.endroad.feature.detail.R
import ru.endroad.shared.serial.entity.Episode
import ru.endroad.shared.serial.entity.Season

class SeasonItem(season: Season) : ModelAbstractItem<Season, SeasonItem.ViewHolder>(season) {

	override val type = R.id.season_item

	override val layoutRes = R.layout.detail_season_item

	override fun getViewHolder(v: View) = ViewHolder(v)

	override fun bindView(holder: ViewHolder, payloads: List<Any>) {
		super.bindView(holder, payloads)
		holder.seasonNumber.text = model.rating.toString()
		holder.episodeList.setEpisodes(model.episodes)
	}

	private fun RecyclerView.setEpisodes(episodes: List<Episode>) {
		val episodeList = ItemAdapter<IModelItem<Episode, *>>().setNewList(episodes.map(::EpisodeItem))
		adapter = FastAdapter.with(episodeList)
	}

	class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
		val seasonNumber: TextView = view.findViewById(R.id.season_number)
		val episodeList: RecyclerView = view.findViewById(R.id.episode_list)
	}
}