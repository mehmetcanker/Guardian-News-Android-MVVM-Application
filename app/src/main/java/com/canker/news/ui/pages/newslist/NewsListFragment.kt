package com.canker.news.ui.pages.newslist

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.canker.core.extensions.afterTextChanged
import com.canker.core.extensions.getMethodTag
import com.canker.core.networking.DataFetchResult
import com.canker.core.ui.recyclerview.LayoutManager
import com.canker.core.utils.KeyboardUtil
import com.canker.data.enums.ServiceType
import com.canker.data.response.search.SearchResponse
import com.canker.data.response.search.model.Search
import com.canker.news.R
import com.canker.news.ui.base.fragment.BaseDataFetchFragment
import com.canker.news.ui.common.dialog.getGenericErrorDialog
import com.canker.news.ui.common.dialog.showIfNonExistent
import com.canker.news.ui.common.navigation.NavigationRequest
import com.canker.news.ui.common.navigation.navigate
import com.canker.news.ui.pages.newslist.viewmodel.NewsListFragmentViewModel
import kotlinx.android.synthetic.main.fragment_news_list.*
import javax.inject.Inject

class NewsListFragment : BaseDataFetchFragment<NewsListFragmentViewModel>(),
    NewsListAdapter.NewsListInteractor {

    @Inject
    lateinit var newsListAdapter: NewsListAdapter

    @Inject
    lateinit var layoutManager: LayoutManager

    override val layoutViewRes: Int = R.layout.fragment_news_list

    override val viewModelClass: Class<NewsListFragmentViewModel> = NewsListFragmentViewModel::class.java

    override var title: String? = "Home"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsListAdapter.interactor = this
        newsListRecylerView!!.layoutManager = layoutManager.createLinear(context!!)
        newsListRecylerView!!.adapter = newsListAdapter
        bindObserver()
    }


    private fun bindObserver() {
        viewModel.getSearchResult()
        viewModel.newsListResultLiveData.observe(this,
            Observer<DataFetchResult<SearchResponse>> { it ->
                when (it) {
                    is DataFetchResult.Progress -> {
                        KeyboardUtil.hideSoftKeyboard(activity)
                    }
                    is DataFetchResult.Success -> {
                        hideLoading()
                        newsListAdapter.setData(it.data.response!!.results)
                        searchNews.clearFocus()
                    }
                    is DataFetchResult.Failure -> {
                        fragmentManager?.let {
                            getGenericErrorDialog(context!!).showIfNonExistent(it, getMethodTag())
                        }
                    }
                }
            })
    }

    override fun onStart() {
        super.onStart()
        viewModel.configureSearch()
        searchNews.afterTextChanged {
            viewModel.onOmnibarInputStateChanged(searchNews.text.toString())
        }
    }

    override fun newsClicked(search: Search) {
        val args = Bundle()
        args.putSerializable(ServiceType.SEARCH.name, search)
        navController.navigate(NavigationRequest.Push(R.id.action_home_to_newsDetail, args))
    }

}
