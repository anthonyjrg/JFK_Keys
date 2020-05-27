package com.example.demo.view.menu

import com.example.demo.app.SideMenuStyle
import com.example.demo.app.Styles
import com.example.demo.controller.SideMenuController
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView
import tornadofx.*


class SideMenu : View("My View") {
    val controller: SideMenuController by inject()

    override val root = vbox() {
        style{
            backgroundColor = multi(c("#ececec"))
        }
        addClass(SideMenuStyle.menuContainer)
        hbox {
            textflow {
                addClass(Styles.headings)
                text("JFK") {
                    addClass(SideMenuStyle.h1)
                }
                text("Keys") {
                    addClass(SideMenuStyle.h2)
                }
            }
        }

        listmenu() {
            item(text = "Keys", graphic = FontAwesomeIconView(FontAwesomeIcon.KEY)) {
                addClass(SideMenuStyle.menuItm)

                item("View All") {
                    addClass(SideMenuStyle.subMenuItem)
                    whenSelected { controller.setView("KeysView") }
                }
                item("Add New") {
                    addClass(SideMenuStyle.subMenuItem)
                    whenSelected { controller.setView("CreateKeyView") }
                }
            }
            item(text = "Personnel", graphic = FontAwesomeIconView(FontAwesomeIcon.USERS)) {
                addClass(SideMenuStyle.menuItm)
                item("View Personnel") {
                    addClass(SideMenuStyle.subMenuItem)
                    whenSelected { controller.setView("PersonsView") }
                }
                item("Create New") {
                    addClass(SideMenuStyle.subMenuItem)
                    whenSelected { controller.setView("CreatePersonnelView") }
                }
            }
            item(text = "Logs", graphic = FontAwesomeIconView(FontAwesomeIcon.BOOK)) {
                addClass(SideMenuStyle.menuItm)
                item("View Logs") {
                    addClass(SideMenuStyle.subMenuItem)
                    whenSelected { controller.setView("LogView") }
                }
            }
        }

    }
}
