package vic.mawinda.composequickrun.ui.screens

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import vic.mawinda.composequickrun.R
import vic.mawinda.composequickrun.ui.theme.ComposeQuickRunTheme
import java.text.NumberFormat


@Composable
fun TipTimeLayout(modifier: Modifier = Modifier) {
    var amountInput by remember {
        mutableStateOf("")
    }
    var tipInput by remember {
        mutableStateOf("")
    }
    var roundUP by remember {
        mutableStateOf(false)
    }
    val amount = amountInput.toDoubleOrNull() ?: 0.0
    val tipPercent = tipInput.toDoubleOrNull() ?: 0.0
    val tip = calculateTip(amount, tipPercent, roundUP)
    
    Column(
        modifier = modifier
            .statusBarsPadding()
            .padding(horizontal = 40.dp)
            .verticalScroll(rememberScrollState())
            .safeDrawingPadding(), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        
        Text(
            text = stringResource(id = R.string.calculate_tip),
            modifier = Modifier
                .padding(bottom = 16.dp, top = 40.dp)
                .align(alignment = Alignment.Start)
        )
        
        
        EditNumberField(
            label = R.string.bill_amount, leadingIcon = R.drawable.money, value = amountInput,
            onValueChange = { amountInput = it }, keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number, imeAction = ImeAction.Next
            ), modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth()
        )
        
        EditNumberField(
            label = R.string.how_was_the_service,
            leadingIcon = R.drawable.baseline_emoji_symbols_24, value = tipInput,
            onValueChange = { tipInput = it }, keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number, imeAction = ImeAction.Done
            ), modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth()
        )
        
        RoundTheTipRow(roundUP = roundUP, onRoundUpChanged = {
            roundUP = it
        }, modifier = Modifier.padding(bottom = 32.dp))
        
        
        Text(
            text = stringResource(id = R.string.tip_amount, tip),
            style = MaterialTheme.typography.displaySmall
        )
        
        Spacer(modifier = Modifier.height(150.dp))
    }
}


@VisibleForTesting
internal fun calculateTip(
        amount: Double,
        tipPercent: Double = 15.0,
        roundUP: Boolean,
): String {
    var tip = tipPercent / 100 * amount
    if(roundUP) {
        tip = kotlin.math.ceil(tip)
    }
    
    return NumberFormat.getCurrencyInstance()
        .format(tip)
}


@Composable
fun EditNumberField(
        @StringRes label: Int,
        @DrawableRes leadingIcon: Int,
        keyboardOptions: KeyboardOptions,
        value: String,
        onValueChange: (String) -> Unit,
        modifier: Modifier = Modifier,
) {
    TextField(value = value, onValueChange = onValueChange, leadingIcon = {
        Icon(
            painter = painterResource(id = leadingIcon), contentDescription = null
        )
    }, label = { Text(text = stringResource(label)) }, singleLine = true,
        keyboardOptions = keyboardOptions, modifier = modifier
    )
}

@Composable
fun RoundTheTipRow(
        roundUP: Boolean,
        onRoundUpChanged: (Boolean) -> Unit,
        modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .size(48.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = stringResource(id = R.string.round_up_tip))
        Switch(
            checked = roundUP, onCheckedChange = onRoundUpChanged,
            modifier = modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End)
        )
    }
    
}

@Preview(showSystemUi = true)
@Composable
fun CalculatorApp() {
    Surface {
        TipTimeLayout()
    }
}

@Preview(showSystemUi = true)
@Composable
fun CalculatorAppPrev() {
    ComposeQuickRunTheme {
        CalculatorApp()
    }
}