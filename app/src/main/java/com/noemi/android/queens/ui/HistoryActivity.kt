package com.noemi.android.queens.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.noemi.android.queens.R
import kotlinx.android.synthetic.main.activity_history.*


class HistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        order_all.setOnClickListener{
            val intent = Intent(this, AllOrdersActivity::class.java)
            startActivity(intent)
        }
    }
}
