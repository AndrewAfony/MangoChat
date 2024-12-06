package andrewafony.testapp.mangotestchat.core.presentation.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val IconPhoto: ImageVector
    get() {
        if (_Camera != null) {
            return _Camera!!
        }
        _Camera = ImageVector.Builder(
            name = "Camera",
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
                moveTo(6.82689f, 6.1749f)
                curveTo(6.4658f, 6.7535f, 5.8613f, 7.134f, 5.186f, 7.2299f)
                curveTo(4.8065f, 7.2839f, 4.4285f, 7.3422f, 4.052f, 7.405f)
                curveTo(2.9991f, 7.5804f, 2.25f, 8.5066f, 2.25f, 9.574f)
                verticalLineTo(18f)
                curveTo(2.25f, 19.2426f, 3.2574f, 20.25f, 4.5f, 20.25f)
                horizontalLineTo(19.5f)
                curveTo(20.7426f, 20.25f, 21.75f, 19.2426f, 21.75f, 18f)
                verticalLineTo(9.57403f)
                curveTo(21.75f, 8.5066f, 21.0009f, 7.5804f, 19.948f, 7.405f)
                curveTo(19.5715f, 7.3422f, 19.1934f, 7.2839f, 18.814f, 7.2299f)
                curveTo(18.1387f, 7.134f, 17.5342f, 6.7535f, 17.1731f, 6.1749f)
                lineTo(16.3519f, 4.85889f)
                curveTo(15.9734f, 4.2524f, 15.3294f, 3.8584f, 14.6155f, 3.8201f)
                curveTo(13.7496f, 3.7736f, 12.8775f, 3.75f, 12f, 3.75f)
                curveTo(11.1225f, 3.75f, 10.2504f, 3.7736f, 9.3845f, 3.8201f)
                curveTo(8.6706f, 3.8584f, 8.0266f, 4.2524f, 7.6481f, 4.8589f)
                lineTo(6.82689f, 6.1749f)
                close()
            }
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
                moveTo(16.5f, 12.75f)
                curveTo(16.5f, 15.2353f, 14.4853f, 17.25f, 12f, 17.25f)
                curveTo(9.5147f, 17.25f, 7.5f, 15.2353f, 7.5f, 12.75f)
                curveTo(7.5f, 10.2647f, 9.5147f, 8.25f, 12f, 8.25f)
                curveTo(14.4853f, 8.25f, 16.5f, 10.2647f, 16.5f, 12.75f)
                close()
            }
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
                moveTo(18.75f, 10.5f)
                horizontalLineTo(18.7575f)
                verticalLineTo(10.5075f)
                horizontalLineTo(18.75f)
                verticalLineTo(10.5f)
                close()
            }
        }.build()
        return _Camera!!
    }

private var _Camera: ImageVector? = null
