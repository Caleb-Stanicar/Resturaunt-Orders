package com.example.waiterorders

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var ordersAdapter: OrdersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ordersAdapter = OrdersAdapter(mutableListOf())

        rvOrders.adapter = ordersAdapter
        rvOrders.layoutManager = LinearLayoutManager(this)

        btnAddOrder.setOnClickListener {
            val orderTitle = etOrdersTitle.text.toString()
            if (orderTitle.isNotEmpty()) {
                val order = Orders(orderTitle, 0)
                ordersAdapter.addOrder(order)
                etOrdersTitle.text.clear()
            }
        }
        btnDeleteOrders.setOnClickListener {
            ordersAdapter.deleteOrders()
        }
    }
}