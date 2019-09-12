package com.sj.sjwanandroid.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sj.sjwanandroid.R
import com.sj.sjwanandroid.bean.Article

class HomeAdapter(
    override var dataList: MutableList<Article>
) : BaseAdapter<Article, HomeAdapter.ViewHolder>() {

    override val itemLayoutId: Int = R.layout.item_home_rv
    lateinit var loadLastOne: () -> Unit


    override fun onBindViewHolder(holder: HomeAdapter.ViewHolder, position: Int) {
        if (position==dataList.size-1){
            loadLastOne()
        }
        holder.titleTv.text = dataList[position].title
        holder.authorTv.text = dataList[position].author
    }

    override fun getViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(itemLayoutId, parent, false))


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titleTv: TextView = itemView.findViewById(R.id.title_tv)
        var authorTv: TextView = itemView.findViewById(R.id.author_tv)
    }


}