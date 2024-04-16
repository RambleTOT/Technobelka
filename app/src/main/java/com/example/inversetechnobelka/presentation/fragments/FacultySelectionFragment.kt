package com.example.inversetechnobelka.presentation.fragments

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.inversetechnobelka.R
import com.example.inversetechnobelka.data.model.GetHousesResponse
import com.example.inversetechnobelka.databinding.FragmentFacultySelectionBinding
import com.example.inversetechnobelka.presentation.managers.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FacultySelectionFragment : Fragment() {

    private var binding: FragmentFacultySelectionBinding? = null
    private lateinit var housesList: List<GetHousesResponse>
    private var currentClick = 0

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
        blockButton()
        housesList = listOf()
        binding!!.houseLayout.setOnClickListener {
            if (currentClick != 1){
                binding!!.houseLayout.setBackgroundResource(R.drawable.item_faculty_background_stroke)
                binding!!.layoutVisible.visibility = View.VISIBLE
                removeClick()
                currentClick = 1
            }
            blockButton()
        }
        binding!!.houseLayout2.setOnClickListener {
            if (currentClick != 2) {
                binding!!.houseLayout2.setBackgroundResource(R.drawable.item_faculty_background_stroke)
                binding!!.layoutVisible2.visibility = View.VISIBLE
                removeClick()
                currentClick = 2
            }
            blockButton()
        }
        binding!!.houseLayout3.setOnClickListener {
            if (currentClick != 3){
                binding!!.houseLayout3.setBackgroundResource(R.drawable.item_faculty_background_stroke)
                binding!!.layoutVisible3.visibility = View.VISIBLE
                removeClick()
                currentClick = 3
            }
            blockButton()
        }
        binding!!.houseLayout4.setOnClickListener {
            if (currentClick != 4){
                binding!!.houseLayout4.setBackgroundResource(R.drawable.item_faculty_background_stroke)
                binding!!.layoutVisible4.visibility = View.VISIBLE
                removeClick()
                currentClick = 4
            }
            blockButton()
        }
        binding!!.buttonSelect.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("id", currentClick)
            bundle.putString("name", housesList[currentClick-1].name)
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            val finalSelectFragment = FinalSelectFragment()
            finalSelectFragment.arguments = bundle
            transaction.replace(R.id.layout_fragment, finalSelectFragment)
            transaction.disallowAddToBackStack()
            transaction.commit()
        }
        binding!!.buttonRandom.setOnClickListener {
            val ran = (1..4).random()
            val bundle = Bundle()
            bundle.putInt("id", ran)
            bundle.putString("name", housesList[ran-1].name)
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            val finalSelectFragment = FinalSelectFragment()
            finalSelectFragment.arguments = bundle
            transaction.replace(R.id.layout_fragment, finalSelectFragment)
            transaction.disallowAddToBackStack()
            transaction.commit()
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun blockButton(){
        if (currentClick != 0){
            binding!!.buttonSelect.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireActivity(), R.color.color_orange))
            binding!!.buttonSelect.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(requireActivity(), R.color.white)))
            binding!!.buttonSelect.isEnabled = true
        }else {
            binding!!.buttonSelect.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireActivity(), R.color.color_block_button))
            binding!!.buttonSelect.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(requireActivity(), R.color.color_block_button_text)))
            binding!!.buttonSelect.isEnabled = false
        }
    }

    private fun removeClick(){
        if (currentClick == 1){
            binding!!.houseLayout.setBackgroundResource(R.drawable.item_faculty_background)
            binding!!.layoutVisible.visibility = View.GONE
        }else if (currentClick == 2){
            binding!!.houseLayout2.setBackgroundResource(R.drawable.item_faculty_background)
            binding!!.layoutVisible2.visibility = View.GONE
        }else if (currentClick == 3){
            binding!!.houseLayout3.setBackgroundResource(R.drawable.item_faculty_background)
            binding!!.layoutVisible3.visibility = View.GONE
        }else if (currentClick == 4){
            binding!!.houseLayout4.setBackgroundResource(R.drawable.item_faculty_background)
            binding!!.layoutVisible4.visibility = View.GONE
        }
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
                    housesList = response.body()!!
                    binding!!.name.text = housesList[0].name
                    binding!!.description.text = housesList[0].description
                    binding!!.ourValues.text = housesList[0].ourValues
                    binding!!.userCount.text = "${housesList[0].usersCount} участников"
                    binding!!.name2.text = housesList[1].name
                    binding!!.description2.text = housesList[1].description
                    binding!!.ourValues2.text = housesList[1].ourValues
                    binding!!.userCount2.text = "${housesList[1].usersCount} участников"
                    binding!!.name3.text = housesList[2].name
                    binding!!.description3.text = housesList[2].description
                    binding!!.ourValues3.text = housesList[2].ourValues
                    binding!!.userCount3.text = "${housesList[2].usersCount} участников"
                    binding!!.name4.text = housesList[3].name
                    binding!!.description4.text = housesList[3].description
                    binding!!.ourValues4.text = housesList[3].ourValues
                    binding!!.userCount4.text = "${housesList[3].usersCount} участников"
                    binding!!.mainLayout.visibility = View.VISIBLE
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