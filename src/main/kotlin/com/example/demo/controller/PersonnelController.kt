package com.example.demo.controller

import com.example.demo.database.execute
import com.example.demo.model.Person
import com.example.demo.model.PersonModel
import javafx.collections.ObservableList
import tornadofx.*

class PersonnelController: Controller() {
    val personModelList: ObservableList<PersonModel> by lazy {
        execute {
            Person.all().map {
                PersonModel().apply {
                    item = it
                }
            }.asObservable()
        }
    }

    val personList: ObservableList<Person> by lazy {
        val list = mutableListOf<Person>()
        personModelList.forEach{
            list.add(it.item)
        }
        list.asObservable()
    }

    val currentPerson: PersonModel by inject()

    fun addPersonnel(personModel: PersonModel){
        execute{
            personModelList.add(
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