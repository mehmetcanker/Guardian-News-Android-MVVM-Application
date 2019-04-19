package com.canker.news.ui.base.activity

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.navigation.ui.setupActionBarWithNavController
import com.canker.news.ui.base.viewmodel.BaseActivityViewModel

abstract class BaseToolbarActivity<VM : BaseActivityViewModel> : BaseActivity<VM>() {

    protected abstract val toolbar: Toolbar
    protected abstract val toolbarTitle: TextView?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        setupActionBarWithNavController(navController)
    }

    override fun setTitle(title: CharSequence?) {
        super.setTitle(title)

        toolbarTitle?.let {
            if (it.text == title) return
            it.animate().cancel()
            if (title.isNullOrBlank()) {
                hideTitle()?.setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        it.text = ""
                    }
                })
            } else {
                if (it.text.isNullOrBlank()) {
                    it.text = title
                    showTitle()
                } else {
                    hideTitle()?.setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator?) {
                            it.text = title
                            showTitle()
                        }
                    })
                }
            }
        }
    }

    private fun showTitle() =
        toolbarTitle
            ?.animate()
            ?.alpha(1.0f)

    private fun hideTitle() =
        toolbarTitle
            ?.animate()
            ?.alpha(0.0f)
}