package com.example.inversetechnobelka.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.inversetechnobelka.HomeFragment
import com.example.inversetechnobelka.R
import com.example.inversetechnobelka.RatingFragment
import com.example.inversetechnobelka.TasksFragment
import com.example.inversetechnobelka.databinding.FragmentBottomNavigationBinding

class BottomNavigationFragment : Fragment() {

    private var binding: FragmentBottomNavigationBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBottomNavigationBinding.inflate(inflater, container, false)
        val view = binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        replaceFragment(HomeFragment())
        binding!!.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId){
                R.id.navbar_home -> replaceFragment(HomeFragment())
                R.id.navbar_rating -> replaceFragment(RatingFragment())
                R.id.navbar_tasks -> replaceFragment(TasksFragment())
                R.id.navbar_events -> replaceFragment(EventsFragment())
                R.id.navbar_profile -> replaceFragment(ProfileFragment())
                else -> {}
            }
            true
        }


    }

    private fun replaceFragment(fragment: Fragment){

        val fragmentManager = parentFragmentManager
        val fragmentTransition = fragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.frame_layout, fragment)
        fragmentTransition.commit()

    }
}