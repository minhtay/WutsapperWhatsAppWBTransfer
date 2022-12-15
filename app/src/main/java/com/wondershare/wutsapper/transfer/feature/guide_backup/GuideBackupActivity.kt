package com.wondershare.wutsapper.transfer.feature.guide_backup

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import androidx.core.text.bold
import androidx.core.text.color
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.basic.common.extension.getColorCompat
import com.basic.data.Preferences
import com.wondershare.wutsapper.transfer.BR
import com.wondershare.wutsapper.transfer.R
import com.wondershare.wutsapper.transfer.databinding.ActivityGuideBackupBinding
import com.wondershare.wutsapper.transfer.feature.base.BaseActivity
import com.wondershare.wutsapper.transfer.feature.home.HomeActivity
import com.wondershare.wutsapper.transfer.feature.user.UserActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

class GuideBackupActivity :
    BaseActivity<ActivityGuideBackupBinding, GuideBackupViewmodel>() {

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, GuideBackupActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)

        }
    }

    private lateinit var binding: ActivityGuideBackupBinding
    private lateinit var viewmodel: GuideBackupViewmodel
    private lateinit var guideViewpaperAdapter: GuideViewpaperAdapter
    private var page: Int = 0

    override val bindingVariable: Int
        get() = BR.viewmodel
    override val layoutId: Int
        get() = R.layout.activity_guide_backup
    override val mViewmodel: GuideBackupViewmodel
        get() = ViewModelProvider(this)[GuideBackupViewmodel::class.java]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewDataBinding!!
        viewmodel = mViewmodel
        setupView()
        actionView()
    }

    private fun actionView() {
        binding.toolbar.txtToolbar.setText(R.string.text_toolbar_guide_backup)


        viewmodel.page.observe(this) {
            binding.txtStep.text = spannable(it)
        }

        binding.toolbar.btnBack.setOnClickListener {
            onBackPressed()
        }

        binding.toolbar.btnToolbar.setOnClickListener {
            UserActivity.startActivity(this)
        }
    }

    private fun setupView() {
        guideViewpaperAdapter = GuideViewpaperAdapter(this, arraylistItemGuide())
        binding.fragmentView.adapter = guideViewpaperAdapter
        binding.fragmentView.clipToPadding = false
        binding.fragmentView.offscreenPageLimit = 2
        val _50sdp = resources.getDimension(com.intuit.sdp.R.dimen._50sdp)
        binding.fragmentView.setPadding(0, 0, _50sdp.toInt(), 0)
        page = binding.fragmentView.currentItem

        binding.fragmentView.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageSelected(position: Int) {
                viewmodel.page.postValue(position)
            }

        })

        binding.dotsIndicator.attachTo(binding.fragmentView)
    }

    private fun arraylistItemGuide(): ArrayList<ItemGuideData> {
        val array = ArrayList<ItemGuideData>()
        array.add(ItemGuideData("Tap “Menu” ", R.drawable.step1))
        array.add(ItemGuideData("Tap “Settings” ", R.drawable.step2))
        array.add(ItemGuideData("Tap “Chats” ", R.drawable.step3))
        array.add(ItemGuideData("Tap “Chat backup” ", R.drawable.step4))
        array.add(ItemGuideData("Tap “Back up” and wait to complete ", R.drawable.step5))
        return array
    }

    private fun spannable(page: Int): Spannable {
        val arrayList = arraylistItemGuide()
        return SpannableStringBuilder()
            .color(getColorCompat(R.color.color_2156DF)) { bold { append("Step ${page + 1}: ") } }
            .append(arrayList[page].textStep)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }


}