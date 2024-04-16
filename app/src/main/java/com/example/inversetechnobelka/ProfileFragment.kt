package com.example.inversetechnobelka

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.inversetechnobelka.data.model.GetMyAccountResponse
import com.example.inversetechnobelka.databinding.FragmentFacultySelectionBinding
import com.example.inversetechnobelka.databinding.FragmentProfileBinding
import com.example.inversetechnobelka.presentation.managers.RetrofitHelper
import com.example.inversetechnobelka.presentation.managers.TokenManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProfileFragment : Fragment() {

    private var binding: FragmentProfileBinding? = null
    private lateinit var tokenManager: TokenManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        getData()
    }

    private fun init(){
        tokenManager = TokenManager(requireActivity())
    }

    private fun getData(){
        RetrofitHelper().getApi().getMyAccount("Token ${tokenManager.getToken()}").enqueue(object :
            Callback<GetMyAccountResponse> {
            override fun onResponse(
                call: Call<GetMyAccountResponse>,
                response: Response<GetMyAccountResponse>
            ) {
                if (response.isSuccessful){
                    val body = response.body()
                    binding!!.nameUser.text = "${body!!.firstname} ${body.lastname}"
                    binding!!.userCount.text = body.points.toString()
                    binding!!.facultyUser.text = body.house!!.name
                    binding!!.layoutVisible.visibility = View.VISIBLE
                    binding!!.progressLogin.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<GetMyAccountResponse>, t: Throwable) {
                Log.d("MyLog", t.message.toString())
                Toast.makeText(activity, "Возникла ошибка, проверьте подключение", Toast.LENGTH_SHORT).show()
            }

        })
    }

}