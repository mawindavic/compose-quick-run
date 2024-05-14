package vic.mawinda.composequickrun.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import vic.mawinda.composequickrun.R
import vic.mawinda.composequickrun.ui.theme.ComposeQuickRunTheme

@Composable
fun TaskManager(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        val image = painterResource(id = R.drawable.ic_task_completed)

        Image(
            painter = image, contentDescription = null,
        )


        Text(
            text = stringResource(id = R.string.task_completed), fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp)
        )

        Text(
            text = stringResource(id = R.string.nice_work), fontSize = 16.sp,
        )
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TaskManagerPreview() {
    ComposeQuickRunTheme {
        TaskManager()
    }
}