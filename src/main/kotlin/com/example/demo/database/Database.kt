package com.example.demo.database

import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.transactions.TransactionManager
import java.sql.Connection


private fun newTransaction(): Transaction = TransactionManager.currentOrNew(Connection.TRANSACTION_SERIALIZABLE)

fun createTables(){
    with(newTransaction()){
        SchemaUtils.create(Persons, Keys, KeyPersonLogs)
    }
}

fun <T> execute(command: ()->T): T{
    with(newTransaction()){
        return command().apply {
            commit()
            close()
        }
    }
}
