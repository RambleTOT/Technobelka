package com.example.inversetechnobelka.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.RecyclerView
import com.example.inversetechnobelka.data.model.GetEventsResponse
import com.example.inversetechnobelka.databinding.ItemEventsBinding

class EventsAdapter (
    private val sectionsList: List<GetEventsResponse>
): RecyclerView.Adapter<EventsAdapter.ViewHolder>() {

    var onItemClick : ((GetEventsResponse) -> Unit)? = null

    class ViewHolder(val binding: ItemEventsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
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
            holder.itemView.setOnClickListener{
                onItemClick?.invoke(currentItem)
            }
        }
    }

}