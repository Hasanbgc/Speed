package com.apps.speed.presentation.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItemDefaults.contentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


    @Composable
    fun TopAppBar(
        title: String? = null,
        titlePosition: TitlePosition? = TitlePosition.Center,
        titleColor: Color = contentColor,
        titleStyle: TextStyle = MaterialTheme.typography.titleLarge,
        navigationIcon: ImageVector? = null,
        navigationIconColor: Color = contentColor,
        onNavigationClick: (() -> Unit)? = null,
        menuIcons: List<ImageVector>? = null,
        onMenuClick: ((Int) -> Unit)? = null,
        menuIconColors: List<Color>? = null,
        backgroundColor: Color = Color.Green,
        modifier: Modifier = Modifier
    ) {

        Box(
            modifier = modifier
                .fillMaxWidth()
                .background(backgroundColor)
                .padding(WindowInsets.statusBars.asPaddingValues())
                .height(56.dp)
        ) {
            if (navigationIcon != null && onNavigationClick != null) {
                val animator = ScaleInFadeRotateIcon(title.toString())
                Icon(
                    imageVector = navigationIcon,
                    contentDescription = "Navigation Icon",
                    tint = navigationIconColor,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 16.dp)
                        .size(24.dp)
                        .graphicsLayer {
                            //alpha = animator.second
                            rotationZ = animator
                        }
                        .clickable {
                            onNavigationClick.invoke()
                        }
                )

            }
            val StartModifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 44.dp)
            val centerModifier = Modifier.align(Alignment.Center)
            Text(
                text = title ?: "",
                style = TextStyle(
                    color = titleColor,
                    fontSize = 20.sp,
                    fontStyle = titleStyle.fontStyle
                ),                            //style = MaterialTheme.typography.titleLarge,
                modifier = if (titlePosition == TitlePosition.Center) centerModifier else StartModifier
            )
            if (menuIcons != null && onMenuClick != null) {
                Row(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(16.dp)
                ) {
                    menuIcons.forEachIndexed { index, imageVector ->
                        Icon(
                            imageVector = imageVector,
                            contentDescription = "Menu Icon",
                            tint = menuIconColors?.get(index) ?: contentColor,
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .size(24.dp)
                                .clickable { onMenuClick(index) }
                        )
                    }
                }

            }

        }

    }

    enum class TitlePosition {
        Start, Center
    }

//Could be use instead of enum class
/*sealed class TitlebarPosition(valposition: String) {
    object Start : TitlebarPosition("Start")
    object Center : TitlebarPosition("Center")
}*/
@Composable
fun ScaleInFadeRotateIcon(title: String):Float /*Triple<Dp,Float,Float>*/ {
   // var title1 by remember { mutableStateOf(title) }

    val transition = updateTransition(targetState = title!="Home", label = "transition")

    /*val size by transition.animateDp(
        label = "sizeAnim",
        transitionSpec = { tween(durationMillis = 500) }
    ) { if (it) 24.dp else 24.dp }

    val alpha by transition.animateFloat(
        label = "alphaAnim",
        transitionSpec = { tween(durationMillis = 500) }
    ) { if (it) 1f else 0.3f }*/

    val rotation by transition.animateFloat(
        label = "rotationAnim",
        transitionSpec = { tween(durationMillis = 300) }
    ) { if (it) 360f else 0f } // Full spin on expand

    /*Box(
        modifier = Modifier
            .size(size)
            .alpha(alpha)
            .graphicsLayer {
                rotationZ = rotation
            }
            .background(Color(0xFF00C3FF), shape = CircleShape)
            .clickable { expanded = !expanded }
    )*/
    return rotation /*Triple(size,alpha,rotation)*/
}

