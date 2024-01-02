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
import com.rums.clickable_textview.utils.getClickableList
import com.rums.clickable_textview.utils.toast


class WithClickableSpanActivity : AppCompatActivity() {

    private lateinit var mContext: Context

    private lateinit var tvOverview: TextView
    private lateinit var tvOverviewReadMoreHide: TextView
    private lateinit var tvDescription: TextView
    private lateinit var tvDescriptionReadMoreHide: TextView

    private var longString:String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_with_clickable_span)

        initComponents()
    }

    private fun initComponents() {
        mContext = this
        longString = getString(R.string.demo_long_text)

        tvOverview = findViewById(R.id.tvOverview)
        tvOverviewReadMoreHide = findViewById(R.id.tvOverviewReadMoreHide)
        tvDescription = findViewById(R.id.tvDescription)
        tvDescriptionReadMoreHide = findViewById(R.id.tvDescriptionReadMoreHide)

        checkLineCountAndSetVisibility()
        setSpannableClickToOverView(longString, getClickableList())
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

    private fun setSpannableClickToOverView(
        stringContent: String?,
        clickableList: ArrayList<ClickContentInfo?>
    ) {
        if (stringContent == null) {
            return
        }
        val ss = SpannableString(stringContent)

        for (clickableObj in clickableList) {
            if (clickableObj == null) {
                break
            }
            if (clickableObj.word == null) {
                break
            }
            if (stringContent.contains(clickableObj.word)) {
                val wordToFind = clickableObj.word
                val indexStart = stringContent.indexOf(wordToFind)
                val indexEnd = indexStart + wordToFind.length

                val clickableSpan: ClickableSpan = object : ClickableSpan() {
                    override fun onClick(textView: View) {
                        toast(clickableObj.message)
                    }
                }
                ss.setSpan(clickableSpan, indexStart, indexEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
        }
        tvOverview.text = ss
    }
}