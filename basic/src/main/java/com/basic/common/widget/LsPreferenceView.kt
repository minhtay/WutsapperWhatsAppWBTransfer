package com.basic.common.widget

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.isVisible
import com.widget.R
import kotlinx.android.synthetic.main.preference_view.view.*

class LsPreferenceView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayoutCompat(context, attrs) {

    var title: String? = null
        set(value) {
            field = value

            if (isInEditMode) {
                findViewById<TextView>(R.id.titleView).text = value
            } else {
                titleView.text = value
            }
        }

    var summary: String? = null
        set(value) {
            field = value


            if (isInEditMode) {
                findViewById<TextView>(R.id.summaryView).run {
                    text = value

                    isVisible = value?.isNotEmpty() == true
                }
            } else {
                summaryView.text = value
                summaryView.isVisible = value?.isNotEmpty() == true
            }
        }

    init {
        View.inflate(context, R.layout.preference_view, this)

        orientation = HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL

        context.obtainStyledAttributes(attrs, R.styleable.LsPreferenceView).run {
            title = getString(R.styleable.LsPreferenceView_title)
            summary = getString(R.styleable.LsPreferenceView_summary)

            // If there's a custom view used for the preference's widget, inflate it
            getResourceId(R.styleable.LsPreferenceView_widget, -1).takeIf { it != -1 }?.let { id ->
                View.inflate(context, id, widgetFrame)
            }

            // If an icon is being used, set up the icon view
            getResourceId(R.styleable.LsPreferenceView_icon, -1).takeIf { it != -1 }?.let { id ->
                icon.isVisible = true
                icon.setImageResource(id)
            }

            recycle()
        }
    }

}