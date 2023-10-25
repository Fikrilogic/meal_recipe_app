package com.fikrisandi.recipeapp.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.fikrisandi.recipeapp.provider.AppNavigationProvider
import com.fikrisandi.theme.AppTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.dependency

@Composable
fun MainRoot(onFinish: () -> Unit){
    val navController = rememberNavController()

//    val currentNavigationStackEntryState by navController.currentBackStackEntryAsState()
//    val destination =
//        currentNavigationStackEntryState?.destination?.route ?: RootNavGraph.startRoute.route
//
//    if (destination == RootNavGraph.startRoute.route) {
//        BackHandler {
//            onFinish.invoke()
//        }
//    }


    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            DestinationsNavHost(
                navGraph = RootNavGraph,
                navController = navController,
                dependenciesContainerBuilder ={
                    dependency(AppNavigationProvider(navController))
                }
            )
        }
    }
}