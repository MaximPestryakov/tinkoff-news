package me.maximpestryakov.tinkoffnews;

import android.os.Build;
import android.text.Html;
import android.text.Spanned;

import java.io.IOException;

import retrofit2.HttpException;

public final class Utils {

    private static Html.TagHandler tagCleaner = (opening, tag, output, xmlReader) -> {
        if (tag.equalsIgnoreCase("style") || tag.equalsIgnoreCase("script")) {
            output.clear();
        }
    };

    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String html) {
        // removing escape characters
        html = html.replaceAll("\\n", "")
                .replaceAll("\\t", "")
                .replaceAll("\\\\\"", "\"")
                .replaceAll("\\\\'", "'")
                .replaceAll("\\\\", "\\");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY, null, tagCleaner);
        }
        return Html.fromHtml(html, null, tagCleaner);
    }

    public static int parseError(Throwable throwable) {
        if (throwable instanceof HttpException) {
            return R.string.server_is_not_available;
        } else if (throwable instanceof IOException) {
            return R.string.no_internet;
        }
        return R.string.unknown_error;
    }
}
