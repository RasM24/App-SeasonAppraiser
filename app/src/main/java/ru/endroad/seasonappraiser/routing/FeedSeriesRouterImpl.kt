package ru.endroad.seasonappraiser.routing

import androidx.fragment.app.FragmentManager
import ru.endroad.feature.detail.view.SeriesDetailMatrixFragment
import ru.endroad.feature.feed.router.FeedSeriesRouter
import ru.endroad.navigation.routing.FragmentRouting
import ru.endroad.navigation.routing.changeRoot
import ru.endroad.seasonappraiser.R

class FeedSeriesRouterImpl(override val fragmentManager: FragmentManager) : FeedSeriesRouter, FragmentRouting {

	override fun openSeries(seriesId: String) = changeRoot(SeriesDetailMatrixFragment.newInstance(seriesId), R.id.root)
}