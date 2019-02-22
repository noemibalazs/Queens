package com.noemi.android.queens.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.noemi.android.queens.R
import com.noemi.android.queens.adapter.AllOrdersAdapter
import com.noemi.android.queens.room.OrderDataBase
import com.noemi.android.queens.room.OrderViewModel

class AllOrdersActivity : AppCompatActivity() {

    private var listView: ListView? = null
    private var myDataBase: OrderDataBase? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_orders)

        myDataBase = OrderDataBase.getOrderDataBase(this)
        listView = findViewById(R.id.list_view)

        val viewModel = ViewModelProviders.of(this).get(OrderViewModel::class.java)
        viewModel.listOrders.observe(this, Observer {
            val myAdapter = AllOrdersAdapter(this, it!!)
            listView!!.adapter = myAdapter
        })

    }


}
