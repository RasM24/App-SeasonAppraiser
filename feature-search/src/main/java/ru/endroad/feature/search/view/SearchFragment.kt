package ru.endroad.feature.search.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.mikepenz.fastadapter.IModelItem
import kotlinx.android.synthetic.main.search_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel
import ru.endroad.camp.extension.list.CampListFragment
import ru.endroad.feature.search.R
import ru.endroad.feature.search.presenter.SearchViewModel
import ru.endroad.shared.serial.entity.Serial

class SearchFragment : CampListFragment() {

	private val viewModel by viewModel<SearchViewModel>()

	override val layout = R.layout.search_fragment

	override fun setupViewComponents() {
		viewModel.serialList.subcribe {
			itemAdapter.set(it.map(::SerialItem))
		}
		viewModel.set(search_input)
	}

	override fun onClickItem(item: IModelItem<*, *>): Boolean {
		val seriesId = item.model as? Serial
		seriesId?.id?.let(viewModel::openSerial)
		return true
	}

	private inline fun LiveData<List<Serial>>.subcribe(crossinline handler: (List<Serial>) -> Unit) =
		observe(viewLifecycleOwner, Observer { handler(it) })
}