package com.example.demo.view.components

import com.example.demo.controller.PersonnelController
import com.example.demo.database.execute
import com.example.demo.model.Person
import com.example.demo.model.PersonModel
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView
import javafx.collections.FXCollections
import tornadofx.*

class CreatePersonnelView : View("My View") {
    val personModel = PersonModel()
    val organizations = FXCollections.observableArrayList("R.B.D.F", "Min of National Sec.", "Guest")
    val personnelController: PersonnelController by inject()

    override val root = vbox {
        addClass("boxes")
        form()
        {
            title = "New Key"
            with(this){
                fieldset("Create New Staff Member", FontAwesomeIconView(FontAwesomeIcon.USER)){
                    field("First Name"){
                        textfield(personModel.firstName)
                    }
                    field ("Last Name"){
                        textfield(personModel.lastName)
                    }
                    field("Select Organization"){
                        combobox(values = organizations).bind(personModel.organization)
                    }
                    field("Employee Number"){
                        textfield {
                            filterInput { it.controlNewText.isInt() }
                        }.bind(personModel.staffNumber)
                    }
                }
            }
        }
        button ("Add New Personnel"){
            action {
                   personnelController.addPersonnel(personModel)
                }
            }
        }
}


