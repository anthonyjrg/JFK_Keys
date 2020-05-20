package com.example.demo.database

import org.jetbrains.exposed.dao.IntIdTable

object Persons : IntIdTable("persons") {
    val firstName = varchar("f_name", 50)
    val lastName = varchar("l_name", 50)
    val organization = varchar("org", 60)
    val staffNumber = integer("staffNumber").uniqueIndex().nullable()
}