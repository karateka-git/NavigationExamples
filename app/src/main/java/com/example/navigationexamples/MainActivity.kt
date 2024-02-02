package com.example.navigationexamples

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.defaultComponentContext
import com.example.navigationexamples.components.RootComponent
import com.example.navigationexamples.ui.theme.NavigationExamplesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val rootComponent = RootComponent(defaultComponentContext())
        setContent {
            NavigationExamplesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val testClass = remember {
                        mutableStateOf(
                            TestClass(listOf("Первый"))
                        )
                    }
                    TestContent(testClass = testClass.value, addNewItem = { testClass.value.items = testClass.value.items.plus(it) })
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NavigationExamplesTheme {
        Greeting("Android")
    }
}

@Stable
class TestClass(initialList: List<String>) {
    var items by mutableStateOf(initialList)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestContent(testClass: TestClass, addNewItem: (String) -> Unit = {}) {
    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
    ) {
        var newItem by remember { mutableStateOf("") }
        TextField(
            value = newItem,
            onValueChange = { newItem = it },
        )
        Row() {
            Button(
                onClick = { addNewItem(newItem) },
            ) {
                Text(text = "Добавить элемент")
            }
        }
        testClass.items.forEach {
            Text(modifier = Modifier.padding(16.dp), text = it)
        }
    }
}
