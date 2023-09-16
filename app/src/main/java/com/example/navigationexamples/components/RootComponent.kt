package com.example.navigationexamples.components

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

class RootComponent(
    componentContext: ComponentContext,
) : IRootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    private val stack =
        childStack(
            source = navigation,
            initialStack = { listOf(Config.Page) },
            childFactory = ::child,
        )

    override val routerState: Value<ChildStack<*, IRootComponent.Child>> = stack

    private fun child(config: Config, componentContext: ComponentContext): IRootComponent.Child =
        when (config) {
            Config.Page -> IRootComponent.Child.PageChild(PageComponent())
        }

    private sealed interface Config : Parcelable {
        @Parcelize
        object Page : Config
    }
}
