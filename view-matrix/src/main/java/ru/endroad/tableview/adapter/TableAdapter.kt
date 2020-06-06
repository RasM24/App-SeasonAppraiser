package ru.endroad.tableview.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import ru.endroad.tableview.adapter.recyclerview.CellRecyclerViewAdapter
import ru.endroad.tableview.adapter.recyclerview.ColumnHeaderRecyclerViewAdapter
import ru.endroad.tableview.adapter.recyclerview.RowHeaderRecyclerViewAdapter
import ru.endroad.tableview.listener.VerticalScrollTableListener
import ru.endroad.shared.serial.entity.Episode

class TableAdapter(
	context: Context,
	verticalItemDecoration: ItemDecoration?,
	getVerticalScrollTableListener: () -> VerticalScrollTableListener,
	getFirstVisibleColumnNumber: () -> (Int),
	getColumnPositionOffset: () -> Int
) {

	val columnHeaderRecyclerViewAdapter = ColumnHeaderRecyclerViewAdapter()
	val rowHeaderRecyclerViewAdapter = RowHeaderRecyclerViewAdapter()

	val cellRecyclerViewAdapter = CellRecyclerViewAdapter(
		context,
		verticalItemDecoration,
		getVerticalScrollTableListener,
		getFirstVisibleColumnNumber,
		getColumnPositionOffset
	)

	//TODO костыли
	fun setAllItems(columnHeaderItems: List<Int>, rowHeaderItems: List<Int>, cellItems: List<List<Episode?>>) {
		columnHeaderRecyclerViewAdapter.setItems(columnHeaderItems)
		rowHeaderRecyclerViewAdapter.setItems(rowHeaderItems)
		cellRecyclerViewAdapter.setItems(cellItems)
	}
}