package com.kft.foodorder.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.kft.foodorder.navigation.AppNavigationComponent
import com.kft.foodorder.ui.theme.FoodOrderTheme

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: MainActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = MainActivityViewModelFactory(this)

        viewModel = ViewModelProvider(this, factory).get(MainActivityViewModel::class.java)

        setContent {
            FoodOrderTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
//                val foodItem1 = FoodDB(
//                    id = 1,
//                    img = "https://www.foodiesfeed.com/wp-content/uploads/2023/08/crispy-spicy-chicken-wings.jpg",
//                    food_name = "Pizza Margherita",
//                    food_category = "Italian",
//                    rp_field = "Rp 10.000",
//                    time = "30 mins",
//                    distance = "5 km",
//                    rating = 4.5,
//                    cart = 2,
//                    isFavorite = true
//                )
                Scaffold(modifier = Modifier.background(MaterialTheme.colorScheme.primary)){
                    Column(modifier = Modifier.padding(it).wrapContentHeight()) {
                        AppNavigationComponent(Modifier, navController, viewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FoodOrderTheme {
        Greeting("Android")
    }
}