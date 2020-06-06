package ru.endroad.seasonappraiser.routing

import org.koin.dsl.module
import org.koin.experimental.builder.singleBy
import ru.endroad.feature.feed.router.FeedSeriesRouter
import ru.endroad.feature.search.router.SearchSeriesRouter

val routingModule = module {
	single { NavigatorHolder() }

	singleBy<FeedSeriesRouter, FeedSeriesRouterImpl>()
	singleBy<SearchSeriesRouter, SearchSeriesRouterImpl>()
}