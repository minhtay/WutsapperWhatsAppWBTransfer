package com.wondershare.wutsapper.transfer.feature.backup.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.wondershare.wutsapper.transfer.BR
import com.wondershare.wutsapper.transfer.R
import com.wondershare.wutsapper.transfer.databinding.ItemStep2BackupBinding
import com.wondershare.wutsapper.transfer.feature.backup.BackupActivity

import com.wondershare.wutsapper.transfer.feature.backup.model.Step2DataRCV

class Step2AdapterRCV( private val arrayList: ArrayList<Step2DataRCV>) :
    RecyclerView.Adapter<Step2AdapterRCV.Viewholder>() {


    class Viewholder(val binding: ItemStep2BackupBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Step2DataRCV) {
            binding.setVariable(BR.data, data)
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val binding: ItemStep2BackupBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_step2_backup,
            parent,
            false
        )
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.bind(arrayList[position])
        holder.binding.root.setOnClickListener {
            holder.binding.checkbox.isChecked = !holder.binding.checkbox.isChecked
        }
    }


    override fun getItemCount(): Int {
        return arrayList.size
    }

}