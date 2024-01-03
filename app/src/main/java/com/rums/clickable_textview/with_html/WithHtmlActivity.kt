package com.rums.clickable_textview.with_html

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.rums.clickable_textview.R
import com.rums.clickable_textview.utils.MAX_SEE_MORE_LINES
import com.rums.clickable_textview.utils.setHtmlStringToTextView
import com.rums.clickable_textview.utils.toast


class WithHtmlActivity : AppCompatActivity() {

    private lateinit var mContext: Context

    private lateinit var tvOverview: TextView
    private lateinit var tvOverviewReadMoreHide: TextView

    private var longString: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_with_clickable_span)

        initComponents()
    }

    private fun initComponents() {
        mContext = this
//        longString = getString(R.string.demo_html_text)
        longString =
            "<html>Laminaria is a genus of large brown seaweeds,  Their <a href=\"this is the description of the complex thallus. \">complex thallus.</a> commonly known as kelp, found in cool, nutrient-rich marine environments. <ul>\n" +
                    "    <li>First item</li>\n" +
                    "    <li>Second item</li>\n" +
                    "    <li>Third item</li>\n" +
                    "</ul> structure includes a holdfast, stipe, and blade. These seaweeds serves as vital compound of marine ecosystem, <a href=\"this is the description of the strucutre.\">the structure.</a> offering habitate and sustenance to various marine species. <br> <br> Thanks.</html>"

        tvOverview = findViewById(R.id.tvOverview)
        tvOverviewReadMoreHide = findViewById(R.id.tvOverviewReadMoreHide)


        tvOverview.movementMethod = object : TextViewLinkHandler() {
            override fun onLinkClick(url: String?) {
                toast("clicked data: $url")
            }
        }

        setHtmlStringToTextView(longString, tvOverview)

        handleSeeMore()
    }

    private fun handleSeeMore() {
        tvOverview.post {
            if (tvOverview.lineCount > MAX_SEE_MORE_LINES) {
                var isContentShorten: Boolean
                tvOverviewReadMoreHide.visibility = View.VISIBLE

                val lineEndIndex: Int = tvOverview.layout.getLineEnd(MAX_SEE_MORE_LINES - 1)
                val trimmedText: String = longString?.subSequence(0, lineEndIndex).toString()

                setHtmlStringToTextView(trimmedText, tvOverview)
                isContentShorten = true
                tvOverviewReadMoreHide.setOnClickListener {
                    if (isContentShorten) {
                        setHtmlStringToTextView(longString, tvOverview)
                        isContentShorten = false
                        tvOverviewReadMoreHide.text = getString(R.string.see_less)
                    } else {
                        setHtmlStringToTextView(trimmedText, tvOverview)
                        isContentShorten = true
                        tvOverviewReadMoreHide.text = getString(R.string.see_more)
                    }
                }

            } else {
                tvOverviewReadMoreHide.visibility = View.GONE
                setHtmlStringToTextView(longString, tvOverview)
            }
        }
    }
}