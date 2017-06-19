package me.maximpestryakov;

import android.os.Build;
import android.text.Html;
import android.text.Spanned;

public final class Utils {

    private static Html.TagHandler tagCleaner = (opening, tag, output, xmlReader) -> {
        if (tag.equalsIgnoreCase("style") || tag.equalsIgnoreCase("script")) {
            output.clear();
        }
    };

    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String html) {
        html = html.replaceAll("\\n", "").replaceAll("\\t", "");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY, null, tagCleaner);
        }
        return Html.fromHtml(html, null, tagCleaner);
    }
}
