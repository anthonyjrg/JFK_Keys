package com.example.demo.view.components

import com.example.demo.database.KeysDbController
import com.example.demo.model.KeyModel
import tornadofx.*

class KeysView : View("My View") {
    val keysDb: KeysDbController by inject()

    override val root = vbox {
        paddingAll = 20
        tableview<KeyModel>{
            items = keysDb.keys
            column("Office", KeyModel::officeName)
            column("Key Number", KeyModel::keyNumber)
            column("Room Number", KeyModel::roomNumber)
            column("Floor", KeyModel::floor)
        }

    }

}
