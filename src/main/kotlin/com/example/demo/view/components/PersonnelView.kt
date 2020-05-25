package com.example.demo.view.components

import com.example.demo.controller.PersonnelController
import com.example.demo.model.PersonModel
import tornadofx.*

class PersonnelView : View("My View") {
    val personnelController: PersonnelController by inject()

    override val root = vbox {
        tableview<PersonModel>{
            items = personnelController.persons
            column("First Name", PersonModel::firstName)
            column("Last Name", PersonModel::lastName)
            column("Organization", PersonModel::organization)
            column("Staff Number", PersonModel::staffNumber)
        }
    }
}