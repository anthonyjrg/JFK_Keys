package com.example.demo.view.components

import com.example.demo.controller.KeysController
import com.example.demo.controller.PersonnelController
import com.example.demo.controller.LogController
import com.example.demo.database.Persons
import com.example.demo.model.KeyModel
import javafx.scene.control.TextField
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.joda.time.DateTime
import tornadofx.*

class KeysView : View("My View") {
    val keysController: KeysController by inject()
    val personnelController: PersonnelController by inject()
    val logController: LogController by inject()
    var keyModel = KeyModel()
    var textDisplay: TextField by singleAssign()

    override val root = vbox {
        paddingAll = 20
        spacing = 20.0
        val keysTable = tableview(keysController.keysList){
            columnResizePolicy = SmartResize.POLICY
            column("Office", KeyModel::officeName)
            column("Key Number", KeyModel::keyNumber)
            column("Room Number", KeyModel::roomNumber)
            column("Floor", KeyModel::floor)
        }

        keysTable.onSelectionChange {
            keyModel = it!!
            textDisplay.text = it!!.officeName.value
        }
        form {
            addClass("panel")
            label( "Log Out Key"){
                addClass("h4")
            }

            hbox {
                spacing = 10.0
                fieldset {
                    label("Office:")
                    textDisplay = textfield(){
                        isEditable = false
                    }
                }
                fieldset {
                    label("Select Person")
                    combobox<String>(){
                        val names: MutableList<String>? = mutableListOf<String>()
                        personnelController.persons.forEach{
                            names?.add(it.item.fullName)
                        }
                        items = names?.toObservable()
                    }
                }
            }

            button("Log Out Key") {
                action {
                    logController.logOutKey(keyModel.keyNumber.value.toInt(), 1, DateTime.now())
                }
            }
        }
    }


}
