package com.rums.clickable_textview.utils

import android.content.Context
import android.graphics.Paint
import android.os.Build
import android.text.Html
import android.text.TextUtils
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.core.text.HtmlCompat
import com.rums.clickable_textview.with_clickable_span.ClickContentInfo

const val MAX_SEE_MORE_LINES = 15

const val TEST_SHORT_STRING = "Laminaria is a genus of large brown seaweeds."
const val TEST_HTML_STRING =
    "<html>Laminaria is a genus of large brown seaweeds, commonly known as kelp, found in cool, <a href=\"Duis ac augue ut lectus congue luctus. Vivamus eu lacus vestibulm, lucutas ante dignissim, interdum Duis ac augue ut lectus congue luctus. Vivamus eu lacus vestibulm, lucutas ante dignissim, interdum \">nutrient-rich marine</a> environments. Their complex thallus.<ul>\n" +
            "    <li>First item</li>\n" +
            "    <li>Second item</li>\n" +
            "    <li>Third item</li>\n" +
            "</ul> structure includes a holdfast, stipe, and blade. These seaweeds serves as vital compound of marine ecosystem, offering habitate and sustenance to various marine species. Economically significant, Laminaria is harvested for its alginates, used as <a href=\"This is the description about stabilizers and emulsifier. This is the description about stabilizers and emulsifier. This is the description about stabilizers and emulsifier\">stabilizers and emulsifier</a> in food, <a href=\"This is the description about pharmaceutical. This is the description about pharmaceutical. This is the description about pharmaceutical.\">pharmaceutical</a> and cosmetic industries, In some culture it is consumed as food, especially in Eas Asian cuisine, known as \"kombu\" in Japan. However environmental threats such as pollution, climate changes, ocean acidification, and overharvesting can harm lamiraria populations. Reachers and conversion effors aim to understand and safeguard this crucial habitats, ensuring the health of marine environment and dependent spices.  </html>"

class Utils {
}

fun TextView.setUnderLined() {
    this.paintFlags = Paint.UNDERLINE_TEXT_FLAG
}

fun setHtmlStringToTextView(htmlString: String?, textView: TextView) {
    Log.e("RumTag", "htmlString = $htmlString")
    if (htmlString == null) {
        return
    }
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