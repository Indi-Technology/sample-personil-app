package com.mabes.projectakhir.ui.main

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.mabes.projectakhir.data.remote.response.DataUser
import com.mabes.projectakhir.R
import com.mabes.projectakhir.databinding.ItemUserLayoutBinding

class ListUsersAdapter: RecyclerView.Adapter<ListUsersAdapter.ViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback
    private val listItem = ArrayList<DataUser>()

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setListUser(items: List<DataUser>) {
        listItem.clear()
        listItem.addAll(items)
        notifyDataSetChanged()
    }

    class ViewHolder(var binding: ItemUserLayoutBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUserLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userData = listItem[position]

        Glide.with(holder.binding.root)
            .load(userData.image)
            .listener(object: RequestListener<Drawable> {
                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    holder.binding.pbLoading.visibility = View.GONE
                    return false
                }

                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    holder.binding.pbLoading.visibility = View.GONE
                    return false
                }
            })
            .placeholder(R.drawable.ic_baseline_account_circle_24)
            .into(holder.binding.imgItemUser)

        holder.binding.tvItemName.setText(userData.name)
        holder.binding.tvItemNRP.setText("NRP. ${userData.nrp}")
        holder.binding.tvItemRank.setText(userData.rank)
        holder.binding.root.setOnClickListener { onItemClickCallback.onItemClicked(listItem[holder.adapterPosition]) }

    }

    override fun getItemCount(): Int = listItem.size

    interface OnItemClickCallback {
        fun onItemClicked(userData: DataUser)
    }
}
