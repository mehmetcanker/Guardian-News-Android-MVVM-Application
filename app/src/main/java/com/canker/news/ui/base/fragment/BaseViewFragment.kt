package com.canker.news.ui.base.fragment

import android.app.Activity
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class BaseViewFragment : BaseFragment(),
    HasSupportFragmentInjector {

    @Inject
    protected lateinit var childFragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment>? {
        return childFragmentInjector
    }

    override fun onAttach(activity: Activity) {
        AndroidSupportInjection.inject(this)
        super.onAttach(activity)
    }

}
