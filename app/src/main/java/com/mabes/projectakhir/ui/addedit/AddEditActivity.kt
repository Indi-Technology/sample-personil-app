package com.mabes.projectakhir.ui.addedit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.activity.viewModels
import com.mabes.projectakhir.DataRank
import com.mabes.projectakhir.data.response.DataStatus

import com.mabes.projectakhir.data.response.retrofit.ApiConfig
import com.mabes.projectakhir.databinding.ActivityAddEditBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddEditActivity : AppCompatActivity() {
    private lateinit var addEditBinding:ActivityAddEditBinding
    private val addEditViewModel : AddEditViewModel by viewModels()

    private val listIdRank=ArrayList<Int>()
    private val listNameRank=ArrayList<String>()

    private val listIdStatus=ArrayList<Int>()
    private val listNameStatus=ArrayList<String>()

    private var rankId = 0
    private var statusId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addEditBinding = ActivityAddEditBinding.inflate(layoutInflater)
        setContentView(addEditBinding.root)

        addEditViewModel.listRank.observe(this){
            showRank(it)
        }

        addEditViewModel.listStatus.observe(this){
            showStatus(it)
        }



    }

    private fun showRank(data:List<DataRank>){
        data.forEach({
            listIdRank.add(it.id)
            listNameRank.add(it.name)
        })
        val arrayAdapter = ArrayAdapter(this,
            com.google.android.material.R.layout.support_simple_spinner_dropdown_item, listNameRank)
        val actRanks:AutoCompleteTextView = addEditBinding.actPangkat
        actRanks.setAdapter(arrayAdapter)
    }


    private fun showStatus(data:List<DataStatus>){
        data.forEach({
            listIdStatus.add(it.id)
            listNameStatus.add(it.name)
        })
        val arrayAdapter = ArrayAdapter(this,
            com.google.android.material.R.layout.support_simple_spinner_dropdown_item, listNameStatus)
        val actStatuses:AutoCompleteTextView = addEditBinding.actStatus
        actStatuses.setAdapter(arrayAdapter)
    }


}