package com.rums.canvas_example.with_textview_resizable

import android.content.Context
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.rums.canvas_example.R
import com.rums.canvas_example.with_textview_resizable.TextViewResizable.MAX_LINE_COUNT

class WithTextViewResizableActivity : AppCompatActivity() {

    private lateinit var mContext: Context

    private lateinit var tvOverview: TextView
    private lateinit var tvOverviewReadMoreHide: TextView
    private lateinit var tvDescription: TextView
    private lateinit var tvDescriptionReadMoreHide: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_with_textviewresizable)

        initComponents()
    }

    private fun initComponents() {
        mContext = this

        tvOverview = findViewById(R.id.tvOverview)
        tvOverviewReadMoreHide = findViewById(R.id.tvOverviewReadMoreHide)
        tvDescription = findViewById(R.id.tvDescription)
        tvDescriptionReadMoreHide = findViewById(R.id.tvDescriptionReadMoreHide)


//        enableDefaultClick()
        testSeeMore()
    }

    private fun enableDefaultClick() { // Enable TextView's click
        tvOverview.movementMethod = LinkMovementMethod.getInstance()
        tvDescription.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun testSeeMore() {
        tvOverview.text = getString(R.string.testttt_small)
        TextViewResizable.makeTextViewResizable(
            tvOverview,
            MAX_LINE_COUNT,
            getString(R.string.see_more),
            true
        )

        tvDescription.text = getString(R.string.testttt)
        TextViewResizable.makeTextViewResizable(
            tvDescription,
            MAX_LINE_COUNT,
            getString(R.string.see_less),
            true
        )
    }
}