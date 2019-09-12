package com.sj.sjwanandroid.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T,V:RecyclerView.ViewHolder> : RecyclerView.Adapter<V>(){
    abstract val itemLayoutId:Int
    lateinit var context:Context
    abstract var dataList:MutableList<T>

    abstract fun getViewHolder(parent: ViewGroup, viewType: Int):V

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): V {
        context = parent.context
        return getViewHolder(parent, viewType)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

}
/**
abstract class BaseAdapter<T,V:RecyclerView.ViewHolder> : RecyclerView.Adapter<V>(){
abstract val itemLayoutId:Int
lateinit var context:Context
abstract var dataList:MutableList<T>

abstract fun getViewHolder(itemView: ViewGroup):V

override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): V {
context = parent.context
return getViewHolder(LayoutInflater.from(context).inflate(itemLayoutId,parent,false) as ViewGroup)
}

override fun getItemCount(): Int {
return dataList.size
}

}*/