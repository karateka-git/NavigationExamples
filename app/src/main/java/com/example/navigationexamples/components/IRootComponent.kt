package com.example.navigationexamples.components

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value

interface IRootComponent {

    val routerState: Value<ChildStack<*, Child>>

    sealed class Child {
        class PageChild(val component: IPageComponent) : Child()
    }
}
