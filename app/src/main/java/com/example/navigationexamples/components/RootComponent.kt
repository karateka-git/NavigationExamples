package com.example.navigationexamples.components

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
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
            handleBackButton = true,
            initialConfiguration = Config.Page(0),
            childFactory = ::child,
        )

    override val routerState: Value<ChildStack<*, IRootComponent.Child>> = stack

    override fun nextPage() {
        navigation.push(Config.Page(stack.value.items.size))
    }
    override fun prevPage() {
        navigation.pop()
    }

    private fun child(config: Config, componentContext: ComponentContext): IRootComponent.Child =
        when (config) {
            is Config.Page -> IRootComponent.Child.PageChild(PageComponent(config.number))
        }

    private sealed interface Config : Parcelable {
        @Parcelize
        data class Page(val number: Int) : Config
    }
}
