package com.example.navigationexamples.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.navigationexamples.components.IPageComponent

@Composable
fun PageContent(component: IPageComponent) {
    Text(component.model.title)
}
