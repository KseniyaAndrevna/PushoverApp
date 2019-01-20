package com.kseniyaa.pushoverapp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item.view.*
import java.text.SimpleDateFormat
import java.util.*

class ItemsAdapter : RecyclerView.Adapter<ItemsAdapter.ItemViewHolder>() {

    private var msgs: List<Msg> = ArrayList()

    fun setItems(msgs :List<Msg>? ) {
        this.msgs = msgs!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val context = parent.context
        return ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item, parent, false))

    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val msg = msgs[position]
        holder.bind(msg)
    }

    override fun getItemCount() = msgs.size


    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtItem = itemView.tv_msg
        private val txtDate = itemView.tv_date

        fun bind(msg: Msg) {
            val result = SimpleDateFormat("DD MM yyyy HH:mm").format(Date(msg.date!! * 1000))
            txtItem.text = msg.message
            txtDate.text = result
        }
    }
}
