package com.mabes.projectakhir.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mabes.projectakhir.DataUser
import com.mabes.projectakhir.R
import com.mabes.projectakhir.databinding.ItemUserLayoutBinding

class ListUserAdapter(private val listPersonil : List<DataUser>)
    :RecyclerView.Adapter<ListUserAdapter.ViewHolder>() {

    class ViewHolder(var binding: ItemUserLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : ViewHolder {
        val binding = ItemUserLayoutBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listPersonil[position]

        holder.binding.tvItemName.text = data.name
        Glide.with(holder.binding.root)
            .load(data.image)
            .placeholder(R.drawable.ic_baseline_account_circle_24)
            .into(holder.binding.imgItemUser)
        holder.binding.tvItemRank.text = data.rank
        holder.binding.tvItemNRP.text = data.nrp
    }

    override fun getItemCount(): Int = listPersonil.size

    interface OnItemClickCallback {
        fun OnItemClicked(userData: DataUser)
    }

    private lateinit var onItemClickCallback: OnItemClickCallback
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    private val listItem = ArrayList<DataUser>()
    fun setListUser(items : List<DataUser>){
        listItem.clear()
        listItem.addAll(items)
        notifyDataSetChanged()
}
    
    
    
    
}