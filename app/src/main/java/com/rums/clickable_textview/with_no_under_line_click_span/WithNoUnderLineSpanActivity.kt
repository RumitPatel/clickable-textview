package com.rums.clickable_textview.with_no_under_line_click_span

import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.rums.clickable_textview.R

class WithNoUnderLineSpanActivity : AppCompatActivity() {

    private lateinit var mContext: Context

    private lateinit var tvOverview: TextView
    private lateinit var tvOverviewReadMoreHide: TextView
    private lateinit var tvDescription: TextView
    private lateinit var tvDescriptionReadMoreHide: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_with_no_under_line_click_span)

        initComponents()
    }

    private fun initComponents() {
        mContext = this

        tvOverview = findViewById(R.id.tvOverview)
        tvOverviewReadMoreHide = findViewById(R.id.tvOverviewReadMoreHide)
        tvDescription = findViewById(R.id.tvDescription)
        tvDescriptionReadMoreHide = findViewById(R.id.tvDescriptionReadMoreHide)


        testSeeMore()
    }


    private fun testSeeMore() {
        tvOverview.setResizableText(getString(R.string.demo_long_hyperlink_text), 4, false)
        tvDescription.setResizableText(getString(R.string.demo_short_text), 4, false)
    }
}