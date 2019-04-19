package com.canker.news.ui.pages.home

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.canker.news.ui.base.activity.BaseToolbarActivity
import com.canker.news.R
import com.canker.news.ui.pages.home.viewmodel.HomeActivityViewModel
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseToolbarActivity<HomeActivityViewModel>() {

    override val viewModelClass: Class<HomeActivityViewModel> =
        HomeActivityViewModel::class.java

    override val toolbar: Toolbar by lazy { header }
    override val toolbarTitle: TextView? = null
    override val layoutViewRes: Int = R.layout.activity_home
    override val navController: NavController by lazy { findNavController(R.id.nav_host) }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
    }

    override fun onSupportNavigateUp(): Boolean = navController.navigateUp()

}
