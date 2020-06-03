package ru.endroad.tableview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

//TODO вынести в библиотеку
internal fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View =
	LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)

internal fun LinearLayoutManager.foreachRecyclerView(action: (RecyclerView) -> Unit) {
	for (i in 0 until childCount) (getChildAt(i) as? RecyclerView)?.let(action)
}

internal fun LinearLayoutManager.getFirstVisibleColumnNumber(): Int = findFirstVisibleItemPosition()

internal fun LinearLayoutManager.getColumnPositionOffset(): Int =
	findViewByPosition(getFirstVisibleColumnNumber())?.top ?: 0

internal fun Context.createItemDecoration(orientation: Int) = DividerItemDecoration(this, orientation)
	.apply { getDrawable(R.drawable.cell_line_divider)?.let(::setDrawable) }