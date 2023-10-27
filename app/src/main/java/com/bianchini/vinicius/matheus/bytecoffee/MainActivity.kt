package com.bianchini.vinicius.matheus.bytecoffee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bianchini.vinicius.matheus.bytecoffee.graph.RootNavGraph
import com.bianchini.vinicius.matheus.bytecoffee.ui.theme.Background
import com.bianchini.vinicius.matheus.bytecoffee.ui.theme.ByteCoffeeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ByteCoffeeTheme {
                navController = rememberNavController()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Background
                ) {
                    RootNavGraph(navHostController = navController)
                }
            }
        }
    }
}