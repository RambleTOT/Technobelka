package com.example.inversetechnobelka.presentation.fragments

import UserLoginPatchEntity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.inversetechnobelka.R
import com.example.inversetechnobelka.data.model.GetUserPathHouseResponse
import com.example.inversetechnobelka.databinding.FragmentFinalSelectBinding
import com.example.inversetechnobelka.presentation.managers.FirstEntryManager
import com.example.inversetechnobelka.presentation.managers.RetrofitHelper
import com.example.inversetechnobelka.presentation.managers.TokenManager
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FinalSelectFragment : Fragment() {

    private var binding: FragmentFinalSelectBinding? = null
    private lateinit var tokenManager: TokenManager
    private lateinit var firstEntryManager: FirstEntryManager

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
        tokenManager = TokenManager(requireActivity())
        firstEntryManager = FirstEntryManager(requireActivity())
        val bundle = this.arguments
        val id = bundle!!.getInt("id")
        val name = bundle!!.getString("name")
        val image = bundle!!.getString("image")
        Picasso.with(requireActivity()).load(image).into(binding!!.image)
        binding!!.name.text = "${name}!"
        binding!!.buttonSelect.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            val facultySelectionFragment = FacultySelectionFragment()
            transaction.replace(R.id.layout_fragment, facultySelectionFragment)
            transaction.disallowAddToBackStack()
            transaction.commit()
        }
        binding!!.buttonNext.setOnClickListener {
            binding!!.buttonNext.visibility = View.INVISIBLE
            binding!!.progressLogin.visibility = View.VISIBLE
            addHouse(id)
        }
    }

    private fun addHouse(houseId: Int){
        Log.d("MyLog", houseId.toString())
        RetrofitHelper().getApi().changeUserHouse(UserLoginPatchEntity(houseId), "Token ${tokenManager.getToken()}").enqueue(object :
            Callback<GetUserPathHouseResponse> {
            override fun onResponse(
                call: Call<GetUserPathHouseResponse>,
                response: Response<GetUserPathHouseResponse>
            ) {
                if (response.isSuccessful) {
                    firstEntryManager.saveFirstEntry(true)
                    binding!!.progressLogin.visibility = View.INVISIBLE
                    val transaction = requireActivity().supportFragmentManager.beginTransaction()
                    val bottomNavigationFragment = BottomNavigationFragment()
                    transaction.replace(R.id.layout_fragment, bottomNavigationFragment)
                    transaction.disallowAddToBackStack()
                    transaction.commit()
                }else{
                    Log.d("MyLog", response.toString())
                    binding!!.buttonNext.visibility = View.VISIBLE
                    binding!!.progressLogin.visibility = View.INVISIBLE
                }
            }

            override fun onFailure(call: Call<GetUserPathHouseResponse>, t: Throwable) {
                Log.d("MyLog", t.message.toString())
                Toast.makeText(activity, "Возникла ошибка, проверьте подключение", Toast.LENGTH_SHORT).show()
                binding!!.buttonNext.visibility = View.VISIBLE
                binding!!.progressLogin.visibility = View.INVISIBLE
            }
        })
    }

}