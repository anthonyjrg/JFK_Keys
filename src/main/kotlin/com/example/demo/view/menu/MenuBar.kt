package com.example.demo.view.menu


import tornadofx.*

class MenuBar() : View() {
    override val root = menubar {
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
}