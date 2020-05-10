package com.example.demo.controller

import tornadofx.*

class MainController: Controller() {

    fun writeToDb(inputValue: String?) {
        println("Writing $inputValue to database!")
    }

}