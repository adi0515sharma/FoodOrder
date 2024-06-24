package com.kft.foodorder.ui.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.kft.foodorder.R
import com.kft.foodorder.local.FoodDB


@Composable
fun FoodItemComponent(modifier: Modifier, foodDB: FoodDB, onClick : ()->Unit){
    
    Row(
        modifier = modifier
        .padding(horizontal = 5.dp)
        .fillMaxWidth()
        .clickable { onClick() },

    ) {
        
        AsyncImage(
            model = foodDB.img,
            contentDescription = "Food Image",
            modifier = Modifier
                .fillMaxWidth(0.30f)
                .clip(RoundedCornerShape(12.dp))
        )

        Spacer(modifier = Modifier.width(15.dp))
        Column (
            modifier = Modifier
                .fillMaxWidth(1f), // 70% width
            verticalArrangement = Arrangement.SpaceAround
        ){
            Spacer(modifier = Modifier.height(3.dp))

            Text(
                text = foodDB.food_name,
                fontFamily = FontFamily(Font(R.font.plus_jakarta_sans_bold)),
                fontSize = 20.sp,
                color = Color(0xff121212)
            )

            Spacer(modifier = Modifier.height(7.dp))

            Text(
                text = foodDB.food_category,
                fontFamily = FontFamily(Font(R.font.plus_jakarta_sans_medium)),
                fontSize = 16.sp,
                color = Color(0xff999999),
                style = TextStyle(
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false
                    )
                )
            )
            Spacer(modifier = Modifier.height(9.dp))

            Row(verticalAlignment = Alignment.CenterVertically){
                Image(
                    painter = painterResource(id = R.drawable.two_wheeler),
                    contentDescription = "two wheeler icon",
                    modifier = Modifier.size(22.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))

                Text(
                    text = foodDB.rp_field,
                    fontFamily = FontFamily(Font(R.font.plus_jakarta_sans_bold)),
                    fontSize = 16.sp,
                    color = Color(0xff121212),
                    style = TextStyle(
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false
                        )
                    )
                )
            }
            Spacer(modifier = Modifier.height(9.dp))

            Row(verticalAlignment = Alignment.CenterVertically){
                Image(
                    painter = painterResource(id = R.drawable.schedule),
                    contentDescription = "schedule icon",
                    modifier = Modifier.size(22.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))

                Text(
                    text = foodDB.time,
                    fontFamily = FontFamily(Font(R.font.plus_jakarta_sans_semi_bold)),
                    fontSize = 16.sp,
                    color = Color(0xff999999),
                    style = TextStyle(
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false
                        )
                    )
                )
                Spacer(modifier = Modifier.width(3.dp))

                Text(
                    text = "•",
                    fontFamily = FontFamily(Font(R.font.plus_jakarta_sans_semi_bold)),
                    fontSize = 16.sp,
                    color = Color(0xff999999),
                    style = TextStyle(
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false
                        )
                    )
                )
                Spacer(modifier = Modifier.width(3.dp))

                Text(
                    text = foodDB.distance,
                    fontFamily = FontFamily(Font(R.font.plus_jakarta_sans_semi_bold)),
                    fontSize = 16.sp,
                    color = Color(0xff999999),
                    style = TextStyle(
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false
                        )
                    )
                )

            }

            Spacer(modifier = Modifier.height(9.dp))

            Row(verticalAlignment = Alignment.CenterVertically){
                Image(
                    painter = painterResource(id = R.drawable.grade),
                    contentDescription = "grade icon",
                    modifier = Modifier.size(22.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))

                Text(
                    text = "${foodDB.rating}",
                    fontFamily = FontFamily(Font(R.font.plus_jakarta_sans_semi_bold)),
                    fontSize = 16.sp,
                    color = Color(0xff999999),
                    style = TextStyle(
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false
                        )
                    )
                )
            }


        }
    }
}


