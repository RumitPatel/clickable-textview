package com.rums.clickable_textview.with_clickable_span

import android.content.Context
import android.graphics.Paint
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.rums.clickable_textview.R
import com.rums.clickable_textview.utils.MAX_SEE_MORE_LINES
import com.rums.clickable_textview.utils.getClickableList
import com.rums.clickable_textview.utils.toast


class WithClickableSpanActivity : AppCompatActivity() {

    private lateinit var mContext: Context

    private lateinit var tvOverview: TextView
    private lateinit var tvOverviewReadMoreHide: TextView
    private lateinit var tvDescription: TextView
    private lateinit var tvDescriptionReadMoreHide: TextView

    private var longString: String? = null


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

        setSpannableClickToOverView(longString, getClickableList(), tvOverview)
        setSpannableClickToOverView(longString, getClickableList(), tvDescription)
        checkLineCountAndSetVisibility()

        tvOverviewReadMoreHide.paintFlags =
            tvOverviewReadMoreHide.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        tvDescriptionReadMoreHide.paintFlags =
            tvDescriptionReadMoreHide.paintFlags or Paint.UNDERLINE_TEXT_FLAG
    }


    private fun checkLineCountAndSetVisibility() {
        tvOverview.movementMethod = LinkMovementMethod.getInstance()
        tvOverview.post {
            if (tvOverview.lineCount > MAX_SEE_MORE_LINES) {
                var isContentShorten: Boolean
                tvOverviewReadMoreHide.visibility = View.VISIBLE

                val lineEndIndex: Int = tvOverview.layout.getLineEnd(MAX_SEE_MORE_LINES - 1)
                val trimmedText: String = tvOverview.text.subSequence(0, lineEndIndex).toString()

                setSpannableClickToOverView(trimmedText, getClickableList(), tvOverview)
                isContentShorten = true

                tvOverviewReadMoreHide.setOnClickListener {
                    if (isContentShorten) {
                        setSpannableClickToOverView(longString, getClickableList(), tvOverview)
                        isContentShorten = false
                        tvOverviewReadMoreHide.text = getString(R.string.see_less)
                    } else {
                        setSpannableClickToOverView(trimmedText, getClickableList(), tvOverview)
                        isContentShorten = true
                        tvOverviewReadMoreHide.text = getString(R.string.see_more)
                    }
                }
            } else {
                tvOverviewReadMoreHide.visibility = View.GONE
                setSpannableClickToOverView(longString, getClickableList(), tvOverview)
            }
        }

        tvDescription.movementMethod = LinkMovementMethod.getInstance()
        tvDescription.post {
            if (tvDescription.lineCount > MAX_SEE_MORE_LINES) {
                var isContentShorten: Boolean
                tvDescriptionReadMoreHide.visibility = View.VISIBLE

                val lineEndIndex: Int = tvDescription.layout.getLineEnd(MAX_SEE_MORE_LINES - 1)
                val trimmedText: String = tvDescription.text.subSequence(0, lineEndIndex).toString()

                setSpannableClickToOverView(trimmedText, getClickableList(), tvDescription)
                isContentShorten = true

                tvDescriptionReadMoreHide.setOnClickListener {
                    if (isContentShorten) {
                        setSpannableClickToOverView(longString, getClickableList(), tvDescription)
                        isContentShorten = false
                        tvDescriptionReadMoreHide.text = getString(R.string.see_less)
                    } else {
                        setSpannableClickToOverView(trimmedText, getClickableList(), tvDescription)
                        isContentShorten = true
                        tvDescriptionReadMoreHide.text = getString(R.string.see_more)
                    }
                }
            } else {
                tvDescriptionReadMoreHide.visibility = View.GONE
                setSpannableClickToOverView(longString, getClickableList(), tvDescription)
            }
        }
    }

    private fun setSpannableClickToOverView(
        stringContent: String?,
        clickableList: ArrayList<ClickContentInfo?>,
        textView: TextView
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
        textView.text = ss
    }
}