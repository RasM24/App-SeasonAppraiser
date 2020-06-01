package ru.endroad.feature.detail.view

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.detail_series_matrix_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import ru.endroad.camp.fragment.CampFragment
import ru.endroad.feature.detail.R
import ru.endroad.feature.detail.presenter.SeriesDetailViewModel
import ru.endroad.shared.serial.entity.Episode
import ru.endroad.shared.serial.entity.Season

class SeriesDetailMatrixFragment : CampFragment() {

	companion object {
		private const val SERIES_ID = "seriesId"

		fun newInstance(seriesId: String): Fragment =
			SeriesDetailMatrixFragment().apply {
				arguments = bundleOf(SERIES_ID to seriesId)
			}
	}

	override val layout = R.layout.detail_series_matrix_fragment

	private val viewModel by viewModel<SeriesDetailViewModel> {
		parametersOf(requireArguments().getString(SERIES_ID))
	}

	override fun setupViewComponents() {
		val itemAdapter = season_list.adapter

		viewModel.seriesDetail.observe(this, Observer { serial ->
			val rawEpisodes = serial.seasonList
			val episodes = serial.seasonList.toMatrix(serial.seasonList.maxCountEpisodes)


			itemAdapter.setAllItems(
				List(serial.seasonList.size) { it + 1 },
				List(serial.seasonList.maxCountEpisodes) { it + 1 },
				serial.seasonList.toMatrix(serial.seasonList.maxCountEpisodes)
			)
		})
	}

	private val List<Season>.maxCountEpisodes get() = maxBy { it.episodes.size }?.episodes?.size ?: 0

	private fun List<Season>.toMatrix(count: Int): List<List<Episode?>> =
		this.map { season -> List(count) { runCatching { season.episodes[it] }.getOrNull() } }
}