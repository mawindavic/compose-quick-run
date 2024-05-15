package vic.mawinda.composequickrun.ui.screens

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import vic.mawinda.composequickrun.R
import vic.mawinda.composequickrun.ui.theme.ComposeQuickRunTheme


data class ArtWork(
        @DrawableRes val image: Int,
        @StringRes val imageDesc: Int,
        val title: String,
        val artist: String,
)

val arts = listOf(
    ArtWork(
        image = R.drawable.lemon_tree, imageDesc = R.string.tree_desc, title = "Lemon Tree",
        artist = "Jane Doe"
    ), ArtWork(
        image = R.drawable.lemon_squeeze, imageDesc = R.string.tap_to_squeeze,
        title = "Lemon Fruit", artist = "John Doe"
    ), ArtWork(
        image = R.drawable.lemon_drink, imageDesc = R.string.tap_to_drink, title = "Lemon Juice",
        artist = "Mike Doe"
    ), ArtWork(
        image = R.drawable.lemon_restart, imageDesc = R.string.tap_to_restart,
        title = "Empty Glass", artist = "Ian Doe"
    )
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtWorkApp(modifier: Modifier = Modifier) {
    var currentSelection by remember {
        mutableIntStateOf(0)
    }
    
    val artWork: ArtWork = arts[currentSelection]
    
    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = { Text(text = "Art Work", fontWeight = FontWeight.Medium) })
    }) { padding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp), verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            
            Image(
                painter = painterResource(id = artWork.image),
                contentDescription = stringResource(id = artWork.imageDesc),
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)
                    .padding(8.dp)
                    .shadow(elevation = 2.dp)
            )
            
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .background(color = Color.LightGray)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
                
                ) {
                Text(
                    text = artWork.title, fontSize = 24.sp, textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold, modifier = Modifier.padding(8.dp)
                )
                Text(
                    text = artWork.artist, textAlign = TextAlign.Start,
                    modifier = Modifier.padding(8.dp)
                )
            }
            
            Spacer(modifier = Modifier.height(IntrinsicSize.Max))
            
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = {
                    if(arts[currentSelection] == arts.first()) {
                        currentSelection = arts.indexOf(arts.last())
                    } else {
                        currentSelection--
                    }
                }) {
                    Text(text = "Previous")
                }
                
                Button(onClick = {
                    if(arts[currentSelection] == arts.last()) {
                        currentSelection = 0
                    } else {
                        currentSelection++
                    }
                }) {
                    Text(text = "Next")
                }
            }
            
        }
    }
    
}


@Preview
@Composable
fun ArtWorkPreview() {
    ComposeQuickRunTheme {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            ArtWorkApp()
        }
    }
}