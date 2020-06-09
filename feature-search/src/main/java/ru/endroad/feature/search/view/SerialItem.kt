package ru.endroad.feature.search.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mikepenz.fastadapter.items.ModelAbstractItem
import com.squareup.picasso.Picasso
import ru.endroad.feature.search.R
import ru.endroad.shared.serial.entity.Serial

//TODO Добавить к дизайну рейтинга звездочку
class SerialItem(serial: Serial) : ModelAbstractItem<Serial, SerialItem.ViewHolder>(serial) {

	override val type = R.id.serial_item

	override val layoutRes = R.layout.search_serial_item

	override fun getViewHolder(v: View) = ViewHolder(v)

	override fun bindView(holder: ViewHolder, payloads: List<Any>) {
		super.bindView(holder, payloads)
		holder.name.text = model.name
		holder.rating.text = String.format("%.2f",model.rating)
		Picasso.get().load(model.posterUrl).centerCrop().fit().into(holder.poster)
	}

	class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
		val name: TextView = view.findViewById(R.id.name)
		val rating: TextView = view.findViewById(R.id.rating)
		val poster: ImageView = view.findViewById(R.id.poster)
	}
}