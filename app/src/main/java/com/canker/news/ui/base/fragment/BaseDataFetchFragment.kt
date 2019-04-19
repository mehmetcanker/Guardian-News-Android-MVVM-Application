package com.canker.news.ui.base.fragment

import android.os.Bundle
import android.view.View
import com.canker.news.ui.base.viewmodel.BaseFragmentViewModel

abstract class BaseDataFetchFragment<VM> :
    BaseViewModelFragment<VM>() where VM : BaseFragmentViewModel {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}
