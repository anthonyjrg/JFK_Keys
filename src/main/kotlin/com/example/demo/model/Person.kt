package com.example.demo.model

import com.example.demo.database.Persons
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import tornadofx.*

class Person(id: EntityID<Int>): IntEntity(id) {
    companion object: IntEntityClass<Person>(Persons)

    var firstName by Persons.firstName
    fun firstNameProperty() = SimpleStringProperty(this, "firstName", firstName)

    var lastName by Persons.lastName
    fun lastNameProperty() = SimpleStringProperty(this, "lastName", lastName)

    var organization by Persons.organization
    fun organizationProperty() = SimpleStringProperty(this, "organization", organization)

    var staffNumber by Persons.staffNumber
    fun staffNumberProperty() = staffNumber?.let { SimpleIntegerProperty(this, "staffNumber", it) }

}

class PersonModel : ItemViewModel<Person>() {
    val firstName = bind{item?.firstNameProperty()}
    val lastName = bind{item?.lastNameProperty()}
    val organization = bind{item?.organizationProperty()}
    val staffNumber = bind{item?.staffNumberProperty()}
}