package com.example.githubsearchapp.common.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.example.githubsearchapp.R

fun openUserProfileInBrowser(context: Context, htmlURL: String?) {

    if (htmlURL.isNullOrEmpty()) {
        Toast.makeText(
            context,
            context.getString(R.string.invalid_url_message),
            Toast.LENGTH_SHORT
        ).show()
        return
    }

    val browseIntent = Intent(
        Intent.ACTION_VIEW,
        Uri.parse(htmlURL)
    ).apply {
        addCategory(Intent.CATEGORY_BROWSABLE)
    }

    val title = context.resources.getString(R.string.select_browser_window_title)
    val chooser = Intent.createChooser(browseIntent, title)

    try {
        context.startActivity(chooser)
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(
            context,
            context.resources.getString(R.string.activity_not_found_toast_message),
            Toast.LENGTH_SHORT
        ).show()
    }
}