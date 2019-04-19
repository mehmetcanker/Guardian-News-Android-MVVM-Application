package com.canker.news.ui.common.dialog

import android.content.Context
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.canker.news.ui.common.dialog.AlertDialog

fun getGenericErrorDialog(context: Context): AlertDialog =
    AlertDialog.Builder(context)
        .title("Generic title")
        .message("Generic message")
        .positiveButton("Ok")
        .create()

fun DialogFragment.showIfNonExistent(manager: FragmentManager, tag: String) {
    if (manager.findFragmentByTag(tag) == null)
        this.show(manager, tag)
}

fun DialogFragment.showNowIfNonExistent(manager: FragmentManager, tag: String) {
    if (manager.findFragmentByTag(tag) == null)
        this.showNow(manager, tag)
}