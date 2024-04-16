package com.example.inversetechnobelka.presentation.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.inversetechnobelka.R
import com.example.inversetechnobelka.data.model.GetCurrentEventsResponse
import com.example.inversetechnobelka.databinding.FragmentCurrentEventsBinding
import com.example.inversetechnobelka.presentation.managers.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Locale

class CurrentEventsFragment : Fragment() {

    private var binding: FragmentCurrentEventsBinding? = null
    private lateinit var linkButton: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCurrentEventsBinding.inflate(inflater, container, false)
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
        binding!!.buttonBack.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.layout_fragment, BottomNavigationFragment())
            transaction.disallowAddToBackStack()
            transaction.commit()
        }
        binding!!.buttonLink.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(linkButton))
            startActivity(intent)
        }
        getDataAboutEvents(id)
    }

    private fun getDataAboutEvents(id: Int){
        RetrofitHelper().getApi().getCurrentEvents(id).enqueue(
            object : Callback<GetCurrentEventsResponse> {
                override fun onResponse(
                    call: Call<GetCurrentEventsResponse>,
                    response: Response<GetCurrentEventsResponse>
                ) {
                    if (response.isSuccessful){
                        val body = response.body()
                        binding!!.dateEvents.text = parseDate(body!!.expireAt.toString())
                        binding!!.levelEvents.text = body.level!!.name
                        binding!!.userCount.text = body.prize.toString()
                        linkButton = body.link.toString()
                        binding!!.nameEvents.text = body.name
                        binding!!.levelDescription.text = body.description

                        binding!!.layoutVisible.visibility = View.VISIBLE
                        binding!!.progress.visibility = View.GONE
                    }
                }

                override fun onFailure(call: Call<GetCurrentEventsResponse>, t: Throwable) {
                    Log.d("MyLog", t.message.toString())
                    Toast.makeText(activity, "Возникла ошибка, проверьте подключение", Toast.LENGTH_SHORT).show()
                }

            })
    }

    fun parseDate(inputDate: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("d MMMM", Locale("ru"))

        val date = inputFormat.parse(inputDate)
        return outputFormat.format(date!!)
    }

}