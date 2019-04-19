package com.canker.news.ui.pages.newslist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.canker.core.extensions.clickWithDebounce
import com.canker.data.response.search.model.Search
import com.canker.news.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.news_item.view.*

class NewsListAdapter(
    private val activity: Context,
    private val picasso: Picasso
) : RecyclerView.Adapter<NewsListAdapter.ListViewHolder>() {

    private var data = emptyList<Search>()
    var interactor: NewsListInteractor? = null

    interface NewsListInteractor {
        fun newsClicked(
            search: Search
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.news_item,
                parent,
                false
            )
        )
    }

    fun setData(data: List<Search>?) {
        this.data = data ?: this.data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        if (data.isNotEmpty()) holder.bind(activity, data[position], picasso)
        holder.itemView.clickWithDebounce {
            interactor?.newsClicked(data[position])
        }
    }

    class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(
            activity: Context,
            search: Search,
            picasso: Picasso
        ) {
            itemView.newsTitle.text = search.webTitle
            picasso.load(search.fields!!.thumbnail)
                .placeholder(R.drawable.placeholder)
                .into(itemView.image)
        }
    }
}