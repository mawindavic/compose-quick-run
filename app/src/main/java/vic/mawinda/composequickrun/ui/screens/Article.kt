package vic.mawinda.composequickrun.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import vic.mawinda.composequickrun.R
import vic.mawinda.composequickrun.ui.theme.ComposeQuickRunTheme


@Composable
fun Article(title: String, paragraphOne: String, body: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        val image = painterResource(id = R.drawable.bg_compose_background)
        Image(painter = image, contentDescription = null, modifier = Modifier.fillMaxWidth())
        Text(text = title, fontSize = 24.sp, modifier = Modifier.padding(16.dp))
        Text(text = paragraphOne, textAlign = TextAlign.Justify, modifier = Modifier.padding(16.dp))
        Text(text = body, textAlign = TextAlign.Justify, modifier = Modifier.padding(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun ArticleBodyPreview() {
    ComposeQuickRunTheme {
        Article(
            title = stringResource(id = R.string.article_title), paragraphOne = stringResource(
                id = R.string.article_paragraph_one
            ), body = stringResource(id = R.string.article_full_content)

        )
    }
}