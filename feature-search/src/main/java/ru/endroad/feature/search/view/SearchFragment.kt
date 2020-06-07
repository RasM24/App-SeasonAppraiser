package ru.endroad.feature.search.view

import android.view.Menu
import android.view.MenuInflater
import android.widget.SearchView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.mikepenz.fastadapter.IModelItem
import org.koin.android.viewmodel.ext.android.viewModel
import ru.endroad.camp.extension.list.CampListFragment
import ru.endroad.feature.search.R
import ru.endroad.feature.search.presenter.SearchViewModel
import ru.endroad.shared.serial.entity.Serial

class SearchFragment : CampListFragment() {

	private val viewModel by viewModel<SearchViewModel>()

	override val layout = R.layout.search_fragment

	override fun setupViewComponents() {
		setHasOptionsMenu(true)
		viewModel.serialList.subcribe {
			itemAdapter.set(it.map(::SerialItem))
		}
	}

	override fun onClickItem(item: IModelItem<*, *>): Boolean {
		val seriesId = item.model as? Serial
		seriesId?.id?.let(viewModel::openSerial)
		return true
	}

	override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
		inflater.inflate(R.menu.search_menu, menu)

		val myActionMenuItem = menu.findItem(R.id.menu_search_view)
		val searchView = myActionMenuItem.actionView as SearchView
		viewModel.set(searchView)

		searchView.onActionViewExpanded()
		searchView.queryHint = "Фильм"
		searchView.requestFocus()
		return super.onCreateOptionsMenu(menu, inflater)
	}

	private inline fun LiveData<List<Serial>>.subcribe(crossinline handler: (List<Serial>) -> Unit) =
		observe(viewLifecycleOwner, Observer { handler(it) })
}