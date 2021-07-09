package com.dariusz.twirplyapp.presentation.components.theme

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.dariusz.twirplyapp.domain.model.AppTheme
import com.dariusz.twirplyapp.utils.ColorUtils.onColor
import com.dariusz.twirplyapp.utils.ColorUtils.variantColor

@Composable
fun TwirplyAppTheme(
    theme: AppTheme,
    content: @Composable () -> Unit
) {
    val darkTheme = isSystemInDarkTheme()
    val themePrimaryColor = theme.primaryColor.getColors(darkTheme)
    val themeSecondaryColor = theme.secondaryColor.getColors(darkTheme)
    val primaryColor = animateColorAsState(themePrimaryColor)
    val primaryVariantColor = animateColorAsState(themePrimaryColor.variantColor())
    val onPrimaryColor = animateColorAsState(themePrimaryColor.onColor())
    val secondaryColor = animateColorAsState(themeSecondaryColor)
    val secondaryVariantColor = animateColorAsState(themeSecondaryColor.variantColor())
    val onSecondaryColor = animateColorAsState(themeSecondaryColor.onColor())
    val mainShapes = theme.shapesFamily.getShapes()
    val mainTypography = theme.mainTypography.getTypography()
    val colors = if (!darkTheme) {
        lightColors(
            primary = primaryColor.value,
            primaryVariant = primaryVariantColor.value,
            onPrimary = onPrimaryColor.value,
            secondary = secondaryColor.value,
            secondaryVariant = secondaryVariantColor.value,
            onSecondary = onSecondaryColor.value
        )
    } else {
        darkColors(
            primary = primaryColor.value,
            primaryVariant = primaryVariantColor.value,
            onPrimary = onPrimaryColor.value,
            secondary = secondaryColor.value,
            onSecondary = onSecondaryColor.value
        )
    }
    MaterialTheme(
        colors = colors,
        typography = mainTypography,
        shapes = mainShapes,
        content = content
    )
}