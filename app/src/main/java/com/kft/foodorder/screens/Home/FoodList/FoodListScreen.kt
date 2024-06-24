package com.kft.foodorder.screens.Home.FoodList

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kft.foodorder.R
import com.kft.foodorder.local.FoodDB
import com.kft.foodorder.navigation.AppScreens
import com.kft.foodorder.ui.Components.FoodItemComponent


@Composable
fun FoodListScreen(
    foodList : List<FoodDB>,
    getFoodList : () -> Unit,
    navigate: (String) -> Unit,
    ) {

    LaunchedEffect(key1 = null){
        getFoodList()
    }

    Column(modifier = Modifier.fillMaxSize()) {

        Column (
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {


                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Text(
                        text = "Home",
                        fontFamily = FontFamily(Font(R.font.abee_zee_italic)),
                        fontSize = 20.sp,
                        color = Color(0xff000000)
                    )
                }


            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(color = Color.Black)
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            itemsIndexed(foodList) { index, item ->
                FoodItemComponent(modifier = Modifier, foodDB = item) {
                    navigate("${AppScreens.DetailPage.route}/${item.meal_id}")
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }

    }
}