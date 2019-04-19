package com.canker.news.ui.base.activity

import android.content.Intent
import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.canker.core.ioc.qualifiers.ActivityContext
import com.canker.news.ui.base.fragment.BaseViewModelFragment
import com.canker.news.ui.base.viewmodel.BaseActivityViewModel
import com.canker.news.ui.common.navigation.navigate
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseActivity<VM : BaseActivityViewModel> : AppCompatActivity(),
    HasSupportFragmentInjector {

    @Inject
    protected lateinit var supportFragmentInjector: DispatchingAndroidInjector<Fragment>
    @get:LayoutRes

    protected abstract val layoutViewRes: Int
    protected abstract val navController: NavController

    @Inject
    @field:ActivityContext
    protected lateinit var viewModelProvider: ViewModelProvider


    protected abstract val viewModelClass: Class<VM>

    private var destroyDisposable: CompositeDisposable? = null
    private var stopDisposable: CompositeDisposable? = null
    private var pauseDisposable: CompositeDisposable? = null
    lateinit var viewModel: VM


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(layoutViewRes)
        destroyDisposable = CompositeDisposable()
        viewModel = viewModelProvider.get(viewModelClass)
        viewModel.handleCreate()
        viewModel.handleIntent(intent)
        onViewModelCreated()
    }

    @CallSuper
    protected open fun onViewModelCreated() {
        viewModel.navigationCommand.observe(this,
            Observer { request ->
                request?.let {
                    navController.navigate(it, this, supportFragmentManager)
                }
            })
        viewModel.resultEvent.observe(this, Observer
        { result ->
            result?.let {
                setResult(it.code, it.intent)
                if (it.finish)
                    finish()
            }
        })
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)

        setIntent(intent)
        viewModel.handleIntent(intent)

        supportFragmentManager.fragments.forEach {
            (it as? BaseViewModelFragment<*>)?.onNewIntent(
                intent
            )
        }
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        viewModel.handleResult(requestCode, resultCode, data)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)

        if (savedInstanceState != null)
            viewModel.handleRestoreInstanceState(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        stopDisposable = CompositeDisposable()
    }

    override fun onResume() {
        super.onResume()
        pauseDisposable = CompositeDisposable()
        viewModel.handleReady()
    }

    override fun onPause() {
        super.onPause()
        pauseDisposable?.dispose()
    }

    override fun onStop() {
        super.onStop()
        stopDisposable?.dispose()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        viewModel.handleSaveInstanceState(outState)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return supportFragmentInjector
    }

    override fun onDestroy() {
        super.onDestroy()
        destroyDisposable?.dispose()
    }
}