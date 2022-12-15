package com.wondershare.wutsapper.transfer.feature.guide_backup

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.wondershare.wutsapper.transfer.R
import java.util.*


class GuideViewpaperAdapter(val context :Context, private val arraylist: ArrayList<ItemGuideData>):
    PagerAdapter() {
    override fun getCount(): Int {
        return arraylist.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as LinearLayout
    }

    @SuppressLint("ResourceAsColor")
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val mLayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = mLayoutInflater.inflate(R.layout.item_guide_backup,container,false)
        val img = view.findViewById<ImageView>(R.id.img_item_guide)
        img.setImageResource(arraylist[position].img)
        Objects.requireNonNull(container).addView(view);
        return view

    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }

}