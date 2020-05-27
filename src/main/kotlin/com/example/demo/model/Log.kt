package com.example.demo.model

import com.example.demo.database.KeyPersonLogs
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.joda.time.DateTime
import tornadofx.*


class Log(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<Log>(KeyPersonLogs)

    var keyNumber by KeyPersonLogs.keyNumber
    fun keyNumberProperty() = SimpleIntegerProperty(this, "keyNumber", keyNumber)

    var person by Person referencedOn KeyPersonLogs.personID
    fun personProperty() = SimpleObjectProperty<Person>(this, "personId", person)

    var issueDate by KeyPersonLogs.issuedDate
    fun issueDateProperty() = SimpleObjectProperty<DateTime>(this, "issueDate", issueDate)

    var returnedDate by KeyPersonLogs.returnedDate
    fun returnedDateProperty() = SimpleObjectProperty<DateTime>(this, "returnedDate", returnedDate)

    override fun toString(): String {
        return "$issueDate $returnedDate"
    }
}

class LogModel : ItemViewModel<Log>() {
    val keyNumber = bind{item?.keyNumberProperty()}
    val person = bind{item?.personProperty()}
    val issueDate = bind{item?.issueDateProperty()}
    val returnedDate = bind{item?.returnedDateProperty()}
}



