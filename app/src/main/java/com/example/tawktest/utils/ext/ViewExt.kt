package com.example.tawktest.utils.ext

import android.content.Context
import android.view.View
import android.widget.*
import com.bumptech.glide.Glide
import com.example.tawktest.R
import com.google.android.material.snackbar.Snackbar

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun ProgressBar.show() {
    visibility = View.VISIBLE
}

fun ProgressBar.hide() {
    visibility = View.GONE
}

fun View.snackbar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).also { snackbar ->
        snackbar.setAction("Ok") {
            snackbar.dismiss()
        }
    }.show()
}

/**View Visibility Ext*/
fun View.gone() {
    if (visibility != View.GONE) visibility = View.GONE
}

fun View.visible() {
    if (visibility != View.VISIBLE) visibility = View.VISIBLE
}

fun View.invisible() {
    if (visibility != View.INVISIBLE) visibility = View.INVISIBLE
}

/**View Get Value Ext*/
val Button.value
    get() = text?.toString() ?: ""

val EditText.value
    get() = text?.toString() ?: ""

val TextView.value
    get() = text?.toString() ?: ""

/**View Empty validation Ext*/
fun EditText.isEmpty() = value.isEmpty()

fun TextView.isEmpty() = value.isEmpty()

/**Image SetUp Using Glide*/
fun ImageView.loadImage(imageId: Int) {
    Glide.with(this)
        .asBitmap()
        .load(imageId)
        .placeholder(R.color.purple_200)
        .into(this)
}

fun ImageView.loadUrl(url: String){
    Glide.with(this)
        .load(url)
        .placeholder(R.color.purple_200)
        .into(this)
}



