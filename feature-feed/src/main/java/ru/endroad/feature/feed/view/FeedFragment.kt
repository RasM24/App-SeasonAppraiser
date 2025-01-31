package ru.endroad.feature.feed.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.IModelItem
import com.mikepenz.fastadapter.adapters.ItemAdapter
import kotlinx.android.synthetic.main.feed_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel
import ru.endroad.camp.extension.hide
import ru.endroad.camp.fragment.CampFragment
import ru.endroad.feature.feed.R
import ru.endroad.feature.feed.presenter.FeedViewModel
import ru.endroad.shared.serial.entity.Serial

class FeedFragment : CampFragment() {

	companion object {
		const val POPULAR_SERIAL_LIST_STATE = "POPULAR_Serial_LIST_STATE"
		const val RECOMMENDED_SERIAL_LIST_STATE = "RECOMMENDED_Serial_LIST_STATE"
		const val BEST_SERIAL_LIST_STATE = "BEST_Serial_LIST_STATE"
	}

	override val layout = R.layout.feed_fragment

	override var toolbarMenu: Int? = R.menu.feed_menu

	private val viewModel by viewModel<FeedViewModel>()

	private var popularSerialList = ItemAdapter<IModelItem<Serial, *>>()
	private var recommendedSerialList = ItemAdapter<IModelItem<Serial, *>>()
	private var bestSerialList = ItemAdapter<IModelItem<Serial, *>>()

	private var popularAdapter = FastAdapter.with(this.popularSerialList)
	private var recommendedAdapter = FastAdapter.with(this.recommendedSerialList)
	private var bestAdapter = FastAdapter.with(this.bestSerialList)

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		savedInstanceState?.let {
			popularAdapter.withSavedInstanceState(it, POPULAR_SERIAL_LIST_STATE)
			recommendedAdapter.withSavedInstanceState(it, RECOMMENDED_SERIAL_LIST_STATE)
			bestAdapter.withSavedInstanceState(it, BEST_SERIAL_LIST_STATE)
		}
	}

	override fun onSaveInstanceState(outState: Bundle) {
		popularAdapter.saveInstanceState(outState, POPULAR_SERIAL_LIST_STATE)
		recommendedAdapter.saveInstanceState(outState, RECOMMENDED_SERIAL_LIST_STATE)
		bestAdapter.saveInstanceState(outState, BEST_SERIAL_LIST_STATE)

		super.onSaveInstanceState(outState)
	}

	override fun setupViewComponents() {
		if (viewModel.configuration.popularSerial) setupPopularSerialList() else list_popular.hide()
		if (viewModel.configuration.recommendedSerial) setupRecommendedSerialList() else list_recommended.hide()
		setupBestSerialList()
	}

	private fun setupPopularSerialList() {
		list_popular.adapter = popularAdapter
		popularAdapter.onClickListener = { _, _, item, _ ->
			viewModel.openSerial(item.model.id)
			true
		}
		viewModel.popularSerialList.subcribe { popularSerialList.set(it.map(::SerialItem)) }
	}

	private fun setupRecommendedSerialList() {
		list_recommended.adapter = recommendedAdapter
		recommendedAdapter.onClickListener = { _, _, item, _ ->
			viewModel.openSerial(item.model.id)
			true
		}
		viewModel.recommendedSerialList.subcribe { recommendedSerialList.set(it.map(::SerialItem)) }
	}

	private fun setupBestSerialList() {
		list_best.adapter = bestAdapter
		bestAdapter.onClickListener = { _, _, item, _ ->
			viewModel.openSerial(item.model.id)
			true
		}
		viewModel.bestSerialList.subcribe { bestSerialList.set(it.map(::SerialItem)) }
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		if (item.itemId == R.id.menu_search) viewModel.openSearchScreen()
		return true
	}

	private inline fun LiveData<List<Serial>>.subcribe(crossinline handler: (List<Serial>) -> Unit) =
		observe(viewLifecycleOwner, Observer { handler(it) })
}