package com.rums.clickable_textview.utils

import android.content.Context
import android.widget.Toast
import com.rums.clickable_textview.with_clickable_span.ClickContentInfo

class Utils {
}

fun Context.toast(message: String?) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}


fun getClickableList(): ArrayList<ClickContentInfo?> {
    return ArrayList<ClickContentInfo?>().apply {
        add(
            ClickContentInfo(
                "genus",
                "genus word clicked"
            )
        )
        add(
            ClickContentInfo(
                "null",
                "this is null entry"
            )
        )
        add(
            ClickContentInfo(
                "fakeEntryThisis",
                "brown word clicked"
            )
        )
        add(
            ClickContentInfo(
                "brown",
                "brown word clicked"
            )
        )
        add(
            ClickContentInfo(
                "various marine",
                "various marine word clicked"
            )
        )
    }
}