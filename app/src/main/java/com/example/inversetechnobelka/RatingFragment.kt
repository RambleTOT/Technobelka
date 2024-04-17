package com.example.inversetechnobelka

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.inversetechnobelka.databinding.FragmentHomeBinding
import com.example.inversetechnobelka.databinding.FragmentRatingBinding


class RatingFragment : Fragment() {

    private var binding: FragmentRatingBinding? = null
    private var currentClickLayout = 1

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
    }

    private fun init(){
        binding!!.clickLayoutOne.setOnClickListener {
            if (currentClickLayout == 2){
                binding!!.clickLayoutOne.setBackgroundResource(R.drawable.background_tapbar_rating)
                binding!!.clickTextOne.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(requireActivity(), R.color.white)))
                binding!!.clickLayoutTwo.setBackgroundColor(Color.TRANSPARENT)
                binding!!.clickTextTwo.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(requireActivity(), R.color.color_text_title)))
                binding!!.layoutFaculty.visibility = View.VISIBLE
                binding!!.layoutPersonal.visibility = View.GONE
                currentClickLayout = 1
            }
        }
        binding!!.clickLayoutTwo.setOnClickListener {
            if (currentClickLayout == 1) {
                binding!!.clickLayoutTwo.setBackgroundResource(R.drawable.background_tapbar_rating)
                binding!!.clickTextTwo.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(requireActivity(), R.color.white)))
                binding!!.clickLayoutOne.setBackgroundColor(Color.TRANSPARENT)
                binding!!.clickTextOne.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(requireActivity(), R.color.color_text_title)))
                binding!!.layoutFaculty.visibility = View.GONE
                binding!!.layoutPersonal.visibility = View.VISIBLE
                currentClickLayout = 2
            }
        }
    }

}