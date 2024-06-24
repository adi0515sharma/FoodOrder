package com.kft.foodorder.navigation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.kft.foodorder.local.CART_ACTION
import com.kft.foodorder.local.FoodDB
import com.kft.foodorder.screens.Home.Cart.CartScreen
import com.kft.foodorder.screens.Home.Favourite.FavouriteScreen
import com.kft.foodorder.screens.Home.FoodList.FoodListScreen
import com.kft.foodorder.screens.MainActivityViewModel
import kotlinx.coroutines.flow.collectLatest


sealed class HomeScreens(
    val route: String,
    val title: String,
    val filled: ImageVector
) {
    object Favorite : HomeScreens("FAVOURITE", "Favorite", Icons.Filled.Favorite)
    object Home : HomeScreens("FOOD_LIST", "Home", Icons.Filled.Home)
    object Cart : HomeScreens("CART", "Cart", Icons.Filled.ShoppingCart)
}


val bottomNavScreen = listOf(
    HomeScreens.Home,
    HomeScreens.Favorite,
    HomeScreens.Cart
)


@Composable
fun HomeBottomNavigationComponent(
    modifier: Modifier,
    navController : NavController,
    mainActivityViewModel : MainActivityViewModel,
    navigate: (String) -> Unit
){


    val foodList by mainActivityViewModel.myFoodList.collectAsState()

    val favouriteList by mainActivityViewModel.myFavouriteFoodList.collectAsState()


    NavHost(
        navController = navController as NavHostController,
        startDestination = HomeScreens.Home.route,
        modifier = modifier.fillMaxSize()
    ) {
        composable(route = HomeScreens.Home.route) {
            FoodListScreen(foodList,
                {
                    mainActivityViewModel.getAllFoods()
                },
                {
                    navigate(it)
                }
            )
        }

        composable(route = HomeScreens.Favorite.route) {
            FavouriteScreen(favouriteList,
                {
                    mainActivityViewModel.getFavoriteList()
                },
                {
                    navController.popBackStack()
                },
                {
                    navigate(it)
                }
            )
        }

        composable(route = HomeScreens.Cart.route) {

            var cartList by rememberSaveable {
                mutableStateOf<MutableList<FoodDB>?>(null)
            }
            LaunchedEffect(key1 = null){
                mainActivityViewModel.getAllCarts().collectLatest {
                    Log.e("FoodDB", " new  cart  $it")
                    cartList = it as MutableList<FoodDB>


                }
            }

            if(cartList == null){
                return@composable
            }
            CartScreen(
               cartList!! ,

                {
                    navigate(it)
                },
                {
                    navController.popBackStack()
                },
                {
                    action, foodDb ->
                        Log.d("FoodDB", "click cart")
                        mainActivityViewModel.changeCart(foodDb, action)
                        if(action == CART_ACTION.ADD){
                            val updatedCartList = cartList!!.toMutableList()
                            updatedCartList[cartList!!.indexOf(foodDb)] = updatedCartList[cartList!!.indexOf(foodDb)].copy(
                                cart = updatedCartList[cartList!!.indexOf(foodDb)].cart + 1 // Example: Incrementing cart field
                            )
                            cartList = updatedCartList
                        }
                        else if(action == CART_ACTION.MINUS){
                            val updatedCartList = cartList!!.toMutableList()
                            updatedCartList[cartList!!.indexOf(foodDb)] = updatedCartList[cartList!!.indexOf(foodDb)].copy(
                                cart = updatedCartList[cartList!!.indexOf(foodDb)].cart - 1 // Example: Incrementing cart field
                            )
                            cartList = updatedCartList
                        }


                }
            )
        }

    }
}




@Composable
fun BottomBar(  navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp, top = 3.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        bottomNavScreen.forEach {


            Row(modifier = Modifier
                .weight(1f)
                .clickable {
                    navController.navigate(it.route)
                },
                horizontalArrangement = Arrangement.Center,){

                Image(
                    painter = rememberVectorPainter(image = it.filled),
                    contentDescription = it.title,
                    modifier = Modifier.size(30.dp),
                    colorFilter = if(currentRoute == it.route)  ColorFilter.tint(MaterialTheme.colorScheme.secondary) else null
                )
            }
        }
    }
}




