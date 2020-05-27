package com.example.demo.view.components

import com.example.demo.controller.KeysController
import com.example.demo.controller.PersonnelController
import com.example.demo.controller.LogController
import com.example.demo.database.execute
import com.example.demo.model.KeyModel
import com.example.demo.model.LogModel
import com.example.demo.model.Person
import com.example.demo.model.PersonModel
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView
import javafx.scene.control.TextField
import org.jetbrains.exposed.sql.exists
import org.joda.time.DateTime
import tornadofx.*

class KeysView : View("My View") {
    val keysController: KeysController by inject()
    val personnelController: PersonnelController by inject()
    val logController: LogController by inject()
    var keyModel = KeyModel()
    var textDisplay: TextField by singleAssign()
    var editable: Boolean = false
    var logOutForm: Form by singleAssign()

    override val root = vbox {
        addClass("mainBox")
        val keysTable = tableview(keysController.keysList){
            columnResizePolicy = SmartResize.POLICY
            column("Office", KeyModel::officeName)
            column("Key Number", KeyModel::keyNumber)
            column("Room Number", KeyModel::roomNumber)
            column("Floor", KeyModel::floor)
            column("Log", KeyModel::currentLog).cellFormat {
                    //If key has a log then it has been issued at lease once
                    if (it.returnedDate == null) {
                        //if current log has been created and has null return date then its status is issued
                        execute {
                            text = "Issued to ${it.person.firstName.take(1)}. ${it.person.lastName}"
                        }
                    }
                    else {
                        println("null")
                        graphic = FontAwesomeIconView(FontAwesomeIcon.CHECK_CIRCLE)
                    }

            }
        }

        keysTable.onSelectionChange {
            keyModel = it!!
            execute {
                textDisplay.text = it.officeName.value
                editable = (keyModel.item?.currentLog != null) && (keyModel.item.currentLog?.returnedDate == null)
            }
            logOutForm.isDisable = editable
        }
        hbox {
            addClass("panel")
            logOutForm = form {
                //If the currently select item has no return date(i.e it is checked out) we disable the form


                label("Log Out Key") {
                    addClass("h4")
                }

                hbox {
                    spacing = 10.0
                    fieldset {
                        label("Office:")
                        textDisplay = textfield() {
                            isEditable = false
                        }
                    }
                    fieldset {
                        label("Select Person")

                        combobox(property = personnelController.currentPerson.itemProperty, values = personnelController.persons) {
                            cellFormat { text = it.fullName }
                        }
                        hbox {
                            spacing = 5.0
                            paddingTop = 5
                            label(personnelController.currentPerson.staffNumber)
                            label(personnelController.currentPerson.organization)
                        }

                    }
                }

                button("Log Out Key") {

                    action {
                        logController.logOutKey(keyModel.keyNumber.value.toInt(), personnelController.currentPerson.item, DateTime.now())
                    }
                }
            }
        }
    }


}
