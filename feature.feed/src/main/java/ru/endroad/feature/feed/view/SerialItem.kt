package ru.endroad.feature.feed.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mikepenz.fastadapter.items.ModelAbstractItem
import ru.endroad.feature.feed.R
import ru.endroad.shared.serial.entity.Serial

class SerialItem(serial: Serial) : ModelAbstractItem<Serial, SerialItem.ViewHolder>(serial) {

	override val type = R.id.serial_item

	override val layoutRes = R.layout.serial_item

	override fun getViewHolder(v: View) = ViewHolder(v)

	override fun bindView(holder: ViewHolder, payloads: List<Any>) {
		super.bindView(holder, payloads)
		holder.name.text = model.name
		//holder.poster.load()
	}

	class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
		val name: TextView = view.findViewById(R.id.name)
		val poster: ImageView = view.findViewById(R.id.poster)
	}
}