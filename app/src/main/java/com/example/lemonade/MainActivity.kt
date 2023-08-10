package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.AppTheme
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TitleBar()
                    LemonadeCardsView()
                }
            }
        }
    }
}

@Composable
private fun TitleBar() {
    Column {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .fillMaxWidth()
        ) {
            Text(
                text = "Lemonade",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(top = 64.dp, bottom = 16.dp)
            )
        }
        Spacer(modifier = Modifier)
    }
}

@Composable
private fun LemonadeCardsView() {
    var card by remember { mutableStateOf(1) }
    val imageResource: Int
    val imageDesc: String
    val cardText: String

    val lemonSqueezeTarget = (2..4).random()
    var lemonSqueezeCount = 0

    when(card) {
        1 -> {
            imageResource = R.drawable.lemon_tree
            imageDesc = "Lemon tree"
            cardText = stringResource(R.string.card_1)
        }
        2 -> {
            imageResource = R.drawable.lemon_squeeze
            imageDesc = "Squeeze lemon"
            cardText = stringResource(R.string.card_2)
        }
        3 -> {
            imageResource = R.drawable.lemon_drink
            imageDesc = "Drink lemonade"
            cardText = stringResource(R.string.card_3)
        }
        else -> {
            imageResource = R.drawable.lemon_restart
            imageDesc = "Empty glass"
            cardText = stringResource(R.string.card_4)
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Box(
            modifier = Modifier
                .size(size = 200.dp)
                .clip(shape = RoundedCornerShape(percent = 15))
                .background(color = MaterialTheme.colorScheme.tertiaryContainer)
                .wrapContentSize(Alignment.Center)
                .padding(16.dp)
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = null
                ) {
                    if (card == 2) {
                        lemonSqueezeCount++
                        if (lemonSqueezeCount == lemonSqueezeTarget) {
                            card = (card + 1) % 4
                        }
                    } else {
                        card = (card + 1) % 4
                    }
                }
        ) {
            Image(
                painter = painterResource(imageResource),
                contentDescription = imageDesc,
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = cardText
        )
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun LemonadePreview() {
    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ){
            TitleBar()
            LemonadeCardsView()
        }
    }
}