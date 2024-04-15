package com.example.inversetechnobelka.presentation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.inversetechnobelka.R
import com.example.inversetechnobelka.data.model.GetHousesResponse
import com.example.inversetechnobelka.databinding.FragmentFacultySelectionBinding
import com.example.inversetechnobelka.databinding.FragmentLoginBinding
import com.example.inversetechnobelka.presentation.adapters.HousesAdapter
import com.example.inversetechnobelka.presentation.managers.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FacultySelectionFragment : Fragment() {

    private var binding: FragmentFacultySelectionBinding? = null
    private lateinit var newsAdapter: HousesAdapter
    private lateinit var sectionsList: List<GetHousesResponse>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFacultySelectionBinding.inflate(inflater, container, false)
        val view = binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        getSections()
    }

    private fun init(){
        sectionsList = listOf()
    }

    private fun getSections(){
        RetrofitHelper().getApi().getHouses().enqueue(object :
            Callback<List<GetHousesResponse>> {

            override fun onResponse(
                call: Call<List<GetHousesResponse>>,
                response: Response<List<GetHousesResponse>>
            ) {
                if (response.isSuccessful){
                    Log.d("MyLog", response.body().toString())
                    sectionsList = response.body()!!
                    binding!!.recyclerHouses.apply {
                        newsAdapter = HousesAdapter(sectionsList)
                        adapter = newsAdapter
                        layoutManager = LinearLayoutManager(requireActivity());

                    }
                }
                Log.d("MyLog", response.toString())
            }

            override fun onFailure(call: Call<List<GetHousesResponse>>, t: Throwable) {
                Log.d("MyLog", t.message.toString())
                Toast.makeText(activity, "Возникла ошибка, проверьте подключение", Toast.LENGTH_SHORT).show()
            }

        })
    }

}