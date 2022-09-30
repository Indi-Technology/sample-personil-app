package com.mabes.projectakhir.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.mabes.projectakhir.R
import com.mabes.projectakhir.databinding.ActivityDetailUserBinding

class DetailUserActivity : AppCompatActivity() {

    private lateinit var detailUserBinding: ActivityDetailUserBinding
    private val detailUserViewModel:DetailUserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailUserBinding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(detailUserBinding.root)

        val idUser = intent.getIntExtra("ID",0)
        detailUserViewModel.getUserById(idUser)

        detailUserViewModel.userData.observe(this){ data ->
            detailUserBinding.apply {
                detailNameTv.text = data.name ?: "Empty Data"
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
        }

        detailUserViewModel.isLoading.observe(this){
            showLoading(it)
        }

    }

    private fun showLoading(isLoading: Boolean) {
        detailUserBinding.loading.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}