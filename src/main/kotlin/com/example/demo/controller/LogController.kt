package com.example.demo.controller

import com.example.demo.database.KeyPersonLogs
import com.example.demo.database.Persons
import com.example.demo.database.execute
import com.example.demo.model.KeyPersonLog
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.joda.time.DateTime
import tornadofx.*

class LogController: Controller(){
    fun logOutKey(keyNumber: Int, personId: Int, issueDate: DateTime){
        execute {
            KeyPersonLog.new {
                this.keyNumber = keyNumber
                this.personId = EntityID(personId, Persons)
                this.issueDate = issueDate
            }
        }

    }
}