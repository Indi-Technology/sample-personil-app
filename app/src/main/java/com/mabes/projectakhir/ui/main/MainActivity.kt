package com.mabes.projectakhir.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager

import com.mabes.projectakhir.DataUser

import com.mabes.projectakhir.databinding.ActivityMainBinding
import com.mabes.projectakhir.ui.addedit.AddEditActivity


class MainActivity : AppCompatActivity() {
    private lateinit var mainActivityBinding: ActivityMainBinding
    private lateinit var listUserAdapter: ListUserAdapter
    private lateinit var btnSearch:ImageButton
    private val mainViewModel:MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivityBinding.root)

        listUserAdapter = ListUserAdapter()
        mainViewModel.getList("")
        showRecyclerView()

        mainViewModel.listUser.observe(this){
            setUserData(it)
        }

        mainViewModel.isLoading.observe(this){
            showLoading(it)
        }

        mainActivityBinding.addFab.setOnClickListener{
            val intentToAddEditActivity = Intent(this, AddEditActivity::class.java)
            startActivity(intentToAddEditActivity)
        }

    }

    private fun showRecyclerView(){
        mainActivityBinding.rvUser.layoutManager = LinearLayoutManager(
            this@MainActivity)

    }

    private fun setUserData(itemUser:List<DataUser>){
        listUserAdapter.setListUser(itemUser)
        mainActivityBinding.rvUser.adapter =listUserAdapter
    }

    private fun showLoading(isLoading:Boolean){
        mainActivityBinding.loadRv.visibility=
            if (isLoading) View.VISIBLE else View.GONE
    }


}