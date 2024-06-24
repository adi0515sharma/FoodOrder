package com.kft.foodorder.screens.Home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.kft.foodorder.local.FoodDB
import com.kft.foodorder.navigation.BottomBar
import com.kft.foodorder.navigation.HomeBottomNavigationComponent
import com.kft.foodorder.screens.MainActivityViewModel

@Composable
fun HomeScreen(
    mainActivityViewModel : MainActivityViewModel,
    navigate: (String) -> Unit){
    val navController = rememberNavController()



    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomBar(navController = navController)
        },
        containerColor = MaterialTheme.colorScheme.primary
    ){
        HomeBottomNavigationComponent(Modifier.padding(it), navController, mainActivityViewModel,{navigate(it)})
    }
}
