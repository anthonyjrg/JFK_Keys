package com.example.demo.controller

import com.example.demo.database.Keys
import com.example.demo.database.execute
import com.example.demo.model.Key
import com.example.demo.model.KeyModel
import javafx.collections.ObservableList
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import tornadofx.*
import java.sql.Connection


class KeysController: Controller() {

    /**
     * Returns a observable list of key models containing all keys.
     */
    val keysList: ObservableList<KeyModel> by lazy {
        execute {
            Key.all().map{
                KeyModel().apply {
                    item = it
                }
            }.asObservable()
        }
    }
}

