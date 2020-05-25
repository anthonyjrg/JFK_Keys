package com.example.demo.database
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.Table


object KeyPersonLogs: IntIdTable("keyPersonLog") {
    val KeyNumber  = reference("key_number", Keys.keyNumber)
    val PersonID = reference("person_id", Persons.id)
    val issuedDate = datetime("issue_date")
    val returnedDate = datetime("return_date").nullable()
}