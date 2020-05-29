package com.example.demo.view.components

import com.example.demo.controller.KeysController
import com.example.demo.controller.PersonnelController
import com.example.demo.controller.LogController
import com.example.demo.database.execute
import com.example.demo.model.KeyModel
import com.example.demo.model.PersonModel
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView
import javafx.scene.control.TextField
import org.joda.time.DateTime
import tornadofx.*

class KeysView : View("My View") {
    val keysController: KeysController by inject()
    val personnelController: PersonnelController by inject()
    val logController: LogController by inject()
    var keyModel = KeyModel()
    var selectedPerson = PersonModel()
    var officeTextDisplay: TextField by singleAssign()
    var officeTextDisplay2:  TextField by singleAssign()
    var personTextDisplay: TextField by singleAssign()
    var isKeyLoggedOut: Boolean = false
    var logOutForm: Form by singleAssign()
    var logInForm: Form by singleAssign()


    override val root = vbox {
        addClass("boxes")
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
                officeTextDisplay.text = it.officeName.value
                officeTextDisplay2.text = it.officeName.value

                isKeyLoggedOut = (keyModel.item?.currentLog != null) && (keyModel.item.currentLog?.returnedDate == null)
                print(it.item.currentLog.toString())
                selectedPerson.item = it.item.currentLog?.person
                selectedPerson.item?.fullName.let{
                    personTextDisplay.text = it
                }
            }
            logOutForm.isDisable = isKeyLoggedOut
            logInForm.isDisable = !isKeyLoggedOut
        }

        hbox {
            spacing = 10.0
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
                            officeTextDisplay = textfield() {
                                isEditable = false
                                validator(){
                                   
                                }
                            }
                        }
                        fieldset {
                            label("Select Person")
                            combobox(property = personnelController.currentPerson.itemProperty, values = personnelController.personList) {
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
                            //Todo Update list after logging key in
                            keysController.keysList
                        }
                    }
                }
            }

            hbox {
                addClass("panel")

                logInForm = form {
                    label("Log In Key") {
                        addClass("h4")
                    }
                    hbox {
                        spacing = 10.0
                        fieldset {
                            label("Office:")
                            //@Todo refactor duplication
                            officeTextDisplay2 = textfield() {
                                isEditable = false
                            }
                        }
                        fieldset {
                            label("In Possesion Of:")
                            personTextDisplay = textfield {
                                isEditable = false
                            }
                            hbox {
                                spacing = 5.0
                                paddingTop = 5
                                label()
                                label()
                            }
                        }
                    }

                    button("Log In Key") {
                        action{
                            logController.logInKey(keyModel.currentLog.value)
                            keysController.keysList.remove(keyModel)
                        }
                    }
                }
            }
        }
    }


}
