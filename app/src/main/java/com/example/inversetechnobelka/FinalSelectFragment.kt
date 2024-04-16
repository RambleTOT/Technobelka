package com.example.inversetechnobelka

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.inversetechnobelka.databinding.FragmentFacultySelectionBinding
import com.example.inversetechnobelka.databinding.FragmentFinalSelectBinding

class FinalSelectFragment : Fragment() {

    private var binding: FragmentFinalSelectBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFinalSelectBinding.inflate(inflater, container, false)
        val view = binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        val bundle = this.arguments
        val id = bundle!!.getInt("id")
        Log.d("MyLog", id.toString())
    }

}