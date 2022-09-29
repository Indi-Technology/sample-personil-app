package com.mabes.projectakhir

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mabes.projectakhir.databinding.ItemRecyclerviewBinding

class RecyclerViewAdapter(private val listPersonil : List<DataItem>):RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    class ViewHolder(var binding:ItemRecyclerviewBinding)
        :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecyclerviewBinding
            .inflate(LayoutInflater.from(parent.context),
                parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = listPersonil.size
}