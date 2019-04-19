package com.canker.news.ui.pages.newsdetail

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import com.canker.news.ui.common.dialog.AlertDialogListener
import com.canker.data.enums.ServiceType
import com.canker.data.response.search.model.Search
import com.canker.news.R
import com.canker.news.ui.base.fragment.BaseViewFragment
import kotlinx.android.synthetic.main.fragment_detail.*

class NewsDetailFragment : BaseViewFragment() {

    override val layoutViewRes: Int = R.layout.fragment_detail

    override var title: String? = "NewsDetailFragment"

    object HTML {
        const val BASE = "<html><head><link href=\"styles.css\"" +
                " type=\"text/css\" rel=\"stylesheet\"/></head><body>"
        const val TITLE = "</body></html>"
        const val MIME_TYPE = "text/html; charset=utf-8"
        const val ASSET_FOLDER = "file:///android_asset/"
    }

    var search: Search? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initWebViewSettings()
        arguments?.let {
            search = arguments!!.getSerializable(ServiceType.SEARCH.name) as Search
        }
    }

    override fun onResume() {
        super.onResume()
        loadWebView()
    }

    private fun loadWebView() {
        val html = StringBuilder()
        html.append(HTML.BASE)
        html.append(createHeadImage(search!!.fields?.thumbnail))
        html.append("<h1>" + search!!.webTitle + "</h1>")
        html.append(search!!.fields?.body)
        html.append(HTML.TITLE)
        webview.loadDataWithBaseURL(
            HTML.ASSET_FOLDER, html.toString(),
            HTML.MIME_TYPE, null, null
        )
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebViewSettings() {
        webview.isFocusable = true
        webview.isFocusableInTouchMode = true
        webview.settings.javaScriptEnabled = true
        webview.settings.defaultFontSize = 35
        webview.settings.useWideViewPort = true
        webview.settings.defaultTextEncodingName = "utf-8"
        webview.settings.setAppCacheEnabled(true)
        webview.settings.loadWithOverviewMode = true
        webview.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webview.setLayerType(View.LAYER_TYPE_HARDWARE, null)
        } else {
            webview.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        }
    }

    private fun createHeadImage(imagePath: String?): String {
        return "<div><img  width=\"100%\" src=\"$imagePath\"></div>"
    }

}
