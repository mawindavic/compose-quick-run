package vic.mawinda.composequickrun.ui.screens

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import vic.mawinda.composequickrun.R

enum class Progress {
    START,
    SQUEEZE,
    DRINK,
    EMPTY;

    val page: LemonPage
        get() = when (this) {
            Progress.START -> {
                val mImage = LemonImage(icon = R.drawable.lemon_tree, desc = R.string.tree_desc)
                LemonPage(image = mImage, instruction = R.string.select_lemon)
            }

            Progress.SQUEEZE -> {
                val mImage = LemonImage(icon = R.drawable.lemon_squeeze, desc = R.string.lemon_desc)
                LemonPage(image = mImage, instruction = R.string.tap_to_squeeze)

            }

            Progress.DRINK -> {
                val mImage = LemonImage(
                    icon = R.drawable.lemon_drink, desc = R.string.full_glass_desc
                )
                LemonPage(image = mImage, instruction = R.string.tap_to_drink)

            }

            Progress.EMPTY -> {
                val mImage = LemonImage(
                    icon = R.drawable.lemon_restart, desc = R.string.empty_glass_desc
                )
                LemonPage(image = mImage, instruction = R.string.tap_to_restart)

            }
        }
}

data class LemonImage(@DrawableRes val icon: Int, @StringRes val desc: Int)
data class LemonPage(val image: LemonImage, @StringRes val instruction: Int)

@Composable
fun LemonadeImageWithActionDesc(modifier: Modifier = Modifier) {
    var progress by remember {
        mutableStateOf(Progress.START)
    }
    var squeezeCount by remember {
        mutableStateOf(0)
    }

    var squeeze by remember {
        mutableStateOf((2..4).random())
    }

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Button(
            onClick = {
                progress = when (progress) {
                    Progress.START -> Progress.SQUEEZE
                    Progress.SQUEEZE -> {
                        squeezeCount++
                        if (squeezeCount == squeeze) {
                            squeezeCount = 0
                            squeeze = (2..4).random()
                            Progress.DRINK
                        } else {
                            Progress.SQUEEZE
                        }
                    }

                    Progress.DRINK -> Progress.EMPTY
                    Progress.EMPTY -> Progress.START
                }
            }, shape = RoundedCornerShape(4.dp), colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.tertiaryContainer
            )
        ) {
            Image(
                painter = painterResource(id = progress.page.image.icon),
                contentDescription = stringResource(
                    id = progress.page.image.desc
                ),
            )
        }

        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = stringResource(id = progress.page.instruction),
            style = MaterialTheme.typography.bodyLarge
        )

    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun LemonadeApp() {

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { Text(text = "Lemonade", fontWeight = FontWeight.Bold) },
            colors = TopAppBarDefaults.largeTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        )
    }) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.tertiaryContainer),
            color = MaterialTheme.colorScheme.background
        ) {
            LemonadeImageWithActionDesc(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        }

    }

}