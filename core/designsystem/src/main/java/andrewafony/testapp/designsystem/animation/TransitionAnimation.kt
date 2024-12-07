package andrewafony.testapp.designsystem.animation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.navigation.NavBackStackEntry

val enterPush: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
    slideIntoContainer(
        animationSpec = tween(300, easing = LinearEasing),
        towards = AnimatedContentTransitionScope.SlideDirection.Start,
        initialOffset = { it }
    )
}
val exitPush: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
    scaleOut(targetScale = 0.98f, animationSpec = tween(400)) +
            fadeOut(animationSpec = tween(300, easing = LinearEasing)) +
            slideOutOfContainer(
                animationSpec = tween(300, easing = LinearEasing),
                towards = AnimatedContentTransitionScope.SlideDirection.End,
                targetOffset = { -it / 2 }
            )
}
val enterPop: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
    scaleIn(initialScale = 0.98f, animationSpec = tween(400)) +
            fadeIn(animationSpec = tween(300, easing = LinearEasing)) +
            slideIntoContainer(
                animationSpec = tween(300, easing = LinearEasing),
                towards = AnimatedContentTransitionScope.SlideDirection.Start,
                initialOffset = { -it / 2 }
            )
}
val exitPop: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
    slideOutOfContainer(
        animationSpec = tween(300, easing = LinearEasing),
        towards = AnimatedContentTransitionScope.SlideDirection.End,
        targetOffset = { it }
    )
}