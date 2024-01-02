package com.rums.clickable_textview.with_clickable_span

import android.content.Context
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.rums.clickable_textview.R
import com.rums.clickable_textview.utils.toast


class WithClickableSpanActivity : AppCompatActivity() {

    private lateinit var mContext: Context

    private lateinit var tvOverview: TextView
    private lateinit var tvOverviewReadMoreHide: TextView
    private lateinit var tvDescription: TextView
    private lateinit var tvDescriptionReadMoreHide: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_with_clickable_span)

        initComponents()
    }

    private fun initComponents() {
        mContext = this

        tvOverview = findViewById(R.id.tvOverview)
        tvOverviewReadMoreHide = findViewById(R.id.tvOverviewReadMoreHide)
        tvDescription = findViewById(R.id.tvDescription)
        tvDescriptionReadMoreHide = findViewById(R.id.tvDescriptionReadMoreHide)

        checkLineCountAndSetVisibility()
        setSpannableClick()
    }


    private fun checkLineCountAndSetVisibility() {
        tvOverview.movementMethod = LinkMovementMethod.getInstance()
        tvOverview.post {
            if (tvOverview.lineCount > 3) {
                tvOverviewReadMoreHide.visibility = View.VISIBLE
            } else {
                tvOverviewReadMoreHide.visibility = View.GONE
            }
        }

        tvDescription.movementMethod = LinkMovementMethod.getInstance()
        tvDescription.post {
            if (tvDescription.lineCount > 3) {
                tvDescriptionReadMoreHide.visibility = View.VISIBLE
            } else {
                tvDescriptionReadMoreHide.visibility = View.GONE
            }
        }
    }

    private fun setSpannableClick() {
        val ss = SpannableString(getString(R.string.demo_long_text))
        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(textView: View) {
                toast("nutrient-rich marine clicked")
            }
        }

        val clickableSpan2: ClickableSpan = object : ClickableSpan() {
            override fun onClick(textView: View) {
                toast("offering habitate clicked")
            }
        }

        ss.setSpan(clickableSpan, 85, 105, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        ss.setSpan(clickableSpan2, 252, 269, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        tvOverview.text = ss
    }
}