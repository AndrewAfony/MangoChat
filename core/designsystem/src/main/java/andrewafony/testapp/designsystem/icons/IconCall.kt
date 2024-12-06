package andrewafony.testapp.designsystem.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val IconCall: ImageVector
    get() {
        if (_Phone != null) {
            return _Phone!!
        }
        _Phone = ImageVector.Builder(
            name = "Phone",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = null,
                fillAlpha = 1.0f,
                stroke = SolidColor(Color(0xFF0F172A)),
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(2.25f, 6.75f)
                curveTo(2.25f, 15.0343f, 8.9657f, 21.75f, 17.25f, 21.75f)
                horizontalLineTo(19.5f)
                curveTo(20.7426f, 21.75f, 21.75f, 20.7426f, 21.75f, 19.5f)
                verticalLineTo(18.1284f)
                curveTo(21.75f, 17.6121f, 21.3987f, 17.1622f, 20.8979f, 17.037f)
                lineTo(16.4747f, 15.9312f)
                curveTo(16.0355f, 15.8214f, 15.5734f, 15.9855f, 15.3018f, 16.3476f)
                lineTo(14.3316f, 17.6412f)
                curveTo(14.05f, 18.0166f, 13.563f, 18.1827f, 13.1223f, 18.0212f)
                curveTo(9.8154f, 16.8098f, 7.1902f, 14.1846f, 5.9788f, 10.8777f)
                curveTo(5.8173f, 10.437f, 5.9834f, 9.95f, 6.3588f, 9.6684f)
                lineTo(7.65242f, 8.69818f)
                curveTo(8.0145f, 8.4266f, 8.1786f, 7.9645f, 8.0688f, 7.5253f)
                lineTo(6.96304f, 3.10215f)
                curveTo(6.8378f, 2.6013f, 6.3879f, 2.25f, 5.8716f, 2.25f)
                horizontalLineTo(4.5f)
                curveTo(3.2574f, 2.25f, 2.25f, 3.2574f, 2.25f, 4.5f)
                verticalLineTo(6.75f)
                close()
            }
        }.build()
        return _Phone!!
    }

private var _Phone: ImageVector? = null

