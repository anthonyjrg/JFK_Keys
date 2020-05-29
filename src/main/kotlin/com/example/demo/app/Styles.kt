package com.example.demo.app

import javafx.scene.effect.BlurType
import javafx.scene.effect.DropShadow
import javafx.scene.effect.InnerShadow
import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import tornadofx.*

class Styles : Stylesheet() {
    companion object {
        val heading by cssclass()
        val label by cssclass()
        val headings by cssclass()
        val panel by cssclass()
        val boxes by cssclass()
    }

    init {
        label and heading {
            padding = box(10.px)
            fontSize = 40.px
            fontWeight = FontWeight.BOLD
        }

        button {
            textFill = c("#6e6e6e")
            minWidth = 150.px
            prefWidth = 150.px
            maxWidth = 150.px
            padding = box(10.px)
            borderRadius = multi(box(0.px))
            backgroundColor = multi(c("#dfdfdf"))
            fontWeight = FontWeight.BOLD

            and(hover) {
                backgroundColor = multi(c("#cdcaca", 0.50))
            }
        }


        panel{
            padding = box(15.px)
            backgroundColor = multi(c("#e7e7e7"))
            borderRadius =  multi(box(10.px))
            backgroundRadius = multi(box(10.px))
            effect = DropShadow(5.0, c("#A6A4A4"))
        }

        boxes{
            padding = box(15.px)
            spacing = 10.px
            backgroundColor = multi(c("#fafafa"))
        }

    }


}