package com.example.navigationexamples.components

interface IPageComponent {
    val model: PageData
}

data class PageData(val title: String)
