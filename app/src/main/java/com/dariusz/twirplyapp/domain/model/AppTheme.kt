package com.dariusz.twirplyapp.domain.model

import com.dariusz.twirplyapp.presentation.components.theme.ThemeColor
import com.dariusz.twirplyapp.presentation.components.theme.ThemeShapesFamily
import com.dariusz.twirplyapp.presentation.components.theme.ThemeTypography

data class AppTheme(
    val primaryColor: ThemeColor = ThemeColor.Blue,
    val secondaryColor: ThemeColor = ThemeColor.Yellow,
    val shapesFamily: ThemeShapesFamily = ThemeShapesFamily.Rounded,
    val mainTypography: ThemeTypography = ThemeTypography.Main
)