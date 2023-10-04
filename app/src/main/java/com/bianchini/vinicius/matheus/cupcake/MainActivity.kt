package com.bianchini.vinicius.matheus.cupcake

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bianchini.vinicius.matheus.cupcake.graph.RootNavGraph
import com.bianchini.vinicius.matheus.cupcake.ui.theme.Background
import com.bianchini.vinicius.matheus.cupcake.ui.theme.CupcakeTheme

class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CupcakeTheme {
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