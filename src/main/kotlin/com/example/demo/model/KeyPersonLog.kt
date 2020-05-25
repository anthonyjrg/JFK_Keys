package com.example.demo.model

import com.example.demo.database.KeyPersonLogs
import com.example.demo.database.Keys
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.joda.time.DateTime
import tornadofx.*


class KeyPersonLog(id: EntityID<Int>) : IntEntity(id){
    companion object: IntEntityClass<KeyPersonLog>(KeyPersonLogs)

    var keyNumber by KeyPersonLogs.KeyNumber
    fun keyNumberProperty() = SimpleIntegerProperty(this, "keyNumber", keyNumber)

    var personId by KeyPersonLogs.PersonID
    fun personIdProperty() = SimpleIntegerProperty(this, "personId", personId.value)

    var issueDate by KeyPersonLogs.issuedDate
    fun issueDateProperty() = SimpleObjectProperty<DateTime>(this, "issueDate", issueDate)

    var returnedDate by KeyPersonLogs.returnedDate
    fun returnedDateProperty() = SimpleObjectProperty<DateTime>(this, "returnedDate", returnedDate)

}

class KeyPersonLogModel : ItemViewModel<KeyPersonLog>() {
    val keyNumber = bind{item?.keyNumberProperty()}
    val personId = bind{item.personIdProperty()}
    val issueDate = bind{item.issueDateProperty()}
    val returnedDate = bind{item.returnedDateProperty()}
}


