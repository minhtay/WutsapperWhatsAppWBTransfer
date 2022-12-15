package com.wondershare.wutsapper.transfer.feature.user.status_saver

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import androidx.core.content.ContextCompat
import androidx.core.text.bold
import androidx.core.text.color
import androidx.lifecycle.ViewModelProvider
import com.basic.common.extension.getColorCompat
import com.wondershare.wutsapper.transfer.BR
import com.wondershare.wutsapper.transfer.R
import com.wondershare.wutsapper.transfer.databinding.ActivityStatusSaverBinding
import com.wondershare.wutsapper.transfer.databinding.ActivityToolkitBinding
import com.wondershare.wutsapper.transfer.feature.base.BaseActivity

class StatusSaverActivity : BaseActivity<ActivityStatusSaverBinding, StatusSaverViewmodel>() {

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, StatusSaverActivity::class.java)
            context.startActivity(intent)
        }
    }

    private lateinit var binding: ActivityStatusSaverBinding
    private lateinit var viewmodel: StatusSaverViewmodel

    override val bindingVariable: Int
        get() = BR.viewmodel
    override val layoutId: Int
        get() = R.layout.activity_status_saver
    override val mViewmodel: StatusSaverViewmodel
        get() = ViewModelProvider(this)[StatusSaverViewmodel::class.java]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewmodel = mViewmodel
        binding = viewDataBinding!!

        initView()
        actionView()

    }

    private fun initView() {
        viewmodel.type.observe(this) {
            when (it) {
                1 -> {
                    binding.image.setCardBackgroundColor(
                        ContextCompat.getColor(
                            this,
                            R.color.color_2156DF
                        )
                    )
                    binding.txtImage.setTextColor(ContextCompat.getColor(this, R.color.white))
                    binding.txtImage.typeface = Typeface.DEFAULT_BOLD
                    binding.video.setCardBackgroundColor(
                        ContextCompat.getColor(
                            this,
                            R.color.color_C9D8FF
                        )
                    )
                    binding.txtVideo.setTextColor(
                        ContextCompat.getColor(
                            this,
                            R.color.color_9497D6
                        )
                    )
                    binding.txtVideo.typeface = Typeface.DEFAULT

                }
                2 -> {
                    binding.video.setCardBackgroundColor(
                        ContextCompat.getColor(
                            this,
                            R.color.color_2156DF
                        )
                    )
                    binding.txtVideo.setTextColor(ContextCompat.getColor(this, R.color.white))
                    binding.txtVideo.typeface = Typeface.DEFAULT_BOLD
                    binding.image.setCardBackgroundColor(
                        ContextCompat.getColor(
                            this,
                            R.color.color_C9D8FF
                        )
                    )
                    binding.txtImage.setTextColor(
                        ContextCompat.getColor(
                            this,
                            R.color.color_9497D6
                        )
                    )
                    binding.txtImage.typeface = Typeface.DEFAULT
                }
            }
        }

        binding.step1.text = spannable(resources.getString(R.string.statusSaver_step1),1)
        binding.step2.text = spannable(resources.getString(R.string.statusSaver_step2),2)
        binding.step3.text = spannable(resources.getString(R.string.statusSaver_step3),3)
    }

    private fun actionView() {
        binding.image.setOnClickListener {
            viewmodel.type.postValue(1)
        }
        binding.video.setOnClickListener {
            viewmodel.type.postValue(2)
        }
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun spannable(string: String,i:Int): Spannable {
        return SpannableStringBuilder()
            .color(getColorCompat(R.color.color_2156DF)) { bold { append("Step $i: ") } }
            .append(string)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}