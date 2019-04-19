package com.canker.news.ui.base.viewmodel

import android.content.Intent
import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData
import com.canker.news.ui.common.navigation.NavigationRequest
import com.canker.news.ui.common.viewmodel.NavigationEvent
import com.canker.news.ui.common.viewmodel.SingleLiveEvent
import com.evernote.android.state.StateSaver

abstract class BaseViewControllerViewModel : BaseViewModel() {

    private val _navigationCommand: NavigationEvent = NavigationEvent()
    val navigationCommand: LiveData<NavigationRequest> = _navigationCommand
    private val _resultEvent: SingleLiveEvent<Result> by lazy { SingleLiveEvent<Result>() }
    val resultEvent: LiveData<Result> = _resultEvent

    open fun handleCreate() {

    }

    open fun handleIntent(intent: Intent) {}

    open fun handleResult(requestCode: Int, code: Int, data: Intent?) {}

    @CallSuper
    open fun handleRestoreInstanceState(savedInstanceState: Bundle) {
        StateSaver.restoreInstanceState(this, savedInstanceState)
    }

    open fun handleReady() {}

    open fun handleUserConnected() {}

    open fun handleUserDisconnected() {}

    @CallSuper
    open fun handleSaveInstanceState(outState: Bundle) {
        StateSaver.saveInstanceState(this, outState)
    }

    fun navigate(navigationRequest: NavigationRequest) {
        _navigationCommand.value = navigationRequest
    }

    fun setResult(code: Int, intent: Intent?, finish: Boolean = true) {
        _resultEvent.value = Result(code, intent, finish)
    }

    data class Result(var code: Int, var intent: Intent?, var finish: Boolean)
}