package com.example.demo.view.components

import com.example.demo.controller.KeysDbController
import javafx.beans.property.SimpleIntegerProperty
import javafx.geometry.Pos
import javafx.scene.text.TextAlignment
import tornadofx.*

class HomeView : View("My View") {
    val keyDbController: KeysDbController by inject()
    var keyCount = SimpleIntegerProperty()

    override val root = hbox {
        keyCount.bind(keyDbController.keys.sizeProperty)
        alignment = Pos.CENTER
        text("Welcome to Key Log App \n You have ${keyCount.value} keys."){
            textAlignment = TextAlignment.CENTER
        }
    }
}
