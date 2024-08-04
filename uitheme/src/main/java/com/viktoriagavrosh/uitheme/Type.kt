package com.viktoriagavrosh.uitheme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val LxFont = FontFamily(
    Font(R.font.lxgw_wen_kai_mono_tc_regular, FontWeight.Normal),
    Font(R.font.lxgw_wen_kai_mono_tc_bold, FontWeight.Bold)
)

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = LxFont,
        fontWeight = FontWeight.Bold,
        fontSize = 40.sp,
    ),
    displaySmall = TextStyle(
        fontFamily = LxFont,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = LxFont,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        letterSpacing = 0.5.sp
    ),
    labelSmall = TextStyle(
        fontFamily = LxFont,
        fontWeight = FontWeight.Normal,
        fontSize = 11.sp,
        letterSpacing = 0.5.sp
    )
)
