package ru.endroad.seasonappraiser.application

import android.os.Bundle
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.module
import ru.endroad.camp.activity.CampActivity
import ru.endroad.feature.feed.view.FeedFragment
import ru.endroad.navigation.changeRoot
import ru.endroad.seasonappraiser.R

class SingleActivity : CampActivity() {
	override val layout = R.layout.base_layout

	private val supportFragmentManagerModule = module { factory { supportFragmentManager } }

	override fun onCreate(savedInstanceState: Bundle?) {
		loadKoinModules(supportFragmentManagerModule)
		super.onCreate(savedInstanceState)
	}

	override fun onStop() {
		unloadKoinModules(supportFragmentManagerModule)
		super.onStop()
	}

	override fun onFirstCreate() = supportFragmentManager.changeRoot(FeedFragment(), R.id.root)
}