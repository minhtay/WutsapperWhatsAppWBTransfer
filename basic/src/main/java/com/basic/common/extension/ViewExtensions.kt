package com.basic.common.extension

import android.animation.ValueAnimator
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.os.Build
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.animation.ArgbEvaluatorCompat
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.RelativeCornerSize
import com.google.android.material.shape.RoundedCornerTreatment

inline fun <reified T : RecyclerView.ViewHolder> RecyclerView.forEachVisibleHolder(
    action: (T) -> Unit
) {
    for (i in 0 until childCount) {
        action(getChildViewHolder(getChildAt(i)) as T)
    }
}

fun View.setBackgroundTint(color: Int) {
    // API 21 doesn't support this
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP_MR1) {
        background?.setColorFilter(color, PorterDuff.Mode.SRC_IN)
    }

    backgroundTintList = ColorStateList.valueOf(color)
}

fun View.setPaddingHorizontal(padding: Int) {
    setPadding(padding, paddingTop, padding, paddingBottom)
}

fun View.setPaddingVertical(padding: Int) {
    setPadding(paddingLeft, padding, paddingRight, padding)
}

fun TextView.setTextSizeDimens(dimenRes: Int) {
    setTextSize(TypedValue.COMPLEX_UNIT_PX, resources.getDimension(dimenRes))
}

fun View.addShadow(colorRes: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        outlineAmbientShadowColor = context.getColorCompat(colorRes)
        outlineSpotShadowColor = context.getColorCompat(colorRes)
    }
}

fun View.animateBackground(endColor: Int) {
    clearAnimation()
    val valueAnimator = ValueAnimator.ofObject(ArgbEvaluatorCompat(), backgroundTintList?.defaultColor, endColor)
    valueAnimator.duration = 300L
    valueAnimator.interpolator = DecelerateInterpolator(1.5f)
    valueAnimator.addUpdateListener { animation: ValueAnimator ->
        backgroundTintList = ColorStateList.valueOf(animation.animatedValue as Int)
    }
    valueAnimator.start()
}

fun BottomAppBar.setRoundedCorners() {
    val babBackgroundDrawable = background as MaterialShapeDrawable
    babBackgroundDrawable.shapeAppearanceModel = babBackgroundDrawable.shapeAppearanceModel
        .toBuilder()
        .setAllCorners(RoundedCornerTreatment())
        .setAllCornerSizes(RelativeCornerSize(0.5F))
        .build()
}

fun RecyclerView.setLinearManager(context: Context, isVertical: Boolean, stackFromEnd: Boolean = false) {
    val manager = LinearLayoutManager(
        context,
        if (isVertical) LinearLayoutManager.VERTICAL else LinearLayoutManager.HORIZONTAL,
        false
    )
    manager.stackFromEnd = stackFromEnd
    layoutManager = manager
}

fun RelativeLayout.setMargins(start: Int, top: Int, end: Int, bottom: Int) {
    val params: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(
        RelativeLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
    )
    params.setMargins(start, top, end, bottom)
    layoutParams = params
    requestLayout()
}

fun RecyclerView.Adapter<*>.autoScrollToStart(recyclerView: RecyclerView) {
    registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            if (itemCount == 0) return
            recyclerView.scrollToPositionSmooth(0)
        }

        override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
            if (itemCount == 0) return
            onItemRangeInserted(positionStart, itemCount)
        }
    })
}

fun EditText.showKeyboard() {
    requestFocus()
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

fun EditText.hideKeyboard() {
    requestFocus()
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun RecyclerView.scrollToPositionSmooth(int: Int) {
    this.layoutManager?.startSmoothScroll(this.context.getSmoothScroll().apply {
        targetPosition = int
    })
}

fun ImageView.setTint(color: Int) {
    imageTintList = ColorStateList.valueOf(color)
}