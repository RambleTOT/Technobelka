package com.example.inversetechnobelka.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.RecyclerView
import com.example.inversetechnobelka.data.model.GetAllTasksResponse
import com.example.inversetechnobelka.databinding.ItemSectionBinding

class AllTasksAdapter (
    private val sectionsList: List<GetAllTasksResponse>
): RecyclerView.Adapter<AllTasksAdapter.ViewHolder>() {

    var onItemClick : ((GetAllTasksResponse) -> Unit)? = null

    class ViewHolder(val binding: ItemSectionBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemSectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return sectionsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = sectionsList[position]
        holder.binding.apply {
            textSectionsCategory.text = currentItem.type!!.name
            taskCount.text = currentItem.prize.toString()
            name.text = currentItem.title
            holder.itemView.setOnClickListener{
                onItemClick?.invoke(currentItem)
            }
        }
    }

}