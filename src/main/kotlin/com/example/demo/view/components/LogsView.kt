package com.example.demo.view.components

import com.example.demo.controller.LogController
import com.example.demo.model.LogModel
import javafx.scene.control.TableView
import javafx.scene.control.TextField
import javafx.scene.text.Text
import tornadofx.*

class LogsView : View() {
    val logController: LogController by inject()

    override val root = vbox {
        addClass("boxes")

        tableview(logController.logList){
            columnResizePolicy = SmartResize.POLICY
            column("Key", LogModel::keyNumber)
            column("Person", LogModel::person)
            column("Logged Out Date", LogModel::issueDate)
            column("Returned Date", LogModel::returnedDate)
        }
    }
}