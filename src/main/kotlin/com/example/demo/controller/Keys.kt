package com.example.demo.controller

import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.EnumerationColumnType
import org.jetbrains.exposed.sql.Table
import kotlin.reflect.KClass

object Keys : IntIdTable("keys"){
//    val id = integer("id").autoIncrement().primaryKey()
    val roomNumber = integer("roomNumber").uniqueIndex()
    val officeName = varchar("officeName", 256)
    val keyNumber = integer("keyNumber")
    val floor = integer("floor")
}