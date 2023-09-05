package com.bianchini.vinicius.matheus.cupcake

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bianchini.vinicius.matheus.cupcake.feature.signup.SignUpScreen
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
                    SetupNavGraph(navHostController = navController)
                }
            }
        }
    }
}

@Composable
fun CupcakeApp() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        SignUpScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CupcakeTheme {
        CupcakeApp()
    }
}