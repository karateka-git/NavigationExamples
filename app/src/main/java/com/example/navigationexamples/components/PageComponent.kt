package com.example.navigationexamples.components

class PageComponent(private val number: Int) : IPageComponent {

    override val model: PageData
        get() = PageData("Test $number")
}
