package com.example.demo.view

import com.example.demo.view.components.SideMenuView
import com.example.demo.view.components.HomeView
import tornadofx.*

class MainView : View("Key Logs") {
    val sideMenu : SideMenuView by inject()
    val homeView : HomeView by inject()

    override val root = borderpane {
        setPrefSize(700.00, 500.00)

        left = sideMenu.root
        center = homeView.root
    }
}
