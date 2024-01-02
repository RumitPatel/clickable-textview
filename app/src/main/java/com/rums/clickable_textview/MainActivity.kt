package com.rums.clickable_textview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.rums.clickable_textview.with_clickable_span.WithClickableSpanActivity
import com.rums.clickable_textview.with_native_click.WithNativeClickActivity
import com.rums.clickable_textview.with_no_under_line_click_span.WithNoUnderLineSpanActivity
import com.rums.clickable_textview.with_textview_resizable.WithTextViewResizableActivity

class MainActivity : AppCompatActivity() {

    private lateinit var mContext: Context

    private lateinit var btnWithNativeClick: Button
    private lateinit var btnWithTextviewResizable: Button
    private lateinit var btnWithNoUnderLineClickSpan: Button
    private lateinit var btnWithCustomTest: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initComponents()
        setListeners()
    }

    private fun initComponents() {
        mContext = this

        btnWithNativeClick = findViewById(R.id.btnWithNativeClick)
        btnWithTextviewResizable = findViewById(R.id.btnWithTextviewResizable)
        btnWithNoUnderLineClickSpan = findViewById(R.id.btnWithNoUnderLineClickSpan)
        btnWithCustomTest = findViewById(R.id.btnWithCustomTest)
    }

    private fun setListeners() {
        btnWithNativeClick.setOnClickListener {
            startActivity(Intent(mContext, WithNativeClickActivity::class.java))
        }

        btnWithTextviewResizable.setOnClickListener {
            startActivity(Intent(mContext, WithTextViewResizableActivity::class.java))
        }

        btnWithNoUnderLineClickSpan.setOnClickListener {
            startActivity(Intent(mContext, WithNoUnderLineSpanActivity::class.java))
        }

        btnWithCustomTest.setOnClickListener {
            startActivity(Intent(mContext, WithClickableSpanActivity::class.java))
        }
    }
}