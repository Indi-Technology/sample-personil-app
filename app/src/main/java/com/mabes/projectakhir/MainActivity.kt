package com.mabes.projectakhir

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.mabes.projectakhir.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.listUserRv.setHasFixedSize(true)

        enqueueApi()
    }

    @SuppressLint("LongLogTag")
    private fun showRecyclerView(dataItem: List<DataItem>){
        viewBinding.listUserRv.layoutManager = LinearLayoutManager(
            this@MainActivity)

        val adapter = ListPersonilAdapter(dataItem)

        viewBinding.listUserRv.adapter = adapter

    }

    private fun enqueueApi(){
        val client = ApiConfig.getApiService().getUserList()
        client.enqueue(object : Callback<ListPersonilResponse>{
            override fun onResponse(
                call: Call<ListPersonilResponse>,
                response: Response<ListPersonilResponse>
            ) {
                if (response.isSuccessful){
                      if (response.body() != null){
                        showRecyclerView(response!!.body()!!.data)
                      }
                } else {
                    Log.e("ON RESPONSE FAILURE",
                        "onResponse: ${response.message()}"
                    )
                }
            }

            override fun onFailure(call: Call<ListPersonilResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}