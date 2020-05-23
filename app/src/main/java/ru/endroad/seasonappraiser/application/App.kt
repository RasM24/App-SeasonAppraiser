package ru.endroad.seasonappraiser.application

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import ru.endroad.config.feature.di.configFeatureModule
import ru.endroad.feature.detail.di.seriesDetailFeatureModule
import ru.endroad.feature.feed.di.feedFeatureModule
import ru.endroad.seasonappraiser.routing.routingModule
import ru.endroad.server.guru.guruServerModule
import ru.endroad.shared.serial.di.sharedSerialModule

class App : Application() {

	override fun onCreate() {
		super.onCreate()

		startKoin {
			androidContext(this@App)
			loadKoinModules(
				listOf(
					guruServerModule,
					configFeatureModule,
					sharedSerialModule,
					routingModule,
					feedFeatureModule,
					seriesDetailFeatureModule
				)
			)
		}
	}
}