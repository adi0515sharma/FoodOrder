package com.kft.foodorder.ui.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kft.foodorder.R


@Composable
fun ButtonComponent(text : String, onClick : () -> Unit){

    Row(
        modifier = Modifier
        .background(color = Color(0xFFFA4A0C), shape = RoundedCornerShape(20.dp))
        .clickable { onClick() }
        .fillMaxWidth()
        .padding(vertical = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ){

        Text(
            text = text,
            fontFamily = FontFamily(Font(R.font.abee_zee_regular)),
            fontSize = 20.sp,
            color = Color(0xffF6F6F9)
        )
    }
}