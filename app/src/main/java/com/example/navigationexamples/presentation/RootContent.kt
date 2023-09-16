package com.example.navigationexamples.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.example.navigationexamples.components.IRootComponent

@Composable
fun RootContent(component: IRootComponent) {
    Column(modifier = Modifier.fillMaxSize()) {
        Children(
            stack = component.routerState,
            modifier = Modifier.weight(weight = 1F),
        ) {
            when (val child = it.instance) {
                is IRootComponent.Child.PageChild -> PageContent(child.component)
            }
        }
    }
}
