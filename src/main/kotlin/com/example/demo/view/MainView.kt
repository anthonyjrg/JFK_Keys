package com.example.demo.view

import com.example.demo.view.components.HomeView
import com.example.demo.view.menu.MenuBar
import com.example.demo.view.menu.SideMenu
import tornadofx.*

class MainView : View("Key Logs") {
    val menuBar: MenuBar by inject()
    val sideMenu : SideMenu by inject()
    val homeView : HomeView by inject()

    override val root = borderpane {
        setPrefSize(900.00, 700.00)
        top = menuBar.root
        left = sideMenu.root
        center = homeView.root
    }
}
