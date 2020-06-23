package com.example.demo.controller

import com.example.demo.database.Keys
import com.example.demo.database.execute
import com.example.demo.model.Key
import com.example.demo.model.KeyModel
import javafx.collections.FXCollections
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

    fun createKey(keyModel: KeyModel){
        execute {
            keysList.add(
                    KeyModel().apply {
                        item = Key.new {
                            this.keyNumber = keyModel.keyNumber.value.toInt()
                            this.roomNumber = keyModel.roomNumber.value.toInt()
                            this.floor = keyModel.floor.value.toInt()
                            this.officeName = keyModel.officeName.value.toString()
                        }
                    }
            )
        }
        keyModel.rollback()
    }

    fun delete(key: KeyModel){
        execute {
            key.item.delete()
        }
        keysList.remove(key)
    }
}


