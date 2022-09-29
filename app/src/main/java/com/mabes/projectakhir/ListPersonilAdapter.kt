package com.mabes.projectakhir

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mabes.projectakhir.databinding.ItemRecyclerviewBinding

class ListPersonilAdapter(private val listPersonil : List<DataItem>)
    :RecyclerView.Adapter<ListPersonilAdapter.ViewHolder>() {

    class ViewHolder(var binding:ItemRecyclerviewBinding)
        :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
    : ViewHolder {
        val binding = ItemRecyclerviewBinding
            .inflate(LayoutInflater.from(parent.context),
                parent,false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listPersonil[position]

        holder.binding.itemNameTv.text = data.name
        Glide.with(holder.binding.root)
            .load(data.image)
            .placeholder(R.drawable.ic_baseline_account_circle_24)
            .into(holder.binding.itemImageIv)
        holder.binding.itemPangkatTv.text = data.rank
        holder.binding.itemNrpTv.text = data.nrp
    }

    override fun getItemCount(): Int = listPersonil.size
}