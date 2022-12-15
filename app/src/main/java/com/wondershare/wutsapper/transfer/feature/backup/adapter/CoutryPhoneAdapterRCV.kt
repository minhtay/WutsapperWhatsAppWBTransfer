package com.wondershare.wutsapper.transfer.feature.backup.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.wondershare.wutsapper.transfer.BR
import com.wondershare.wutsapper.transfer.R
import com.wondershare.wutsapper.transfer.databinding.ItemCoutryPhoneBinding
import com.wondershare.wutsapper.transfer.feature.backup.model.CoutryPhoneDataRCV

class CoutryPhoneAdapterRCV(
    private val arrayList: ArrayList<CoutryPhoneDataRCV>,
    private val callBack: (CoutryPhoneDataRCV) -> Unit
) :
    RecyclerView.Adapter<CoutryPhoneAdapterRCV.Viewholder>() {
    class Viewholder(val binding: ItemCoutryPhoneBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: CoutryPhoneDataRCV) {
            binding.setVariable(BR.data, data)
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val binding: ItemCoutryPhoneBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_coutry_phone,
            parent,
            false
        )
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.bind(arrayList[position])
        holder.binding.root.setOnClickListener {
            callBack.invoke(arrayList[position])
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}