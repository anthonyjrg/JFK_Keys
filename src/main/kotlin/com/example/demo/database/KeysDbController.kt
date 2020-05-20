package com.example.demo.database

import com.example.demo.controller.Keys
import com.example.demo.controller.Keys.roomNumber
import com.example.demo.model.Key
import com.example.demo.model.KeyModel
import javafx.collections.ObservableList
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import tornadofx.*
import java.sql.Connection


class KeysDbController: Controller() {

    init {
        Database.connect("jdbc:sqlite:src/main/data/data.db", "org.sqlite.JDBC")
        TransactionManager.manager.defaultIsolationLevel = Connection.TRANSACTION_SERIALIZABLE // Or Connection.TRANSACTION_READ_COMMITTED
        transaction {
//            addLogger(StdOutSqlLogger)
            //TODO Decide to key db creation in init block
            SchemaUtils.create(Keys)
        }
    }

    fun connect(){
        Database.connect("jdbc:sqlite:src/main/data/data.db", "org.sqlite.JDBC")
        TransactionManager.manager.defaultIsolationLevel = Connection.TRANSACTION_SERIALIZABLE // Or Connection.TRANSACTION_READ_COMMITTED
        transaction {
//            addLogger(StdOutSqlLogger)
            //TODO Decide to key db creation in init block
            SchemaUtils.create(Keys)
        }
    }

    val keys: ObservableList<KeyModel> by lazy {
        transaction {
            Key.all().map{
                KeyModel().apply {
                    item = it
                }
            }.observable()
        }
    }
}


