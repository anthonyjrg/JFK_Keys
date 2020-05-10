package com.example.demo.view.components


import com.example.demo.database.KeysDbController
import com.example.demo.model.Key
import com.example.demo.model.KeyModel
import de.jensd.fx.glyphs.icons525.Icons525
import de.jensd.fx.glyphs.icons525.Icons525View
import javafx.beans.property.*
import org.jetbrains.exposed.sql.transactions.transaction
import tornadofx.*
import tornadofx.Stylesheet.Companion.form


class CreateKeyView : View("My View") {
    val keysDb : KeysDbController by inject()
    var keyModel = KeyModel()
    val floors: List<Int> = listOf(0, 1, 2, 3, 4)
    val selectedFloor = SimpleIntegerProperty(floors.first())

    override val root = vbox {

        form()
        {
            title = "New Key"
            with(this){
                fieldset("Create New Key",Icons525View(Icons525.KEY, "3em")){
                    field("Name of Office"){
                        textfield(keyModel.officeName)
                    }
                    field ("Room Number"){
                        textfield(){
                            filterInput { it.controlNewText.isInt() }
                        }.bind(keyModel.roomNumber)
                    }
                    field("Key Number"){
                        textfield(){
                            filterInput { it.controlNewText.isInt() }
                        }.bind(keyModel.keyNumber)
                    }
                    field("Floor") {
                        combobox(selectedFloor, floors).bind(keyModel.floor)
                    }

                }
            }
        }
        button ("Create Key"){
            action {
                keysDb.connect()
                transaction {
                    keysDb.keys.add(
                            KeyModel().apply {
                                item = Key.new {
                                    this.keyNumber = keyModel.keyNumber.value.toInt()
                                    this.roomNumber = keyModel.roomNumber.value.toInt()
                                    this.floor = keyModel.floor.value.toInt()
                                    this.officeName = keyModel.officeName.value.toString()
                                }
                            }
                    )
                }

            }
        }
    }

}