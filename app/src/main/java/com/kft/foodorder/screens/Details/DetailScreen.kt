package com.kft.foodorder.screens.Details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kft.foodorder.R
import com.kft.foodorder.local.FoodDB
import coil.compose.AsyncImage
import com.kft.foodorder.local.CART_ACTION
import com.kft.foodorder.navigation.BottomBar
import com.kft.foodorder.ui.Components.ButtonComponent


@Composable
fun DetailScreen(currentFood : FoodDB, isLike : Boolean, cart : Int, updateLike: () -> Unit, updateCart : (action : CART_ACTION) -> Unit,  navigate: () -> Unit){


    Scaffold(
        modifier = Modifier.fillMaxSize(),

        containerColor = MaterialTheme.colorScheme.primary
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .padding(20.dp),

            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.chevron_left),
                        contentDescription = "back button",
                        modifier = Modifier.size(24.dp).clickable {
                            navigate()
                        }
                    )


                    Image(
                        painter = painterResource(id = if (isLike) R.drawable.filled_heart else R.drawable.heart),
                        contentDescription = "like button",
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                updateLike()
                            },
                        colorFilter = ColorFilter.tint(Color.Red),

                        )

                }

                Spacer(modifier = Modifier.height(20.dp))

                AsyncImage(
                    model = currentFood.img,
                    contentDescription = "Food Image",
                    contentScale = ContentScale.Crop,            // crop the image if it's not a square
                    modifier = Modifier
                        .size(240.dp)
                        .clip(CircleShape)                       // clip to the circle shape
                        .border(0.dp, Color.Gray, CircleShape)
                )

                Spacer(modifier = Modifier.height(10.dp))


                Text(
                    text = currentFood.food_name,
                    fontFamily = FontFamily(Font(R.font.abee_zee_italic)),
                    fontSize = 30.sp,
                    color = Color(0xff000000)
                )

                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = currentFood.food_category,
                    fontFamily = FontFamily(Font(R.font.abee_zee_italic)),
                    fontSize = 22.sp,
                    color = Color(0xffFA4A0C)
                )

                Spacer(modifier = Modifier.height(15.dp))

                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Delivery info",
                        fontFamily = FontFamily(Font(R.font.abee_zee_italic)),
                        fontSize = 19.sp,
                        color = Color(0xff000000)
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Delivered between monday aug and thursday 20 from 8pm to 91:32 pm",
                        fontFamily = FontFamily(Font(R.font.abel_regular)),
                        fontSize = 17.sp,
                        color = Color(0xff999999)
                    )
                }


                Spacer(modifier = Modifier.height(20.dp))

                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Return policy",
                        fontFamily = FontFamily(Font(R.font.abee_zee_italic)),
                        fontSize = 19.sp,
                        color = Color(0xff000000)
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "All our foods are double checked before leaving our stores so by any case you found a broken food please contact our hotline immediately.",
                        fontFamily = FontFamily(Font(R.font.abel_regular)),
                        fontSize = 17.sp,
                        color = Color(0xff999999)
                    )
                }
            }



            ButtonComponent(
                text = if (cart > 0) "Remove from cart" else "Add to cart",
                onClick = {
                    if (cart > 0) {
                        updateCart(CART_ACTION.REMOVE)
                    } else {
                        updateCart(CART_ACTION.ADD)
                    }

                }
            )
        }
    }
}