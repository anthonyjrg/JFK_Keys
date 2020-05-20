package com.example.demo.view.components

import com.example.demo.app.SideMenuStyle
import com.example.demo.controller.MainController
import com.example.demo.controller.SideMenuController
import com.example.demo.view.MainView
import javafx.geometry.Insets
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import tornadofx.*

class SideMenuView : View() {
    val mainView : MainView by inject()
    val controller: SideMenuController by inject()

    override val root = vbox {

        listmenu(theme = "blue") {
            item(text = "Contacts") {
                // Marks this item as active.
                activeItem = this
                whenSelected { /* Do some action */ }
            }
            item(text = "Projects" )
            item(text = "Settings")
        }
    }
}
