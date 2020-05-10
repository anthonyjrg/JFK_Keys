package com.example.demo.app

import javafx.scene.layout.BackgroundFill
import javafx.scene.text.FontWeight
import tornadofx.*

class SideMenuStyle : Stylesheet() {
    companion object {
        // Define our styles
        val wrapper by cssclass()
    }

    init {
        wrapper{
            spacing = 10.px
        }
    }
}