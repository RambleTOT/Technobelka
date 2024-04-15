package com.example.inversetechnobelka.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.inversetechnobelka.data.model.GetHousesResponse
import com.example.inversetechnobelka.databinding.ItemFacultyBinding

class HousesAdapter (
    private val newsList: List<GetHousesResponse>
): RecyclerView.Adapter<HousesAdapter.ViewHolder>() {

    var onItemClick : ((GetHousesResponse) -> Unit)? = null

    inner class ViewHolder(val binding: ItemFacultyBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemFacultyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = newsList[position]
        holder.binding.apply {
            name.text = currentItem.name
            description.text = currentItem.description
            ourValues.text = currentItem.ourValues
            userCount.text = "${currentItem.usersCount} участников"
            holder.itemView.setOnClickListener{
                description.text = currentItem.description
                ourValues.text = currentItem.ourValues
                layoutVisible.visibility = View.VISIBLE
            }
        }
    }

}