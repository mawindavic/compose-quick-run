package vic.mawinda.composequickrun

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import vic.mawinda.composequickrun.ui.screens.ArtWorkApp
import vic.mawinda.composequickrun.ui.theme.ComposeQuickRunTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeQuickRunTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    ArtWorkApp()
                    
                    //CalculatorApp()
                    
                    // LemonadeApp()
                    //DiceRollerApp()
                    //                    Article(
                    //                        title = stringResource(id = R.string.article_title),
                    //                        paragraphOne = stringResource(
                    //                            id = R.string.article_paragraph_one
                    //                        ), body = stringResource(id = R.string.article_full_content)
                    //
                    //                    )
                    //                    GreetingImage(
                    //                        message = stringResource(id = R.string.happy_birthday_text),
                    //                        from = stringResource(R.string.signature_text),
                    //                    )
                }
            }
        }
    }
}

