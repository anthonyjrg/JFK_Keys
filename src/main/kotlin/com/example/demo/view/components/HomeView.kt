package com.example.demo.view.components

import com.example.demo.database.KeysDbController
import javafx.beans.property.SimpleIntegerProperty
import javafx.geometry.Pos
import javafx.scene.text.TextAlignment
import tornadofx.*

class HomeView : View("My View") {
    val keyDbController: KeysDbController by inject()
    var keyCount = keyDbController.keys

    override val root = hbox {
        alignment = Pos.CENTER
        text("Welcome to Key Log App \n You have ${keyCount} keys."){
            textAlignment = TextAlignment.CENTER
        }
    }
}
