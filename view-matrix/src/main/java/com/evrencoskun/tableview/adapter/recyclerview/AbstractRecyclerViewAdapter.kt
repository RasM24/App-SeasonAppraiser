package com.evrencoskun.tableview.adapter.recyclerview

import androidx.recyclerview.widget.RecyclerView

abstract class AbstractRecyclerViewAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

	private var items: MutableList<T> = mutableListOf()

	override fun getItemCount() = items.size

	override fun getItemViewType(position: Int): Int = 0

	fun getItem(position: Int): T = items[position]

	fun setItems(itemList: List<T>) {
		items.clear()
		items.addAll(itemList)
		notifyDataSetChanged()
	}

	fun getItems(): List<T> = items
}