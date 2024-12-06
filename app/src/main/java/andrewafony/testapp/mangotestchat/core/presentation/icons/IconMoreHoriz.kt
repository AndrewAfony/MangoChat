package andrewafony.testapp.mangotestchat.core.presentation.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val More_horiz: ImageVector
    get() {
        if (_More_horiz != null) {
            return _More_horiz!!
        }
        _More_horiz = ImageVector.Builder(
            name = "More_horiz",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(240f, 560f)
                quadToRelative(-33f, 0f, -56.5f, -23.5f)
                reflectiveQuadTo(160f, 480f)
                reflectiveQuadToRelative(23.5f, -56.5f)
                reflectiveQuadTo(240f, 400f)
                reflectiveQuadToRelative(56.5f, 23.5f)
                reflectiveQuadTo(320f, 480f)
                reflectiveQuadToRelative(-23.5f, 56.5f)
                reflectiveQuadTo(240f, 560f)
                moveToRelative(240f, 0f)
                quadToRelative(-33f, 0f, -56.5f, -23.5f)
                reflectiveQuadTo(400f, 480f)
                reflectiveQuadToRelative(23.5f, -56.5f)
                reflectiveQuadTo(480f, 400f)
                reflectiveQuadToRelative(56.5f, 23.5f)
                reflectiveQuadTo(560f, 480f)
                reflectiveQuadToRelative(-23.5f, 56.5f)
                reflectiveQuadTo(480f, 560f)
                moveToRelative(240f, 0f)
                quadToRelative(-33f, 0f, -56.5f, -23.5f)
                reflectiveQuadTo(640f, 480f)
                reflectiveQuadToRelative(23.5f, -56.5f)
                reflectiveQuadTo(720f, 400f)
                reflectiveQuadToRelative(56.5f, 23.5f)
                reflectiveQuadTo(800f, 480f)
                reflectiveQuadToRelative(-23.5f, 56.5f)
                reflectiveQuadTo(720f, 560f)
            }
        }.build()
        return _More_horiz!!
    }

private var _More_horiz: ImageVector? = null
