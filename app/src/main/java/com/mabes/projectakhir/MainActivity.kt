package com.mabes.projectakhir

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.mabes.projectakhir.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        viewBinding.listUserRv.setHasFixedSize(true)

        showRecyclerView()
    }

    private fun showRecyclerView(dataItem: List<DataItem>){
        viewBinding.listUserRv.layoutManager = LinearLayoutManager(this@MainActivity)
        val adapter = RecyclerViewAdapter(dataItem)
        viewBinding.listUserRv.adapter = adapter
    }
}