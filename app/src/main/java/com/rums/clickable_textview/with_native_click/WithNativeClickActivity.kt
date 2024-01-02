package com.rums.clickable_textview.with_native_click

import android.content.Context
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.rums.clickable_textview.R

class WithNativeClickActivity : AppCompatActivity() {

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

        enableDefaultClick()
    }

    private fun enableDefaultClick() { // Enable TextView's click
        tvOverview.movementMethod = LinkMovementMethod.getInstance()
        tvDescription.movementMethod = LinkMovementMethod.getInstance()
    }
}