package com.alanvan.gues_the_breed.ui.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.alanvan.gues_the_breed.R

internal val interRegularFont = Font(R.font.inter_regular, FontWeight.Normal)
internal val interMediumFont = Font(R.font.inter_medium, FontWeight.Medium)
internal val interSemiBoldFont = Font(R.font.inter_semibold, FontWeight.SemiBold)
internal val interBoldFont = Font(R.font.inter_bold, FontWeight.Bold)

val interFontFamily: FontFamily = FontFamily(
    interRegularFont,
    interMediumFont,
    interSemiBoldFont,
    interBoldFont
)
