package com.example.inversetechnobelka.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.inversetechnobelka.R
import com.example.inversetechnobelka.data.model.GetEventsResponse
import com.example.inversetechnobelka.databinding.FragmentEventsBinding
import com.example.inversetechnobelka.presentation.adapters.EventsAdapter
import com.example.inversetechnobelka.presentation.managers.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventsFragment : Fragment() {

    private var binding: FragmentEventsBinding? = null
    private lateinit var eventsAdapter: EventsAdapter
    private lateinit var eventsList: List<GetEventsResponse>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEventsBinding.inflate(inflater, container, false)
        val view = binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        getEvents()
    }

    private fun init() {
        eventsList = listOf()
    }

    private fun getEvents(){
        RetrofitHelper().getApi().getEvents().enqueue(object :
            Callback<List<GetEventsResponse>> {

            override fun onResponse(
                call: Call<List<GetEventsResponse>>,
                response: Response<List<GetEventsResponse>>
            ) {
                if (response.isSuccessful){
                    Log.d("MyLog", response.body().toString())
                    eventsList = response.body()!!
                    binding!!.recyclerEvents.apply {
                        eventsAdapter = EventsAdapter(eventsList)
                        eventsAdapter.onItemClick = {
                            val transaction = requireActivity().supportFragmentManager.beginTransaction()
                            val currentEventsFragment = CurrentEventsFragment()
                            val bundle = Bundle()
                            bundle.putInt("id", it.id!!)
                            currentEventsFragment.arguments = bundle
                            transaction.replace(R.id.layout_fragment, currentEventsFragment)
                            transaction.disallowAddToBackStack()
                            transaction.commit()
                        }
                        adapter = eventsAdapter
                        layoutManager = LinearLayoutManager(requireActivity());

                    }
                }
                binding!!.layoutVisible.visibility = View.VISIBLE
                binding!!.progressLogin.visibility = View.GONE
            }

            override fun onFailure(call: Call<List<GetEventsResponse>>, t: Throwable) {
                Log.d("MyLog", t.message.toString())
                Toast.makeText(activity, "Возникла ошибка, проверьте подключение", Toast.LENGTH_SHORT).show()
            }

        })
    }

}