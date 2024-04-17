package com.example.inversetechnobelka

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.inversetechnobelka.data.model.GetAllTasksResponse
import com.example.inversetechnobelka.data.model.GetEventsResponse
import com.example.inversetechnobelka.databinding.FragmentRatingBinding
import com.example.inversetechnobelka.databinding.FragmentTasksBinding
import com.example.inversetechnobelka.presentation.adapters.AllTasksAdapter
import com.example.inversetechnobelka.presentation.adapters.EventsAdapter
import com.example.inversetechnobelka.presentation.adapters.TasksAdapter
import com.example.inversetechnobelka.presentation.fragments.CurrentEventsFragment
import com.example.inversetechnobelka.presentation.fragments.CurrentTasksFragment
import com.example.inversetechnobelka.presentation.managers.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TasksFragment : Fragment() {

    private var binding: FragmentTasksBinding? = null
    private lateinit var tasksAdapter: TasksAdapter
    private lateinit var tasksList: List<GetAllTasksResponse>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTasksBinding.inflate(inflater, container, false)
        val view = binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        getTasks()
    }

    private fun init(){
        tasksList = listOf()
    }

    private fun getTasks(){
        RetrofitHelper().getApi().getAllTasks().enqueue(object :
            Callback<List<GetAllTasksResponse>> {

            override fun onResponse(
                call: Call<List<GetAllTasksResponse>>,
                response: Response<List<GetAllTasksResponse>>
            ) {
                if (response.isSuccessful){
                    Log.d("MyLog", response.body().toString())
                    tasksList = response.body()!!
                    binding!!.recyclerTasks.apply {
                        tasksAdapter = TasksAdapter(tasksList)
                        tasksAdapter.onItemClick = {
                            val transaction = requireActivity().supportFragmentManager.beginTransaction()
                            val currentEventsFragment = CurrentEventsFragment()
                            val bundle = Bundle()
                            bundle.putInt("id", it.id!!)
                            currentEventsFragment.arguments = bundle
                            transaction.replace(R.id.layout_fragment, currentEventsFragment)
                            transaction.disallowAddToBackStack()
                            transaction.commit()
                        }
                        adapter = tasksAdapter
                        layoutManager = LinearLayoutManager(requireActivity());

                    }
                }
                binding!!.layoutVisible.visibility = View.VISIBLE
                binding!!.progressLogin.visibility = View.GONE
            }

            override fun onFailure(call: Call<List<GetAllTasksResponse>>, t: Throwable) {
                Log.d("MyLog", t.message.toString())
                Toast.makeText(activity, "Возникла ошибка, проверьте подключение", Toast.LENGTH_SHORT).show()
            }

        })
    }

}