package com.example.demo.controller

import com.example.demo.view.MainView
import com.example.demo.view.components.*
import javafx.scene.Node
import tornadofx.*

class SideMenuController() : Controller() {
    val mainView : MainView by inject()
    val homeView : HomeView by inject()
    val createKey : CreateKeyView by inject()
    val keysView : KeysView by inject()
    val createPersonnelView : CreatePersonnelView by inject()
    val personsView : PersonnelView by inject()

    fun setView(view: String) {
        var currentView: Node = when(view){
            "HomeView" -> homeView.root
            "CreateKeyView" -> createKey.root
            "KeysView" -> keysView.root
            "CreatePersonnelView" -> createPersonnelView.root
            "PersonsView" -> personsView.root
            else -> homeView.root
        }
        mainView.root.center = currentView
    }

    fun showNewKeyView() {

    }

    fun writeToDb(inputValue: String?) {
        println("Writing $inputValue to database!")
    }
}



