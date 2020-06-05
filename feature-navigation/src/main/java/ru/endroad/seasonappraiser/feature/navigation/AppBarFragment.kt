package ru.endroad.seasonappraiser.feature.navigation

import android.content.Context.INPUT_METHOD_SERVICE
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.appbar_fragment.*
import ru.endroad.camp.fragment.CampFragment
import ru.endroad.feture_navigation.R

class AppBarFragment : CampFragment() {

	override val layout = R.layout.appbar_fragment

	override fun setupViewComponents() {
		(activity as AppCompatActivity).run {
			setSupportActionBar(toolbar)
			toolbar.setNavigationOnClickListener { activity?.onBackPressed() }
			supportFragmentManager.addOnBackStackChangedListener {
				hideSoftKeyboard()
				supportActionBar?.setHomeEnabled()
			}
			supportActionBar?.setHomeEnabled()
		}
	}

	private fun ActionBar.setHomeEnabled() {
		this.setDisplayHomeAsUpEnabled(requireFragmentManager().backStackEntryCount != 0)
	}

	private fun AppCompatActivity.hideSoftKeyboard() {
		val inputMethodManager = (getSystemService(INPUT_METHOD_SERVICE) as? InputMethodManager) ?: return
		currentFocus?.run { inputMethodManager.hideSoftInputFromWindow(windowToken, 0) }
	}
}