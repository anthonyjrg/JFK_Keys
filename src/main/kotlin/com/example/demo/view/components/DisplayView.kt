package com.example.demo.view.components

import javafx.scene.control.TextField
import javafx.scene.text.Text
import tornadofx.*

class DisplayView : View() {
    var text: TextField = TextField()

    override val root = gridpane() {
        setPrefSize(700.00, 500.00)
    }

    fun addText(text : String) : Unit {
        this.text.text = text
    }
}
