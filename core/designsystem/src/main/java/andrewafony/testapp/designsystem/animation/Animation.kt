package andrewafony.testapp.designsystem.animation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally

object Animation {

    val slideInWithScaleAndFade: EnterTransition = slideInHorizontally(
        initialOffsetX = { -it },
        animationSpec = tween(300, easing = LinearEasing)
    ) + scaleIn(initialScale = 0.98f, animationSpec = tween(400)) +
        fadeIn(animationSpec = tween(300, easing = LinearEasing))

    val slideOutWithScaleAndFade: ExitTransition = slideOutHorizontally(
        animationSpec = tween(300, easing = LinearEasing),
        targetOffsetX = { -it / 2 }
    ) + scaleOut(targetScale = 0.98f, animationSpec = tween(400)) +
        fadeOut(animationSpec = tween(300, easing = LinearEasing))

}