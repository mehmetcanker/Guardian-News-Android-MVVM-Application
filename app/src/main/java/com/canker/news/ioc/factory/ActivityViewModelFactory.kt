@file:Suppress("UNCHECKED_CAST")

package com.canker.news.ioc.factory

import androidx.lifecycle.ViewModel
import com.canker.core.ioc.scopes.ActivityScope
import com.canker.news.ui.base.viewmodel.BaseActivityViewModel
import javax.inject.Inject
import javax.inject.Provider

@ActivityScope
class ActivityViewModelFactory @Inject constructor(
    creators: Map<Class<out BaseActivityViewModel>
            , @JvmSuppressWildcards Provider<BaseActivityViewModel>>
) : BaseViewModelFactory(creators as Map<Class<out ViewModel>, Provider<ViewModel>>)