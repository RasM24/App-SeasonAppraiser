package ru.endroad.seasonappraiser.application

import android.os.Bundle
import org.koin.android.ext.android.inject
import ru.endroad.camp.activity.CampActivity
import ru.endroad.seasonappraiser.feature.navigation.AppBarFragment
import ru.endroad.feature.feed.view.FeedFragment
import ru.endroad.navigation.changeRoot
import ru.endroad.seasonappraiser.R
import ru.endroad.seasonappraiser.routing.NavigatorHolder

class SingleActivity : CampActivity() {

	override val layout = R.layout.base_layout

	private val navigatorHolder by inject<NavigatorHolder>()

	override fun onCreate(savedInstanceState: Bundle?) {
		navigatorHolder.fragmentManager = supportFragmentManager
		super.onCreate(savedInstanceState)
	}

	override fun onDestroy() {
		navigatorHolder.fragmentManager = null
		super.onDestroy()
	}

	override fun onFirstCreate() {
		supportFragmentManager.changeRoot(AppBarFragment(), R.id.root)
		supportFragmentManager.changeRoot(FeedFragment(), R.id.content)
	}
}