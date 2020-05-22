package ru.endroad.feature.detail.view

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.detail_series_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import ru.endroad.camp.extension.list.CampListFragment
import ru.endroad.feature.detail.R
import ru.endroad.feature.detail.presenter.SeriesDetailViewModel

class SeriesDetailFragment : CampListFragment() {

	companion object {
		private const val SERIES_ID = "seriesId"

		fun newInstance(seriesId: String): Fragment =
			SeriesDetailFragment().apply {
				arguments = bundleOf(SERIES_ID to seriesId)
			}
	}

	override val layout = R.layout.detail_series_fragment

	private val viewModel by viewModel<SeriesDetailViewModel> {
		parametersOf(requireArguments().getString(SERIES_ID))
	}

	override fun setupViewComponents() {
		viewModel.seriesDetail.observe(this, Observer {
			Picasso.get().load(it.posterUrl).centerCrop().fit().into(poster)
			itemAdapter.set(it.seasonList.map(::SeasonItem))
		})
	}
}