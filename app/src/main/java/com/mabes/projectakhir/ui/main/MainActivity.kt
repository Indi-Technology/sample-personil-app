package com.mabes.projectakhir.ui.main

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.ImageButton
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mabes.projectakhir.ui.addedit.AddEditActivity
import com.mabes.projectakhir.data.remote.response.DataUser
import com.mabes.projectakhir.ui.detail.DetailUserActivity
import com.mabes.projectakhir.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityBinding: ActivityMainBinding
    private lateinit var listUsersAdapter: ListUsersAdapter

    private lateinit var btnSearch: ImageButton

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivityBinding.root)

        listUsersAdapter = ListUsersAdapter()

        btnSearch = mainActivityBinding.btnSearch
        mainViewModel.getList("")

        mainActivityBinding.edtSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(editable: Editable?) {
                mainViewModel.getList(editable.toString())
            }
        })

        btnSearch.setOnClickListener {
            mainViewModel.getList(mainActivityBinding.edtSearch.text.toString())
        }

        showRecyclerView()

        mainViewModel.listUser.observe(this){
            setUserData(it)
        }

        mainViewModel.isLoading.observe(this){
            showLoading(it)
        }

        mainActivityBinding.addFab.setOnClickListener {
            val intentToAddEditActivity = Intent(this, AddEditActivity::class.java)
            startActivity(intentToAddEditActivity)
        }
    }

    override fun onRestart() {
        super.onRestart()
        mainViewModel.getList("")
        showRecyclerView()
    }

    private fun setUserData(itemUser: List<DataUser>) {
        listUsersAdapter.setListUser(itemUser)
        mainActivityBinding.rvUser.adapter = listUsersAdapter
    }

    private fun showRecyclerView(){
        mainActivityBinding.rvUser.setHasFixedSize(true)
        mainActivityBinding.rvUser.layoutManager = LinearLayoutManager(this)

        listUsersAdapter.setOnItemClickCallback(object : ListUsersAdapter.OnItemClickCallback {
            override fun onItemClicked(userData: DataUser) {
                Log.d("Main DataUser ID: ", userData.id.toString())
                val intentToDetail = Intent(this@MainActivity, DetailUserActivity::class.java)
                intentToDetail.putExtra(RV_ITEM_ID,userData.id)
                startActivity(intentToDetail)
            }
        })
    }

    private fun showLoading(isLoading: Boolean) {
        mainActivityBinding.loadRv.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    companion object {
        const val RV_ITEM_ID = "RV_ITEM_ID"
    }
}