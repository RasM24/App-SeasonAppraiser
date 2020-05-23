package ru.endroad.seasonappraiser.routing

import org.koin.dsl.module
import org.koin.experimental.builder.singleBy
import ru.endroad.feature.feed.router.FeedSeriesRouter

val routingModule = module {
	singleBy<FeedSeriesRouter, FeedSeriesRouterImpl>()
}