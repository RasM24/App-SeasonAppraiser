package ru.endroad.feature.search.view

import android.view.Menu
import android.view.MenuInflater
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.coroutineScope
import com.mikepenz.fastadapter.IModelItem
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel
import ru.endroad.camp.extension.list.CampListFragment
import ru.endroad.feature.search.R
import ru.endroad.feature.search.extension.textChangeFlow
import ru.endroad.feature.search.presenter.SearchScreenPresenter
import ru.endroad.feature.search.presenter.SearchViewModel
import ru.endroad.feature.search.state.ModelEvent
import ru.endroad.feature.search.state.ScreenState
import ru.endroad.shared.serial.entity.Serial

class SearchFragment : CampListFragment() {

	override val layout = R.layout.search_fragment

	private val presenter: SearchScreenPresenter by viewModel<SearchViewModel>()

	override fun setupViewComponents() {
		setHasOptionsMenu(true)
		presenter.state.observe(viewLifecycleOwner, Observer { render(it) })
	}

	private fun render(screenState: ScreenState) {
		when (screenState) {
			ScreenState.Loading -> TODO()
			is ScreenState.Data -> itemAdapter.set(screenState.serialList.map(::SerialItem))
			ScreenState.EmptyData -> TODO()
			is ScreenState.Error -> TODO()
		}
	}

	override fun onClickItem(item: IModelItem<*, *>): Boolean {
		(item.model as? Serial)?.id
			?.let(ModelEvent::ClickOnSerial)
			?.let(presenter::reduce)

		return true
	}

	override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
		inflater.inflate(R.menu.search_menu, menu)

		(menu.findItem(R.id.menu_search_view)?.actionView as? SearchView)?.let { view ->
			lifecycle.coroutineScope.launch {
				view.textChangeFlow
					.filter { it.length > 1 }
					.debounce(100)
					.collect { it.let(ModelEvent::Search).let(presenter::reduce) }
			}

			view.onActionViewExpanded()
			view.queryHint = "Фильм"
			view.requestFocus()
		}

		return super.onCreateOptionsMenu(menu, inflater)
	}
}