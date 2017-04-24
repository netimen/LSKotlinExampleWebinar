package com.loftschool.kotlinexample

import android.graphics.Color
import android.os.Bundle
import android.support.annotation.ColorInt
import android.support.annotation.MenuRes
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * Copyright (c) 2017.
 * All Rights Reserved.
 *
 * Author: Dmitry Gordeev <netimen@gmail.com>
 * Date:   24.04.17
 */

// https://antonioleiva.com/kotlin-awesome-tricks-for-android/
class MainActivityK : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        add_item.setOnClickListener { onAddItemClicked() }

        start_activity.setOnClickListener { startActivity<AnotherActivity>(AnotherActivity.PARAM_NAME to "Some name", AnotherActivity.PARAM_PRICE to 15) }
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean = inflateMenu(R.menu.main, menu)
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.menu_item -> {
            onAddItemClicked()
            true
        }
//        R.id.menu_item -> consume { onAddItemClicked() }
        else -> super.onOptionsItemSelected(item)
    }

    fun onAddItemClicked() {
        addItem(Item("item", 0))
        Snackbar.make(container, R.string.added, Snackbar.LENGTH_LONG)
                .setAction(R.string.cancel) {
                    toast(R.string.cancelled)
                }
                .setActionTextColor(Color.YELLOW)
                .show()
//        container.snack(R.string.added) {
//            action(R.string.cancel, Color.YELLOW) { toast(R.string.cancelled) }
//        }
    }

    fun addItem(item: Item) {
        // adding item...
    }

}

class Item(val name: String, val price: Int)

/// SHOULD BE SOMEWHERE IN UTILS.KT

fun AppCompatActivity.inflateMenu(@MenuRes menuRes: Int, menu: Menu?): Boolean {
    menuInflater.inflate(menuRes, menu)
    return true
}

inline fun consume(f: () -> Unit): Boolean {
    f()
    return true
}

inline fun View.snack(@StringRes messageResId: Int, length: Int = Snackbar.LENGTH_LONG, f: Snackbar.() -> Unit) {
    val snack = Snackbar.make(this, messageResId, length)
    snack.f()
    snack.show()
}

fun Snackbar.action(@StringRes textResId: Int, @ColorInt color: Int? = null, listener: (View) -> Unit) {
    setAction(textResId, listener)
    color?.let { setActionTextColor(color) }
}
