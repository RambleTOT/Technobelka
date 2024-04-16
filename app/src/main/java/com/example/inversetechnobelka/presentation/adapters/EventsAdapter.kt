package com.example.inversetechnobelka.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.RecyclerView
import com.example.inversetechnobelka.data.model.GetEventsResponse
import com.example.inversetechnobelka.databinding.ItemEventsBinding
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.Locale

class EventsAdapter (
    private val sectionsList: List<GetEventsResponse>
): RecyclerView.Adapter<EventsAdapter.ViewHolder>() {

    var onItemClick : ((GetEventsResponse) -> Unit)? = null
    private lateinit var context: Context

    class ViewHolder(val binding: ItemEventsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            ItemEventsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return sectionsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = sectionsList[position]
        holder.binding.apply {
            nameEvents.text = currentItem.name
            dateLevel.text = currentItem.level.name
            userCount.text = currentItem.prize.toString()
            dateUser.text = parseDate(currentItem.expireAt.toString())
            Picasso.with(context).load(currentItem.link).into(imageEvents);
            holder.itemView.setOnClickListener{
                onItemClick?.invoke(currentItem)
            }
        }
    }

    fun parseDate(inputDate: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("d MMMM", Locale("ru"))

        val date = inputFormat.parse(inputDate)
        return outputFormat.format(date!!)
    }

}