package com.example.inversetechnobelka.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.RecyclerView
import com.example.inversetechnobelka.data.model.GetAllTasksResponse
import com.example.inversetechnobelka.databinding.ItemSectionBinding
import com.example.inversetechnobelka.databinding.ItemTaskBinding
import java.text.SimpleDateFormat
import java.util.Locale

class TasksAdapter (
    private val sectionsList: List<GetAllTasksResponse>
): RecyclerView.Adapter<TasksAdapter.ViewHolder>() {

    var onItemClick : ((GetAllTasksResponse) -> Unit)? = null

    class ViewHolder(val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return sectionsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = sectionsList[position]
        holder.binding.apply {
            taskCount.text = currentItem.prize.toString()
            name.text = currentItem.title
            date.text = parseDate(currentItem.expireAt.toString())
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