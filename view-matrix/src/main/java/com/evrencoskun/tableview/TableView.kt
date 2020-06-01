package com.evrencoskun.tableview

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.evrencoskun.tableview.adapter.TableAdapter
import com.evrencoskun.tableview.listener.ColumnScrollListener
import com.evrencoskun.tableview.listener.HorizontalScrollTableListener
import com.evrencoskun.tableview.listener.VerticalScrollTableListener
import com.evrencoskun.tableview.listener.RowScrollListener

class TableView : FrameLayout {

	private val rowHeaderWidth = resources.getDimension(R.dimen.cell_width).toInt()
	private val columnHeaderHeight = resources.getDimension(R.dimen.cell_height).toInt()

	private val columnHeaderLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
	private val rowHeaderLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
	private val cellLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

	private val columnHeaderRecyclerView by lazy { createColumnHeaderRecyclerView() }
	private val rowHeaderRecyclerView by lazy { createRowHeaderRecyclerView() }
	private val cellRecyclerView by lazy { createCellRecyclerView() }

	private val rowScrollListener by lazy { RowScrollListener(::onScrollRow) }
	private fun verticalScrollTableListener() = VerticalScrollTableListener(::onVerticalScrollTable)
	private val columnScrollListener by lazy { ColumnScrollListener(::onScrollColumn) }
	private val horizontalScrollTableListener by lazy{ HorizontalScrollTableListener(::onHorizontalScrollTable) }

	private val verticalItemDecoration by lazy { context.createItemDecoration(DividerItemDecoration.VERTICAL) }
	private val horizontalItemDecoration by lazy { context.createItemDecoration(DividerItemDecoration.HORIZONTAL) }

	val adapter by lazy {
		TableAdapter(
			context,
			verticalItemDecoration,
			::verticalScrollTableListener,
			rowHeaderLayoutManager::getFirstVisibleColumnNumber,
			rowHeaderLayoutManager::getColumnPositionOffset
		)
	}

	constructor(context: Context) : this(context, null)
	constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
	constructor(context: Context, attrs: AttributeSet?, @AttrRes defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
		initialize()

		columnHeaderRecyclerView.adapter = adapter.columnHeaderRecyclerViewAdapter
		rowHeaderRecyclerView.adapter = adapter.rowHeaderRecyclerViewAdapter
		cellRecyclerView.adapter = adapter.cellRecyclerViewAdapter
	}

	private fun initialize() {
		// Add Views
		addView(columnHeaderRecyclerView)
		addView(rowHeaderRecyclerView)
		addView(cellRecyclerView)

		rowHeaderRecyclerView.addOnScrollListener(rowScrollListener)
		cellRecyclerView.addOnScrollListener(horizontalScrollTableListener)
		columnHeaderRecyclerView.addOnScrollListener(columnScrollListener)
	}

	private fun createColumnHeaderRecyclerView() = RecyclerView(context).apply {
		setHasFixedSize(true)
		layoutManager = columnHeaderLayoutManager
		layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
			.apply { leftMargin = rowHeaderWidth }
		horizontalItemDecoration.let(this::addItemDecoration)
	}

	private fun createRowHeaderRecyclerView() = RecyclerView(context).apply {
		setHasFixedSize(true)
		layoutManager = rowHeaderLayoutManager
		layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
			.apply { topMargin = columnHeaderHeight }
		verticalItemDecoration.let(this::addItemDecoration)
	}

	private fun createCellRecyclerView() = RecyclerView(context).apply {
		setHasFixedSize(true)
		isMotionEventSplittingEnabled = false
		layoutManager = cellLayoutManager
		layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
			leftMargin = rowHeaderWidth
			topMargin = columnHeaderHeight
		}
		horizontalItemDecoration.let(this::addItemDecoration)
	}

	private fun onScrollRow(deltaY: Int) {
		cellLayoutManager.foreachRecyclerView { it.scrollBy(0, deltaY) }
	}

	private fun onScrollColumn(deltaX: Int) {
		cellRecyclerView.scrollBy(deltaX, 0)
	}

	private fun onVerticalScrollTable(scrolledView: RecyclerView, deltaY: Int) {
		rowHeaderRecyclerView.scrollBy(0, deltaY)

		cellLayoutManager.foreachRecyclerView { if (it !== scrolledView) it.scrollBy(0, deltaY) }
	}

	private fun onHorizontalScrollTable(deltaX: Int) {
		columnHeaderRecyclerView.scrollBy(deltaX, 0)
	}
}