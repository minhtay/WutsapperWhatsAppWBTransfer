package com.wondershare.wutsapper.transfer.feature.backup.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.wondershare.wutsapper.transfer.R
import com.wondershare.wutsapper.transfer.databinding.ItemRcvSelectFileBinding


class SelectFileAdapterRCV(
    private val arrayList: ArrayList<String>
): RecyclerView.Adapter<SelectFileAdapterRCV.Viewholder>() {
    class Viewholder(val binding: ItemRcvSelectFileBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val binding: ItemRcvSelectFileBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_rcv_select_file,
            parent,
            false
        )
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
    }

    override fun getItemCount(): Int {
        return if (arrayList.size>0){
            arrayList.size
        }else 0
    }

}