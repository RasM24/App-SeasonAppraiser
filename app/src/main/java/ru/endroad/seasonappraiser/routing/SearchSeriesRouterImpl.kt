package ru.endroad.seasonappraiser.routing

import ru.endroad.feature.detail.view.SeriesDetailMatrixFragment
import ru.endroad.feature.search.router.SearchSeriesRouter
import ru.endroad.navigation.routing.FragmentRouting
import ru.endroad.navigation.routing.forwardTo
import ru.endroad.seasonappraiser.R

class SearchSeriesRouterImpl(private val navigatorHolder: NavigatorHolder) : SearchSeriesRouter, FragmentRouting {

	override val fragmentManager get() = navigatorHolder.fragmentManager ?: throw RuntimeException()

	override fun openSeries(seriesId: String) = forwardTo(SeriesDetailMatrixFragment.newInstance(seriesId), R.id.root)
}