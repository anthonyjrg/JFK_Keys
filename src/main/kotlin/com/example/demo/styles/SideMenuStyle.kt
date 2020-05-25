package com.example.demo.app

import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import tornadofx.*

class SideMenuStyle : Stylesheet() {
    companion object {
        val menuItm by cssclass()
        val subMenuItem by cssclass()
        val menuContainer by cssclass()
        val h1 by cssclass()
        val h2 by cssclass()
        val h4 by cssclass()
        val panel by cssclass()
    }

    init {
        menuItm {
            fontWeight = FontWeight.BOLD
            fontSize = 20.px
            textFill = c("#12b4b4")
            backgroundColor = multi(Color.TRANSPARENT)
            padding = box(20.px, 0.px, 10.px, 0.px)
        }
        subMenuItem {
            fontSize = 16.px
            backgroundColor = multi(Color.TRANSPARENT)
            padding = box(5.px, 40.px)
        }
        menuContainer {
            padding = box(40.px, 10.px, 30.px, 30.px)
        }
    }

    init {
        h1 {
            fontSize = 30.px
            fill = c("#454545")
        }

        h2 {
            fontSize = 30.px
            fill = c("#1f177a")
            fontWeight = FontWeight.BOLD
        }
        h4{
            fontSize = 18.px
            fill = c("#454545")
            padding =box(10.px, 0.px)
        }
        Styles.headings {

        }
        panel{
            padding = box(20.px)
            backgroundColor = multi(c("#ececec"))
            borderRadius =  multi(box(10.px))
        }
    }

}