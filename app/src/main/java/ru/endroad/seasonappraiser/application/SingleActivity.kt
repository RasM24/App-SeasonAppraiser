package ru.endroad.seasonappraiser.application

import ru.endroad.camp.activity.CampActivity
import ru.endroad.feature.feed.view.FeedFragment
import ru.endroad.navigation.changeRoot
import ru.endroad.seasonappraiser.R

class SingleActivity : CampActivity() {
	override val layout = R.layout.base_layout

	override fun onFirstCreate() = supportFragmentManager.changeRoot(FeedFragment(), R.id.root)
}