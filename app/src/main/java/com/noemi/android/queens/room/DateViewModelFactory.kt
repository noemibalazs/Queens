package com.noemi.android.queens.room

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider


class DateViewModelFactory(private val database: OrderDataBase, private val date: String): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DateViewModel(database, date) as T
    }
}