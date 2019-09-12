package com.sj.sjwanandroid.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sj.sjwanandroid.bean.Article

class NavigationAdapter(
    override val itemLayoutId: Int,
    override var dataList: MutableList<Article>
) : BaseAdapter<Article, RecyclerView.ViewHolder>() {
    override fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    class ViewHolder(item: View) : RecyclerView.ViewHolder(item)
}