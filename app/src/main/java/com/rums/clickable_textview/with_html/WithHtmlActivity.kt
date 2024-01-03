package com.rums.clickable_textview.with_html

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.rums.clickable_textview.R
import com.rums.clickable_textview.utils.MAX_SEE_MORE_LINES
import com.rums.clickable_textview.utils.TEST_HTML_STRING
import com.rums.clickable_textview.utils.setHtmlStringToTextView
import com.rums.clickable_textview.utils.setUnderLined
import com.rums.clickable_textview.utils.toast


class WithHtmlActivity : AppCompatActivity() {

    private lateinit var mContext: Context

    private lateinit var tvOverview: TextView
    private lateinit var tvOverviewReadMoreHide: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_with_clickable_span)

        initComponents()
    }

    private fun initComponents() {
        mContext = this
        tvOverview = findViewById(R.id.tvOverview)
        tvOverviewReadMoreHide = findViewById(R.id.tvOverviewReadMoreHide)

        setTextAndHandleHyperlinkClick(TEST_HTML_STRING, tvOverview, tvOverviewReadMoreHide)
    }

    private fun setTextAndHandleHyperlinkClick(
        text: String?,
        textView: TextView,
        textViewSeeMoreHide: TextView
    ) {
        setHtmlStringToTextView(text, textView)
        textViewSeeMoreHide.setUnderLined()
        textView.movementMethod = object : TextViewLinkHandler() {
            override fun onLinkClick(url: String?) {
                toast(url)
            }
        }

        handleSeeMoreOnTvOverview(text, textView, textViewSeeMoreHide)
    }

    private fun handleSeeMoreOnTvOverview(
        text: String?,
        textView: TextView,
        textViewSeeMoreHide: TextView
    ) {
        textView.post {
            if (textView.lineCount > MAX_SEE_MORE_LINES) {
                var isContentShorten: Boolean
                textViewSeeMoreHide.visibility = View.VISIBLE

                val lineEndIndex: Int = textView.layout.getLineEnd(MAX_SEE_MORE_LINES - 1)
                val trimmedText: String = text?.subSequence(0, lineEndIndex).toString()

                setHtmlStringToTextView(trimmedText, textView)
                isContentShorten = true
                textViewSeeMoreHide.setOnClickListener {
                    if (isContentShorten) {
                        setHtmlStringToTextView(text, textView)
                        isContentShorten = false
                        textViewSeeMoreHide.text = getString(R.string.see_less)
                    } else {
                        setHtmlStringToTextView(trimmedText, textView)
                        isContentShorten = true
                        textViewSeeMoreHide.text = getString(R.string.see_more)
                    }
                }
            } else {
                textViewSeeMoreHide.visibility = View.GONE
                setHtmlStringToTextView(text, textView)
            }
        }
    }
}