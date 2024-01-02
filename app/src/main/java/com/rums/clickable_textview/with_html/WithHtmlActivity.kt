package com.rums.clickable_textview.with_html

import android.content.Context
import android.os.Bundle
import android.text.Layout
import android.text.Spannable
import android.text.method.LinkMovementMethod
import android.text.style.URLSpan
import android.view.MotionEvent
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rums.clickable_textview.R
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
            "<html>Hello Test user,<br><br> <a href=\"https://www.google.com\">Click here.</a> <br><br> Thanks.</html>"

        tvOverview = findViewById(R.id.tvOverview)
        tvOverviewReadMoreHide = findViewById(R.id.tvOverviewReadMoreHide)


        tvOverview.movementMethod = object : TextViewLinkHandler() {
            override fun onLinkClick(url: String?) {
                toast("url = $url")
            }
        }

//        tvOverview.movementMethod = LinkMovementMethod.getInstance()
        setHtmlStringToTextView(longString, tvOverview)


    }


    abstract class TextViewLinkHandler : LinkMovementMethod() {
        override fun onTouchEvent(
            widget: TextView,
            buffer: Spannable,
            event: MotionEvent
        ): Boolean {
            if (event.action != MotionEvent.ACTION_UP) return super.onTouchEvent(
                widget,
                buffer,
                event
            )
            var x = event.x.toInt()
            var y = event.y.toInt()
            x -= widget.totalPaddingLeft
            y -= widget.totalPaddingTop
            x += widget.scrollX
            y += widget.scrollY
            val layout: Layout = widget.layout
            val line: Int = layout.getLineForVertical(y)
            val off: Int = layout.getOffsetForHorizontal(line, x.toFloat())
            val link = buffer.getSpans(off, off, URLSpan::class.java)
            if (link.isNotEmpty()) {
                onLinkClick(link[0].url)
            }
            return true
        }

        abstract fun onLinkClick(url: String?)
    }

}