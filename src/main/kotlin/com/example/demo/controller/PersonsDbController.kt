package com.example.demo.controller

import com.example.demo.database.Persons
import com.example.demo.model.Person
import com.example.demo.model.PersonModel
import javafx.collections.ObservableList
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import tornadofx.*
import java.sql.Connection

class PersonsDbController: Controller() {
    init {
        Database.connect("jdbc:sqlite:src/main/data/data.db", "org.sqlite.JDBC")
        TransactionManager.manager.defaultIsolationLevel = Connection.TRANSACTION_SERIALIZABLE // Or Connection.TRANSACTION_READ_COMMITTED
        transaction {
//            addLogger(StdOutSqlLogger)
            //TODO Decide to key db creation in init block
            SchemaUtils.create(Persons)
        }
    }

    fun connect() {
        Database.connect("jdbc:sqlite:src/main/data/data.db", "org.sqlite.JDBC")
        TransactionManager.manager.defaultIsolationLevel = Connection.TRANSACTION_SERIALIZABLE // Or Connection.TRANSACTION_READ_COMMITTED
        transaction {
//            addLogger(StdOutSqlLogger)
            //TODO Decide to key db creation in init block
            SchemaUtils.create(Persons)
        }
    }

    val persons: ObservableList<PersonModel> by lazy {
        transaction {
            Person.all().map {
                PersonModel().apply {
                    item = it
                }
            }.asObservable()
        }
    }
}