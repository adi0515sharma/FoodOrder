package com.kft.foodorder.ui.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kft.foodorder.local.FoodDB
import coil.compose.AsyncImage
import com.kft.foodorder.R
import com.kft.foodorder.local.CART_ACTION


@Composable
fun CartItemComponent(
    modifier: Modifier,
    foodDB: FoodDB,
    onClick : ()->Unit,
    onCart : (action : CART_ACTION)-> Unit
){

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(10.dp))
            .padding(12.dp)
            .clickable {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically
    ){
        AsyncImage(
            model = foodDB.img,
            contentDescription = "Food Image",
            contentScale = ContentScale.Crop,            // crop the image if it's not a square
            modifier = Modifier
                .size(75.dp)
                .clip(CircleShape)                       // clip to the circle shape
                .border(0.dp, Color.Gray, CircleShape)

        )

        Column(modifier = Modifier
            .fillMaxWidth(1f)
            .padding(horizontal = 10.dp)) {
            Text(
                text = foodDB.food_name,
                fontFamily = FontFamily(Font(R.font.abee_zee_italic)),
                fontSize = 20.sp,
                color = Color(0xff000000)
            )
            Spacer(modifier = Modifier.height(5.dp))
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween){
                Text(
                    text = foodDB.food_category,
                    fontFamily = FontFamily(Font(R.font.abee_zee_italic)),
                    fontSize = 18.sp,
                    color = Color(0xffFA4A0C)
                )

                Row (modifier = Modifier
                    .background(
                        shape = RoundedCornerShape(10.dp),
                        color = Color(0xFFFA4A0C)
                    )
                    .padding(vertical = 5.dp, horizontal = 8.dp),

                    verticalAlignment = Alignment.CenterVertically

                ){
                    Text(
                        text = "-",
                        fontFamily = FontFamily(Font(R.font.abee_zee_italic)),
                        fontSize = 20.sp,
                        color = Color(0xffF4F4F7),
                        modifier = Modifier.clickable { onCart(CART_ACTION.MINUS) }.padding(
                            PaddingValues(start = 4.dp, end = 6.dp, top = 1.dp, bottom = 1.dp)
                        )
                    )
                    Text(
                        text = "${foodDB.cart}",
                        fontFamily = FontFamily(Font(R.font.abee_zee_italic)),
                        fontSize = 18.sp,
                        color = Color(0xffF4F4F7)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = "+",
                        fontFamily = FontFamily(Font(R.font.abee_zee_italic)),
                        fontSize = 20.sp,
                        color = Color(0xffF4F4F7),
                        modifier = Modifier.clickable { onCart(CART_ACTION.ADD) }.padding(
                            PaddingValues(start = 5.dp, end = 4.dp, top = 1.dp, bottom = 1.dp)
                        )
                    )
                }
            }

        }
    }
}