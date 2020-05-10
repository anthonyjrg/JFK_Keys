package com.example.demo.model

import com.example.demo.controller.Keys
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass

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

    override fun toString() = "Office: $officeName, Key Number: $keyNumber"
}
