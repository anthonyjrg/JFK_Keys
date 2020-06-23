package com.example.demo.view.components

import com.example.demo.controller.KeysController
import com.example.demo.model.KeyModel
import tornadofx.*

class KeyModal: View("Key View") {
    val keysController: KeysController by inject()
    val it: KeyModel by inject()

    override val root = borderpane {
        setPrefSize(500.00, 500.00)
        println("${it} selected")
        top = vbox {
            label(it.officeName)
            label(it.roomNumber)
            label(it?.notes)
        }

        bottom = hbox{
            button("Delete Key"){
                action {
                    keysController.delete(it)
                    close()
                }
            }
        }
    }
}
