package com.example.demo.model

import javafx.beans.property.Property
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty
import tornadofx.*

class KeyModel : ItemViewModel<Key>() {
    val roomNumber = bind { item?.roomNumberProperty() }
    val officeName = bind { item?.officeNameProperty() }
    val keyNumber = bind { item?.keyNumberProperty() }
    val floor = bind { item?.floorProperty() }
}
