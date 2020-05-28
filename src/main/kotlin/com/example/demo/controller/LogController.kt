package com.example.demo.controller

import com.example.demo.database.Keys
import com.example.demo.database.execute
import com.example.demo.model.Key
import com.example.demo.model.Log
import com.example.demo.model.LogModel
import com.example.demo.model.Person
import javafx.collections.ObservableList
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.joda.time.DateTime
import tornadofx.*

class LogController: Controller(){
    val logList: ObservableList<LogModel> by lazy {
        execute {
            Log.all().map {
                LogModel().apply {
                    item = it
                }
            }.asObservable()
        }
    }

    fun logOutKey(keyNumber: Int, person: Person, issueDate: DateTime){
        execute {
            var log = Log.new {
                this.keyNumber = keyNumber
                this.person = person
                this.issueDate = issueDate
            }
            //Finding key being logged out and updating its latest log value.
            val key = Key.find(Keys.keyNumber eq keyNumber).firstOrNull()
            key?.currentLog = log
        }
    }

    fun logInKey(log: Log){
        execute {
            //Set the return date in log to now
            log.returnedDate = DateTime.now()

            //Remove Log attached to this key
            val key = Key.find(Keys.keyNumber eq log.keyNumber).firstOrNull()
            key?.currentLog = null
        }
    }
}