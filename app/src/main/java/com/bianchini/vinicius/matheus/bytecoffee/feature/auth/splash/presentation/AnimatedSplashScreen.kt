package com.bianchini.vinicius.matheus.bytecoffee.feature.auth.splash.presentation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.bianchini.vinicius.matheus.bytecoffee.R
import com.bianchini.vinicius.matheus.bytecoffee.feature.auth.commun.viewmodel.AuthViewModel
import com.bianchini.vinicius.matheus.bytecoffee.graph.AuthScreenRoutes
import com.bianchini.vinicius.matheus.bytecoffee.graph.Graph
import com.bianchini.vinicius.matheus.bytecoffee.ui.theme.Primary
import kotlinx.coroutines.delay

@Composable
fun AnimatedSplashScreen(
    navController: NavController,
    authViewModel: AuthViewModel,
) {
    val isLoggedProfile = authViewModel.isLoggedProfile.collectAsStateWithLifecycle()

    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 3000
        ),
        label = "",
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(3000)
        if (isLoggedProfile.value) {
            navController.popBackStack()
            navController.navigate(Graph.HOME)
        } else {
            navController.popBackStack()
            navController.navigate(AuthScreenRoutes.Login.route)
        }
    }
    Splash(alpha = alphaAnim.value)
}

@Composable
fun Splash(alpha: Float) {
    Box(
        modifier = Modifier
            .background(Primary)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier
                .size(120.dp)
                .alpha(alpha = alpha),
            imageVector = Icons.Filled.Coffee,
            contentDescription = stringResource(id = R.string.app_logo),
            tint = Color.White
        )
    }
}