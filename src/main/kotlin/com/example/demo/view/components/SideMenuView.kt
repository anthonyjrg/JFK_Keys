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

        addClass(SideMenuStyle.wrapper)
        paddingAll = 20

        button ("Home") {
            action {
                controller.setView("HomeView")
            }
        }

        button("View Keys"){
            action {
                controller.setView("KeysView")
            }
        }

        button("Add New Key") {
            action {
                controller.setView("CreateKeyView")
            }
        }
    }
}
