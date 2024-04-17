package com.example.inversetechnobelka.presentation.fragments

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.inversetechnobelka.R
import com.example.inversetechnobelka.data.model.GetAllTasksResponse
import com.example.inversetechnobelka.databinding.FragmentRatingBinding
import com.example.inversetechnobelka.presentation.adapters.AllTasksAdapter
import com.example.inversetechnobelka.presentation.managers.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RatingFragment : Fragment() {

    private var binding: FragmentRatingBinding? = null
    private var currentClickLayout = 1
    private lateinit var allTasksAdapter: AllTasksAdapter
    private lateinit var allTasksList: List<GetAllTasksResponse>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRatingBinding.inflate(inflater, container, false)
        val view = binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        getAllTasks()
    }

    private fun init(){
        binding!!.clickLayoutOne.setOnClickListener {
            if (currentClickLayout == 2){
                binding!!.clickLayoutOne.setBackgroundResource(R.drawable.background_tapbar_rating)
                binding!!.clickTextOne.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(requireActivity(),
                    R.color.white
                )))
                binding!!.clickLayoutTwo.setBackgroundColor(Color.TRANSPARENT)
                binding!!.clickTextTwo.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(requireActivity(),
                    R.color.color_text_title
                )))
                binding!!.layoutFaculty.visibility = View.VISIBLE
                binding!!.layoutPersonal.visibility = View.GONE
                currentClickLayout = 1
            }
        }
        binding!!.clickLayoutTwo.setOnClickListener {
            if (currentClickLayout == 1) {
                binding!!.clickLayoutTwo.setBackgroundResource(R.drawable.background_tapbar_rating)
                binding!!.clickTextTwo.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(requireActivity(),
                    R.color.white
                )))
                binding!!.clickLayoutOne.setBackgroundColor(Color.TRANSPARENT)
                binding!!.clickTextOne.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(requireActivity(),
                    R.color.color_text_title
                )))
                binding!!.layoutFaculty.visibility = View.GONE
                binding!!.layoutPersonal.visibility = View.VISIBLE
                currentClickLayout = 2
            }
        }
    }

    private fun getAllTasks(){
        RetrofitHelper().getApi().getAllTasks().enqueue(object :
            Callback<List<GetAllTasksResponse>> {

            override fun onResponse(
                call: Call<List<GetAllTasksResponse>>,
                response: Response<List<GetAllTasksResponse>>
            ) {
                if (response.isSuccessful){
                    allTasksList = response.body()!!
                    binding!!.recyclerTasks.apply {
                        allTasksAdapter = AllTasksAdapter(allTasksList)
                        allTasksAdapter.onItemClick = {
//                            val transaction = requireActivity().supportFragmentManager.beginTransaction()
//                            val currentSectionsFragment = CurrentSectionsFragment()
//                            val bundle = Bundle()
//                            bundle.putInt("id", it.id!!)
//                            currentSectionsFragment.arguments = bundle
//                            transaction.replace(R.id.linear_fragment, currentSectionsFragment)
//                            transaction.disallowAddToBackStack()
//                            transaction.commit()
                        }
                        adapter = allTasksAdapter
                        layoutManager = GridLayoutManager(requireActivity(), 2)
                    }
                }
            }

            override fun onFailure(call: Call<List<GetAllTasksResponse>>, t: Throwable) {
                Log.d("MyLog", t.message.toString())
                Toast.makeText(activity, "Возникла ошибка, проверьте подключение", Toast.LENGTH_SHORT).show()            }

        })
    }


}