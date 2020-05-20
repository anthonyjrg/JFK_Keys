package com.example.demo.view

import com.example.demo.view.components.HomeView
import com.example.demo.view.menu.SideMenu
import tornadofx.*

class MainView : View("Key Logs") {
    val sideMenu : SideMenu by inject()
    val homeView : HomeView by inject()

    override val root = borderpane {
        setPrefSize(900.00, 700.00)
        top = menubar {
            menu("File") {
                menu("Connect")
                item("Quit")
            }
            menu("Edit") {
                item("Copy")
                item("Paste")
            }
            menu("Help"){
                item("User Guide")
                item("About")
            }
        }
        left = sideMenu.root
        center = homeView.root
    }
}
