package com.fraporitmos.yapechallengue.presentation.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


data class Dimens(
    val extraSmall: Dp = 0.dp,
    val small1: Dp = 0.dp,
    val small2: Dp = 0.dp,
    val small3: Dp = 0.dp,
    val medium1: Dp = 0.dp,
    val medium2: Dp = 0.dp,
    val medium3: Dp = 0.dp,
    val large: Dp = 0.dp,
    val buttonHeight: Dp = 40.dp,
    val imageCardHeight: Dp = 130.dp,
    val imageCardWidth : Dp = 200.dp,
    val lineHeight : TextUnit = 16.sp,
    val heightImageDetail : Dp = 360.dp,
    val iconSize : Dp = 28.dp,
    val paddingCard : Dp = 4.dp
)

val CompactSmallDimens = Dimens(
    small1 = 6.dp,
    small2 = 5.dp,
    small3 = 8.dp,
    medium1 = 15.dp,
    medium2 = 26.dp,
    medium3 = 30.dp,
    large = 45.dp,
    buttonHeight = 30.dp,
    imageCardHeight = 100.dp,
    imageCardWidth = 140.dp,
    lineHeight = 15.sp,
    heightImageDetail = 300.dp,
    iconSize = 28.dp,
    paddingCard = 4.dp
)

val CompactMediumDimens = Dimens(
    small1 = 8.dp,
    small2 = 13.dp,
    small3 = 17.dp,
    medium1 = 25.dp,
    medium2 = 30.dp,
    medium3 = 46.dp,
    large = 65.dp,
    buttonHeight = 35.dp,
    imageCardHeight = 140.dp,
    imageCardWidth = 180.dp,
    lineHeight = 16.sp,
    heightImageDetail = 320.dp,
    iconSize = 38.dp,
    paddingCard = 4.dp

)

val CompactDimens = Dimens(
    small1 = 10.dp,
    small2 = 15.dp,
    small3 = 20.dp,
    medium1 = 30.dp,
    medium2 = 36.dp,
    medium3 = 48.dp,
    large = 80.dp,
    buttonHeight = 40.dp,
    imageCardHeight = 200.dp,
    imageCardWidth = 220.dp,
    lineHeight = 16.sp,
    heightImageDetail = 340.dp,
    iconSize = 44.dp,
    paddingCard = 4.dp

)



val ExpandedDimens = Dimens(
    small1 = 15.dp,
    small2 = 20.dp,
    small3 = 25.dp,
    medium1 = 35.dp,
    medium2 = 30.dp,
    medium3 = 56.dp,
    large = 130.dp,
    imageCardHeight = 340.dp,
    imageCardWidth = 440.dp,
    lineHeight = 24.sp,
    heightImageDetail = 500.dp,
    iconSize = 54.dp,
    paddingCard = 8.dp

)
