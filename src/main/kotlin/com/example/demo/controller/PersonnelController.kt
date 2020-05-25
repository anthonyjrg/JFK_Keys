package com.example.demo.controller

import com.example.demo.database.Persons
import com.example.demo.database.execute
import com.example.demo.model.Person
import com.example.demo.model.PersonModel
import javafx.collections.ObservableList
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import tornadofx.*
import java.sql.Connection

class PersonnelController: Controller() {
    val persons: ObservableList<PersonModel> by lazy {
        execute {
            Person.all().map {
                PersonModel().apply {
                    item = it
                }
            }.asObservable()
        }
    }
}