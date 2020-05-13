package com.example.demo.view.components

import com.example.demo.controller.PersonsDbController
import com.example.demo.database.Persons.firstName
import com.example.demo.model.Key
import com.example.demo.model.KeyModel
import com.example.demo.model.Person
import com.example.demo.model.PersonModel
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView
import de.jensd.fx.glyphs.icons525.Icons525
import de.jensd.fx.glyphs.icons525.Icons525View
import javafx.collections.FXCollections
import org.jetbrains.exposed.sql.transactions.transaction
import tornadofx.*

class CreatePersonnelView : View("My View") {
    val personModel = PersonModel()
    val organizations = FXCollections.observableArrayList("R.B.D.F", "Min of National Sec.", "Guest")
    val personsDb: PersonsDbController by inject()

    override val root = vbox {
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
                personsDb.connect()
                transaction {
                    personsDb.persons.add(
                            PersonModel().apply {
                                item = Person.new {
                                    this.firstName = personModel.firstName.value.toString()
                                    this.lastName = personModel.lastName.value.toString()
                                    this.organization = personModel.organization.value.toString()
                                    this.staffNumber = personModel.staffNumber.value.toInt()
                                }
                            }
                    )
                }
                personModel.rollback()
            }
        }
    }
}
