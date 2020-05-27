package com.example.demo.model

import com.example.demo.database.KeyPersonLogs
import com.example.demo.database.Keys
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import tornadofx.*

class Key(id: EntityID<Int>): IntEntity(id){
    companion object: IntEntityClass<Key>(Keys)

    var roomNumber by Keys.roomNumber
    fun roomNumberProperty() = SimpleIntegerProperty(this, "roomNumber", roomNumber)

    var officeName by Keys.officeName
    fun officeNameProperty() = SimpleStringProperty(this, "officeName", officeName)

    var keyNumber by Keys.keyNumber
    fun keyNumberProperty() = SimpleIntegerProperty(this,"keyNumber", keyNumber)

    var floor by Keys.floor
    fun floorProperty() = SimpleIntegerProperty(this, "floor", floor)

    var currentLog by Log optionalReferencedOn  Keys.currentLogId
    fun currentLogProperty() = SimpleObjectProperty<Log>(this, "currentLog", currentLog)

    override fun toString() = "Office: $officeName, Key Number: $keyNumber"
}

class KeyModel(): ItemViewModel<Key>() {
    val roomNumber = bind { item?.roomNumberProperty() }
    val officeName = bind { item?.officeNameProperty() }
    val keyNumber = bind { item?.keyNumberProperty() }
    val floor = bind { item?.floorProperty() }
    val currentLog = bind{item?.currentLogProperty()}
}

