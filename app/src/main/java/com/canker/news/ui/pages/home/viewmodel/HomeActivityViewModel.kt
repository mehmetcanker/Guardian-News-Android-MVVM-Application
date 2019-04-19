package com.canker.news.ui.pages.home.viewmodel

import android.accounts.AccountAuthenticatorResponse
import android.accounts.AccountManager
import android.content.Intent
import com.canker.core.ioc.scopes.ActivityScope
import com.canker.news.ui.base.viewmodel.BaseActivityViewModel
import javax.inject.Inject


@ActivityScope
class HomeActivityViewModel @Inject constructor() : BaseActivityViewModel() {
    private var hasAuthenticatorResponse: Boolean = false

    override fun handleIntent(intent: Intent) {
        super.handleIntent(intent)
        hasAuthenticatorResponse = intent.getParcelableExtra<AccountAuthenticatorResponse>(
            AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE
        ) != null
    }

    override fun handleReady() {
        super.handleReady()
//        if (hasAuthenticatorResponse) navigate(NavigationRequest.Push(R.id.action_global_authenticator))
    }
}