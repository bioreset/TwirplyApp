package com.dariusz.twirplyapp.presentation.components.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.unit.dp

enum class ThemeShapesFamily {
    Rounded
}

fun ThemeShapesFamily.getShapes(): Shapes = when (this) {
    ThemeShapesFamily.Rounded -> MainShapes
}

val MainShapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp)
)