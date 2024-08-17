package com.viktoriagavrosh.fairytales.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.viktoriagavrosh.fairytales.R

val Popping = FontFamily(
    Font(R.font.lxgw_wen_kia_mono_tc_regular, FontWeight.Normal),
    Font(R.font.lxgw_wen_kia_mono_tc_bold, FontWeight.Bold)
)

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = Popping,
        fontWeight = FontWeight.Bold,
        fontSize = 40.sp,
        //lineHeight = 30.sp
    ),
    displaySmall = TextStyle(
        fontFamily = Popping,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = Popping,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        //lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    labelSmall = TextStyle(
        fontFamily = Popping,
        fontWeight = FontWeight.Normal,
        fontSize = 11.sp,
        //lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)
