package com.example.inversetechnobelka.presentation.fragments

import GetTokenResponse
import UserLoginEntity
import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Color.parseColor
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.inversetechnobelka.R
import com.example.inversetechnobelka.databinding.FragmentLoginBinding
import com.example.inversetechnobelka.presentation.managers.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : Fragment() {

    private var binding: FragmentLoginBinding? = null
    private var isEmptyLogin = false
    private var isEmptyPassword = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initEditText()
        init()
    }

    private fun init(){
//        tokenManager = TokenManager(requireActivity())
//        firstEntryManager = FirstEntryManager(requireActivity())
        binding!!.buttonLogin.setOnClickListener {
            binding!!.buttonLogin.visibility = View.INVISIBLE
            binding!!.progressLogin.visibility = View.VISIBLE
            login(binding!!.editTextLogin.text.toString(),
                binding!!.editTextPassword.text.toString()
            )
        }
    }

    private fun initEditText(){
        binding!!.editTextPassword.setOnClickListener {
            binding!!.textErrorLogin.visibility = View.GONE
        }

        binding!!.editTextLogin.setOnClickListener {
            binding!!.textErrorLogin.visibility = View.GONE
        }

        binding!!.editTextLogin.addTextChangedListener(object : TextWatcher {
            // when there is no text added
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                binding!!.textErrorLogin.visibility = View.GONE
            }

            override fun afterTextChanged(s: Editable) {
                if (s.toString().trim().isEmpty()) {
                    isEmptyLogin = false
                    blockButton()
                } else {
                    isEmptyLogin = true
                    blockButton()
                }
            }
        })

        binding!!.editTextPassword.addTextChangedListener(object : TextWatcher {
            // when there is no text added
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                binding!!.textErrorLogin.visibility = View.GONE
            }

            override fun afterTextChanged(s: Editable) {
                if (s.toString().trim().isEmpty()) {
                    isEmptyPassword = false
                    blockButton()
                }else {
                    isEmptyPassword = true
                    blockButton()
                }
            }
        })
    }

    @SuppressLint("ResourceAsColor")
    private fun blockButton(){
        if (isEmptyLogin && isEmptyPassword){
            binding!!.buttonLogin.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireActivity(), R.color.color_background_button))
            binding!!.buttonLogin.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(requireActivity(), R.color.white)))
            binding!!.buttonLogin.isEnabled = true
        }else if (!isEmptyLogin || !isEmptyPassword){
            binding!!.buttonLogin.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireActivity(), R.color.color_block_button))
            binding!!.buttonLogin.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(requireActivity(), R.color.color_block_button_text)))
            binding!!.buttonLogin.isEnabled = false
        }
    }

    private fun login(login: String, password: String){
        RetrofitHelper().getApi().loginUser(UserLoginEntity(login, password)).enqueue(object :
            Callback<GetTokenResponse> {
            override fun onResponse(
                call: Call<GetTokenResponse>,
                response: Response<GetTokenResponse>
            ) {
                if (response.isSuccessful) {
//                    tokenManager.saveToken(response.body()!!.token.toString())
//                    firstEntryManager.saveFirstEntry(true)
                    binding!!.progressLogin.visibility = View.INVISIBLE
                    Toast.makeText(activity, "Успех", Toast.LENGTH_SHORT).show()
                    val transaction = activity!!.supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.layout_fragment, FacultySelectionFragment())
                    transaction.disallowAddToBackStack()
                    transaction.commit()
                }else{
                    binding!!.textErrorLogin.visibility = View.VISIBLE
                    binding!!.buttonLogin.visibility = View.VISIBLE
                    binding!!.progressLogin.visibility = View.INVISIBLE
                }
            }

            override fun onFailure(call: Call<GetTokenResponse>, t: Throwable) {
                Log.d("MyLog", t.message.toString())
                Toast.makeText(activity, "Возникла ошибка, проверьте подключение", Toast.LENGTH_SHORT).show()
                binding!!.buttonLogin.visibility = View.VISIBLE
                binding!!.progressLogin.visibility = View.INVISIBLE
            }

        })
    }

}