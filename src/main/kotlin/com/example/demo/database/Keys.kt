package com.example.demo.database

import org.jetbrains.exposed.dao.IntIdTable

object Keys : IntIdTable("keys"){
    val roomNumber = integer("roomNumber").uniqueIndex()
    val officeName = varchar("officeName", 256)
    val keyNumber = integer("keyNumber")
    val floor = integer("floor")
    val currentLogId = reference("current_log_id", KeyPersonLogs.id).nullable()
}