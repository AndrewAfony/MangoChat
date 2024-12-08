package andrewafony.testapp.profile.screen

import andrewafony.testapp.designsystem.toast
import andrewafony.testapp.domain.model.Birthday
import andrewafony.testapp.domain.model.User
import andrewafony.testapp.profile.ProfileViewModel
import andrewafony.testapp.profile.screen.components.ProfileAboutItem
import andrewafony.testapp.profile.screen.components.ProfileItem
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    profileViewModel: ProfileViewModel = koinViewModel(),
    navigateToNameEdit: () -> Unit,
    navigateToCityEdit: () -> Unit,
    navigateBack: () -> Unit,
) {

    val userState by profileViewModel.userState.collectAsStateWithLifecycle()

    ProfileScreenContent(
        modifier = modifier,
        user = userState,
        updateBirthday = profileViewModel::updateBirthday,
        updateImage = profileViewModel::updateImage,
        navigateToNameEdit = navigateToNameEdit,
        navigateToCityEdit = navigateToCityEdit,
        navigateBack = navigateBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreenContent(
    modifier: Modifier = Modifier,
    user: User,
    updateImage: (Uri?) -> Unit,
    updateBirthday: (Birthday) -> Unit,
    navigateToNameEdit: () -> Unit,
    navigateToCityEdit: () -> Unit,
    navigateBack: () -> Unit,
) {

    val context = LocalContext.current
    val clipboardManager = LocalClipboardManager.current

    var isBirthdayBottomSheet by remember { mutableStateOf(false) }
    val birthdayBottomSheetState = rememberModalBottomSheetState()

    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()

    val iconEdit = rememberVectorPainter(andrewafony.testapp.designsystem.icons.IconPhoto)
    val primaryColor = MaterialTheme.colorScheme.primary
    var editImageButtonOffset = remember { Offset(0f, 0f) }

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            updateImage(uri)
        }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .imePadding()
            .background(MaterialTheme.colorScheme.surface),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ProfileScreenTopBar(navigateBack = navigateBack)

        AsyncImage(
            model = user.image,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(vertical = 16.dp)
                .size(128.dp)
                .graphicsLayer {
                    compositingStrategy = CompositingStrategy.Offscreen
                }
                .pointerInput(Unit) {
                    detectTapGestures { tapOffset ->
                        if (tapOffset.x > editImageButtonOffset.x && tapOffset.y > editImageButtonOffset.y) {
                            launcher.launch("image/*")
                        }
                    }
                }
                .drawWithCache {
                    val dotSize = size.width / 8f

                    editImageButtonOffset =
                        Offset(size.width - dotSize * 2, size.height - dotSize * 2)

                    val path = Path()
                    path.addOval(
                        Rect(
                            topLeft = Offset.Zero,
                            bottomRight = Offset(size.width, size.height)
                        )
                    )
                    onDrawWithContent {
                        clipPath(path) {
                            this@onDrawWithContent.drawContent()
                        }
                        drawCircle(
                            Color.Black,
                            radius = dotSize,
                            center = Offset(
                                x = size.width - dotSize,
                                y = size.height - dotSize
                            ),
                            blendMode = BlendMode.Clear
                        )
                        drawCircle(
                            color = primaryColor,
                            radius = dotSize * 0.8f,
                            center = Offset(
                                x = size.width - dotSize,
                                y = size.height - dotSize
                            )
                        )
                        translate(
                            left = size.width - dotSize - iconEdit.intrinsicSize.width / 2 + 4.dp.toPx(),
                            top = size.height - dotSize - iconEdit.intrinsicSize.height / 2 + 4.dp.toPx()
                        ) {
                            with(iconEdit) {
                                draw(
                                    size = Size(16.dp.toPx(), 16.dp.toPx())
                                )
                            }
                        }
                    }
                }
        )

        ProfileUnchangeableItemsGroup(
            username = user.username,
            phone = user.phone,
            onClick = {
                clipboardManager.setText(AnnotatedString(it))
                context.toast("Copied")
            }
        )

        ProfileScreenItems(
            name = "${user.name} ${user.surname}",
            birthday = user.birthday,
            city = user.city,
            onNameChange = navigateToNameEdit,
            onCityChange = navigateToCityEdit,
            onBirthdayChange = { isBirthdayBottomSheet = true }
        )

        ProfileAboutItem(
            modifier = Modifier
                .padding(horizontal = 16.dp)
        )

        if (isBirthdayBottomSheet) {
            BirthdayEditBottomSheet(
                birthday = user.birthday,
                sheetState = birthdayBottomSheetState,
                updateBirthday = updateBirthday,
                onDismiss = {
                    scope.launch { birthdayBottomSheetState.hide() }.invokeOnCompletion {
                        if(!birthdayBottomSheetState.isVisible) {
                            isBirthdayBottomSheet = false
                        }
                    }
                }
            )
        }

    }
}

@Composable
fun ProfileScreenTopBar(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowLeft,
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .clickable { navigateBack() }
                .padding(12.dp)
        )
    }
}

@Composable
fun ProfileScreenItems(
    modifier: Modifier = Modifier,
    name: String,
    city: String,
    birthday: Birthday,
    onNameChange: () -> Unit,
    onCityChange: () -> Unit,
    onBirthdayChange: () -> Unit,
) {

    val birthdayDay = rememberSaveable(birthday) {
        if (birthday.day.toInt() < 10) "0${birthday.day}"
        else birthday.day
    }

    Column(
        modifier = modifier
            .imePadding()
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(andrewafony.testapp.designsystem.theme.veryLightGray)
    ) {
        ProfileItem(
            title = "Имя, фамилия",
            content = name,
            isChangeable = true,
            onClick = onNameChange
        )
        HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
        ProfileItem(
            title = "Город",
            content = city,
            isChangeable = true,
            onClick = onCityChange
        )
        HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
        ProfileItem(
            title = "Дата рождения",
            content = "$birthdayDay.${birthday.month.number}.${birthday.year}",
            isChangeable = true,
            onClick = onBirthdayChange
        )
        HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
        ProfileItem(
            title = "Знак зодиака",
            content = "Близнецы",
            isChangeable = false
        )
    }
}

@Composable
fun ProfileUnchangeableItemsGroup(
    modifier: Modifier = Modifier,
    username: String,
    phone: String,
    onClick: (String) -> Unit,
) {

    Column(
        modifier = modifier
            .imePadding()
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(andrewafony.testapp.designsystem.theme.veryLightGray)
    ) {
        ProfileItem(
            title = "Никнейм",
            content = username,
            isChangeable = false,
            onClick = { onClick(username) }
        )
        HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
        ProfileItem(
            title = "Номер телефона",
            content = phone,
            isChangeable = false,
            onClick = { onClick(phone) }
        )
    }
}

@Preview
@Composable
private fun ProfileScreenPrev() {
    andrewafony.testapp.designsystem.theme.MangoTestChatTheme {
        ProfileScreen(
            navigateToCityEdit = {},
            navigateToNameEdit = {},
            navigateBack = {},
        )
    }
}