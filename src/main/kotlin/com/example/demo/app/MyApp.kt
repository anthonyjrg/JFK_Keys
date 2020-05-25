package com.example.demo.app

import com.example.demo.view.MainView
import org.jetbrains.exposed.sql.Database
import com.example.demo.database.createTables
import tornadofx.*

class MyApp: App(MainView::class, Styles::class, SideMenuStyle::class){
    init {
        Database.connect("jdbc:sqlite:src/main/data/data.db", "org.sqlite.JDBC")
        createTables()
    }
}