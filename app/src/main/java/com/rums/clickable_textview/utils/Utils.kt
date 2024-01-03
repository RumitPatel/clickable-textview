package com.rums.clickable_textview.utils

import android.content.Context
import android.os.Build
import android.text.Html
import android.text.TextUtils
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.core.text.HtmlCompat
import com.rums.clickable_textview.with_clickable_span.ClickContentInfo

const val MAX_SEE_MORE_LINES = 7

class Utils {
}

fun setHtmlStringToTextView(htmlString: String?, textView: TextView) {
    Log.e("RumTag", "htmlString = $htmlString")
    if(htmlString == null) {return}
    if (!TextUtils.isEmpty(htmlString)) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textView.text = Html.fromHtml(htmlString, Html.FROM_HTML_MODE_COMPACT)
        } else {
            textView.text = HtmlCompat.fromHtml(htmlString, HtmlCompat.FROM_HTML_MODE_LEGACY)
        }
    } else {
        textView.text = ""
    }
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