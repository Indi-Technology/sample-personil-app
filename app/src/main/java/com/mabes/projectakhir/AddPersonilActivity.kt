package com.mabes.projectakhir

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import com.mabes.projectakhir.databinding.ActivityAddPersonilBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddPersonilActivity : AppCompatActivity() {
    private lateinit var binding:ActivityAddPersonilBinding

    private val listIdRank=ArrayList<Int>()
    private val listNameRank=ArrayList<String>()

    private val listIdStatus=ArrayList<Int>()
    private val listNameStatus=ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPersonilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getRank();

    }

    private fun showRank(data:List<DataRank>){
        Toast.makeText(this,"Show Data Text", Toast.LENGTH_LONG).show()
        data.forEach({
            listIdRank.add(it.id)
            listNameRank.add(it.name)
        })
        val arrayAdapter = ArrayAdapter(this,
            com.google.android.material.R.layout.support_simple_spinner_dropdown_item, listNameRank)
        val actRanks:AutoCompleteTextView = binding.actPangkat
        actRanks.setAdapter(arrayAdapter)
    }

    private fun showStatus(data:List<DataStatus>){
        Toast.makeText(this,"Show Data Text", Toast.LENGTH_LONG).show()
        data.forEach({
            listIdStatus.add(it.id)
            listNameStatus.add(it.name)
        })
        val arrayAdapter = ArrayAdapter(this,
            com.google.android.material.R.layout.support_simple_spinner_dropdown_item, listNameStatus)
        val actStatus:AutoCompleteTextView = binding.actStatus
        actStatus.setAdapter(arrayAdapter)
    }

    private fun getRank(){
        val client = ApiConfig.getApiService().getUserRank()
        client.enqueue(object : Callback<RankResponse>{
            override fun onResponse(call: Call<RankResponse>, response: Response<RankResponse>) {
                if(response.isSuccessful){
                    val dataResponse = response.body()?.data
                    Toast.makeText(this@AddPersonilActivity, "Sukses getData",Toast.LENGTH_SHORT).show()
                    Log.e("ON RESPONSE Success",
                        "onResponse: ${response.body()}"
                    )
                    if(dataResponse!=null){
                        showRank(dataResponse)
                    }
                }
            }
            override fun onFailure(call: Call<RankResponse>, t: Throwable) {
                TODO("Not yet implemented")
                Log.e("ON RESPONSE error",
                    "onResponse: }"
                )
            }
        })
    }

    private fun getStatus(){
        val client = ApiConfig.getApiService().getUserStatus()
        client.enqueue(object : Callback<StatusResponse>{
            override fun onResponse(call: Call<StatusResponse>, response: Response<StatusResponse>) {
                if(response.isSuccessful){
                    val dataResponse = response.body()?.data
                    Toast.makeText(this@AddPersonilActivity, "Sukses getData",Toast.LENGTH_SHORT).show()
                    Log.e("ON RESPONSE Success",
                        "onResponse: ${response.body()}"
                    )
                    if(dataResponse!=null){
                        showStatus(dataResponse)
                    }
                }
            }
            override fun onFailure(call: Call<StatusResponse>, t: Throwable) {
                TODO("Not yet implemented")
                Log.e("ON RESPONSE error",
                    "onResponse: }"
                )
            }
        })
    }
}