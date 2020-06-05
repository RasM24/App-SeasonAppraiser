package ru.endroad.seasonappraiser.routing

import ru.endroad.feature.detail.view.SeriesDetailMatrixFragment
import ru.endroad.feature.feed.router.FeedSeriesRouter
import ru.endroad.navigation.routing.FragmentRouting
import ru.endroad.navigation.routing.forwardTo
import ru.endroad.seasonappraiser.R

class FeedSeriesRouterImpl(private val navigatorHolder: NavigatorHolder) : FeedSeriesRouter, FragmentRouting {

	override val fragmentManager get() = navigatorHolder.fragmentManager ?: throw RuntimeException()

	override fun openSeries(seriesId: String) = forwardTo(SeriesDetailMatrixFragment.newInstance(seriesId), R.id.content)
}