package com.wondershare.wutsapper.transfer.feature.backup

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.wondershare.wutsapper.transfer.BR
import com.wondershare.wutsapper.transfer.R
import com.wondershare.wutsapper.transfer.databinding.ActivityCoutryPhoneBinding
import com.wondershare.wutsapper.transfer.feature.backup.adapter.CoutryPhoneAdapterRCV
import com.wondershare.wutsapper.transfer.feature.backup.model.CoutryPhoneDataRCV
import com.wondershare.wutsapper.transfer.feature.base.BaseActivity
import com.wondershare.wutsapper.transfer.feature.base.BaseViewmodel
import com.wondershare.wutsapper.transfer.utils.Utils
import org.json.JSONArray
import java.io.IOException
import java.nio.charset.Charset

class CoutryPhoneActivity : BaseActivity<ActivityCoutryPhoneBinding, BaseViewmodel<*>>() {

    private lateinit var binding: ActivityCoutryPhoneBinding

    override val bindingVariable: Int
        get() = BR.viewmodel
    override val layoutId: Int
        get() = R.layout.activity_coutry_phone
    override val mViewmodel: BackupViewmodel
        get() = ViewModelProvider(this)[BackupViewmodel::class.java]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = viewDataBinding!!
        initView()
        actionView()

    }

    private fun actionView() {
        binding.toolbar.btnBack.setOnClickListener {
            onBackPressed()
        }
        binding.edtSearchCoutry.addTextChangedListener { textChange ->
            if (textChange != null) {
                val data = ArrayList<CoutryPhoneDataRCV>()
                if (textChange.isNotEmpty()) {
                    val data1 = Utils.loadJSONFromAsset(this)!!.filter {
                        it.country.startsWith(textChange.toString(),true)
                    } as ArrayList<CoutryPhoneDataRCV>
                    val data2 = Utils.loadJSONFromAsset(this)!!.filter {
                        it.code.startsWith(textChange.toString(),true)
                    } as ArrayList<CoutryPhoneDataRCV>
                    data.addAll(data1)
                    data.addAll(data2)
                    Log.d("TAG", "actionView: ")
                    val adapter = CoutryPhoneAdapterRCV(data) { data ->
                        changeCoutryPhone(data)
                    }
                    binding.rcvCoutryPhone.adapter = adapter
                    adapter.notifyDataSetChanged()
                } else {
                    val adapter = CoutryPhoneAdapterRCV(Utils.loadJSONFromAsset(this) as ArrayList<CoutryPhoneDataRCV>) { data ->
                        changeCoutryPhone(data)
                    }
                    binding.rcvCoutryPhone.adapter = adapter
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun initView() {
        binding.toolbar.txtToolbar.text = resources.getString(R.string.country_code)
        gone(binding.toolbar.btnToolbar)

        binding.rcvCoutryPhone.layoutManager =
            LinearLayoutManager(this@CoutryPhoneActivity, LinearLayoutManager.VERTICAL, false)
        val adapter = CoutryPhoneAdapterRCV(Utils.loadJSONFromAsset(this) as ArrayList<CoutryPhoneDataRCV>) { data ->
            changeCoutryPhone(data)
        }
        binding.rcvCoutryPhone.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun changeCoutryPhone(data: CoutryPhoneDataRCV) {
        if (data != null) {
            val bundle = Bundle()
            bundle.putSerializable("results", data)
            intent.putExtras(bundle)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }



    override fun onBackPressed() {
        super.onBackPressed()
    }

}