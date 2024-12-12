package andrewafony.testapp.profile.screen

import andrewafony.testapp.designsystem.theme.MangoTestChatTheme
import andrewafony.testapp.common.utils.toast
import andrewafony.testapp.domain.model.User
import andrewafony.testapp.profile.ProfileState
import andrewafony.testapp.profile.ProfileViewModel
import andrewafony.testapp.profile.screen.components.ProfileAboutItem
import andrewafony.testapp.profile.screen.components.ProfileItem
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = koinViewModel(),
    navigateToNameEdit: () -> Unit,
    navigateToCityEdit: () -> Unit,
    navigateBack: () -> Unit,
) {

    val profileState by viewModel.userState.collectAsStateWithLifecycle()

    ProfileScreenContent(
        modifier = modifier,
        profileState = profileState,
        updateBirthday = viewModel::updateBirthday,
        updateImage = viewModel::updateImage,
        updateAbout = viewModel::updateAbout,
        retry = viewModel.userState::restart,
        navigateToNameEdit = navigateToNameEdit,
        navigateToCityEdit = navigateToCityEdit,
        navigateBack = navigateBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreenContent(
    modifier: Modifier = Modifier,
    profileState: ProfileState,
    updateImage: (Uri?) -> Unit,
    updateBirthday: (LocalDate) -> Unit,
    updateAbout: (String) -> Unit,
    retry: () -> Unit,
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
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri: Uri? ->
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

        when (profileState) {
            is ProfileState.Error -> {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Something went wrong",
                        color = Color.Red,
                        fontStyle = FontStyle.Italic
                    )
                    Spacer(modifier = Modifier.size(12.dp))
                    Button(onClick = retry) {
                        Text("Повторить")
                    }
                }
            }

            is ProfileState.Loading -> {
                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            is ProfileState.Success -> {

                AsyncImage(
                    model = profileState.user.image,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(andrewafony.testapp.designsystem.R.drawable.person_placeholder),
                    error = painterResource(andrewafony.testapp.designsystem.R.drawable.person_placeholder),
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
                    username = profileState.user.username,
                    phone = profileState.user.phone,
                    onClick = {
                        clipboardManager.setText(AnnotatedString(it))
                        context.toast("Copied")
                    }
                )

                ProfileScreenItems(
                    name = profileState.user.name,
                    birthday = profileState.user.birthday,
                    city = profileState.user.city,
                    zodiac = profileState.user.zodiac,
                    onNameChange = navigateToNameEdit,
                    onCityChange = navigateToCityEdit,
                    onBirthdayChange = { isBirthdayBottomSheet = true }
                )

                ProfileAboutItem(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    prevAbout = profileState.user.about,
                    updateAbout = updateAbout
                )

                if (isBirthdayBottomSheet) {
                    BirthdayEditBottomSheet(
                        birthday = profileState.user.birthday,
                        sheetState = birthdayBottomSheetState,
                        updateBirthday = updateBirthday,
                        onDismiss = {
                            scope.launch { birthdayBottomSheetState.hide() }.invokeOnCompletion {
                                if (!birthdayBottomSheetState.isVisible) {
                                    isBirthdayBottomSheet = false
                                }
                            }
                        }
                    )
                }
            }
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
    birthday: LocalDate?,
    zodiac: String,
    onNameChange: () -> Unit,
    onCityChange: () -> Unit,
    onBirthdayChange: () -> Unit,
) {

    val birthdayDay: String? = remember(birthday) {
        birthday?.let {
            if (it.dayOfMonth < 10)
                "0${it.dayOfMonth}"
            else
                it.dayOfMonth.toString()
        }
    }

    val birthdayMonth: String? = remember(birthday) {
        birthday?.let {
            if (it.monthValue < 10)
                "0${it.monthValue}"
            else
                it.monthValue.toString()
        }
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
            title = "Имя",
            content = name,
            isChangeable = true,
            onClick = onNameChange
        )
        HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
        ProfileItem(
            title = "Город",
            content = city.ifBlank { "Не указан" },
            isChangeable = true,
            onClick = onCityChange
        )
        HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
        ProfileItem(
            title = "Дата рождения",
            content = if (birthday != null) "$birthdayDay.$birthdayMonth.${birthday.year}" else "Не указано",
            isChangeable = true,
            onClick = onBirthdayChange
        )
        HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
        ProfileItem(
            title = "Знак зодиака",
            content = zodiac.ifBlank { "День рождения не указан" },
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
            content = "@$username",
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
    MangoTestChatTheme {
        ProfileScreenContent(
            navigateToCityEdit = {},
            navigateToNameEdit = {},
            navigateBack = {},
            retry = {},
            updateAbout = {},
            profileState = ProfileState.Success(User.empty()),
            updateImage = {},
            updateBirthday = {}
        )
    }
}