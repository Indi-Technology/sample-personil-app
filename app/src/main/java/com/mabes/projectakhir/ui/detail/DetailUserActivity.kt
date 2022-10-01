package com.mabes.projectakhir.ui.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import com.mabes.projectakhir.R
import com.mabes.projectakhir.databinding.ActivityDetailUserBinding
import com.mabes.projectakhir.ui.addedit.AddEditActivity
import com.mabes.projectakhir.ui.main.MainActivity

class DetailUserActivity : AppCompatActivity() {
    private lateinit var detailActivityBinding: ActivityDetailUserBinding
    private val detailUserViewModel: DetailUserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        detailActivityBinding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(detailActivityBinding.root)

        supportActionBar?.hide()

        val idUser = intent.getIntExtra(MainActivity.RV_ITEM_ID, 0)
        Log.d("Detail DataUser ID: ", idUser.toString())
        Toast.makeText(this, idUser.toString(), Toast.LENGTH_SHORT).show()
        detailUserViewModel.getUserById(idUser)

        detailUserViewModel.userData.observe(this) { data ->
            Log.d("DataUser Observe: ", data.toString())
            detailActivityBinding.apply {
                detailNameTv.text = data?.name ?: "Empty data"
                detailAlamatTv.text = data?.address ?: "Empty data"
                detailTglLahirTv.text = data?.bornDate ?: "Empty data"
                detailTmptLahirTv.text = data?.bornPlace ?: "Empty data"
                detailStatusTv.text = data?.status ?: "Empty data"
                detailJabatanTv.text = data?.rank ?: "Empty data"

                Glide.with(root)
                    .load(data?.image)
                    .centerCrop()
                    .placeholder(R.drawable.ic_baseline_account_circle_24)
                    .into(detailIv)

            }

            detailActivityBinding.detailEditBtn.setOnClickListener {
                val intentToDetailActivity = Intent(this, AddEditActivity::class.java)
                intentToDetailActivity.putExtra(DETAIL_DATA_ISEDIT, true)
                intentToDetailActivity.putExtra(DETAIL_DATA_PREF, data)
                startActivity(intentToDetailActivity)
            }

            detailActivityBinding.detailDeleteBtn.setOnClickListener {
                val id = data?.id

                AlertDialog.Builder(this).setTitle("Peringatan!")
                    .setMessage("Apakah anda ingin menghapus user ini ?")
                    .setCancelable(true)
                    .setPositiveButton("Ya"){ _, _ ->
                        Toast.makeText(this, "HAPUS", Toast.LENGTH_SHORT).show()
                        if (id != null) {
                            detailUserViewModel.deleteUserById(id)
                        }
                        detailUserViewModel.responseMessage.observe(this){
                            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                        }
                        finish()
                    }
                    .setNegativeButton("Tidak"){ dialogInterface, _ ->
                        dialogInterface.cancel()
                    }
                    .show()
            }
        }

        detailActivityBinding.btnBack.setOnClickListener {
            onBackPressed()
        }

        detailUserViewModel.isLoading.observe(this){
            showLoading(it)
        }

    }

    override fun onRestart() {
        super.onRestart()
        val idUser = intent.getIntExtra(MainActivity.RV_ITEM_ID, 0)
        detailUserViewModel.getUserById(idUser)
    }

    private fun showLoading(isLoading: Boolean) {
        detailActivityBinding.loading.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    companion object {
        const val DETAIL_DATA_PREF = "DETAIL_DATA_PREF"
        const val DETAIL_DATA_ISEDIT = "DETAIL_DATA_ISEDIT"
    }
}