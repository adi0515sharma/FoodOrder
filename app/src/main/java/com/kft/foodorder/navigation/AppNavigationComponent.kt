package com.kft.foodorder.navigation

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kft.foodorder.local.FoodDB
import com.kft.foodorder.screens.Details.DetailScreen
import com.kft.foodorder.screens.Home.HomeScreen
import com.kft.foodorder.screens.MainActivityViewModel
import com.kft.foodorder.screens.Result
import kotlinx.coroutines.flow.collectLatest


sealed class AppScreens(
    val route: String,
    val title: String,
) {
    object HomePage : AppScreens("HomePage", "Home",)
    object DetailPage : AppScreens("DetailPage", "Detail",)
}



@Composable
fun AppNavigationComponent(
    modifier: Modifier,
    navController: NavController,
    mainActivityViewModel: MainActivityViewModel
){


    NavHost(
        navController = navController as NavHostController,
        startDestination = AppScreens.HomePage.route,
        modifier = modifier.fillMaxSize()
    ) {
        composable(route = AppScreens.HomePage.route) {

            HomeScreen(
                mainActivityViewModel,
            ) {
                navController.navigate(it)
            }
        }

        composable(
            route = "${AppScreens.DetailPage.route}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: return@composable

            var currentFood by rememberSaveable {
                mutableStateOf<FoodDB?>(null)
            }

            var isLike by rememberSaveable {
                mutableStateOf<Boolean>(false)
            }

            var cart by rememberSaveable {
                mutableStateOf<Int>(0)
            }

            LaunchedEffect(key1 = id){
                mainActivityViewModel.getCurrentItem(id).collectLatest {
                    if(currentFood == null){
                        currentFood = it
                    }
                    isLike = it.isFavorite
                    cart = it.cart
                }
            }




            if(currentFood == null){
                return@composable
            }

            DetailScreen(currentFood!!, isLike, cart,  {
                mainActivityViewModel.changeFavorite(currentFood!!)
            },{
                mainActivityViewModel.changeCart(currentFood!!, it)
            }) {
                navController.popBackStack()
            }
        }
    }
}
