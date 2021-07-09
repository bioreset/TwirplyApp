package com.dariusz.twirplyapp.presentation.components.theme

import androidx.compose.runtime.saveable.Saver
import com.dariusz.twirplyapp.domain.model.AppTheme

val ThemeSaver = Saver<AppTheme, Map<String, Int>>(
    save = { theme ->
        mapOf(
            PrimaryColorKey to theme.primaryColor.ordinal,
            SecondaryColorKey to theme.secondaryColor.ordinal,
            ShapesFamilyKey to theme.shapesFamily.ordinal,
            MainTypographyKey to theme.mainTypography.ordinal,
        )
    },
    restore = { map ->
        AppTheme(
            primaryColor = ThemeColor.values()[map[PrimaryColorKey]!!],
            secondaryColor = ThemeColor.values()[map[SecondaryColorKey]!!],
            shapesFamily = ThemeShapesFamily.values()[map[ShapesFamilyKey]!!],
            mainTypography = ThemeTypography.values()[map[MainTypographyKey]!!],
        )
    }
)

private const val PrimaryColorKey = "primaryColor"
private const val SecondaryColorKey = "secondaryColor"
private const val ShapesFamilyKey = "shapeFamily"
private const val MainTypographyKey = "mainTypography"