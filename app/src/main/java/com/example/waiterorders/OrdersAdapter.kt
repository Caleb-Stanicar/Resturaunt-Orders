package com.example.waiterorders

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_order.view.*

class OrdersAdapter (
    private val orders: MutableList<Orders>
        ) : RecyclerView.Adapter<OrdersAdapter.OrderViewHolder>() {
    class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        return OrderViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_order,
                parent,
                false
            )
        )
    }

    fun deleteOrders(){
        orders.removeAll { orders ->
            orders.isChecked
        }
        notifyDataSetChanged()
    }

    fun addOrder(order: Orders){
        orders.add(order)
        notifyItemInserted(orders.size-1)
    }

    private fun toggleStrikeThrough(tvOrderTitle: TextView, isChecked: Boolean){
        if(isChecked) {
            tvOrderTitle.paintFlags = tvOrderTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvOrderTitle.paintFlags = tvOrderTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val currOrder = orders[position]
        holder.itemView.apply {
            tvOrderTitle.text = currOrder.title
            cbDone.isChecked = currOrder.isChecked
            toggleStrikeThrough(tvOrderTitle, currOrder.isChecked)
            cbDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tvOrderTitle, isChecked)
                currOrder.isChecked = !currOrder.isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return orders.size
    }
}