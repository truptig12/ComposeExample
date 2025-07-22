package com.herts.composeexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val painter = painterResource(id = R.drawable.snowman)
            val description = "Snowman playing in the snow"
            val title = "Snowman playing in the snow"
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(16.dp)
            ) {
                ImageCard(painter = painter, contentDescription = description, title = title)
            }
        }
    }
}

@Composable
private fun <T> RadioButtonContent(
    rtl: Boolean,
    isSelectedOption: Boolean,
    modifier: Modifier,
    onOptionSelect: (T) -> Unit,
    option: T,
    isEnabled: Boolean,
    painter: Painter,
    label: (T) -> String,
    description: (T) -> String,
    descriptionTextStyle: TextStyle
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (!rtl) {
            IconToggleButtonComposable(
                modifier = Modifier,
                isSelectedOption = isSelectedOption,
                onSelected = { onOptionSelect(option) },
                isEnabled = isEnabled,
                painter = painter
            )
        }

        // Text column centered to radio button
        Column(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically),
            verticalArrangement = Arrangement.Center
        ) {
            RadioButtonText(
                label(option),
                isEnabled
            )

            if (description(option).isNotBlank()) {
                RadioButtonText(
                    description(option),
                    isEnabled,
                    descriptionTextStyle
                )
            }
        }

        if (rtl) {
            IconToggleButtonComposable(
                modifier = Modifier,
                isSelectedOption = isSelectedOption,
                onSelected = { onOptionSelect(option) },
                isEnabled = isEnabled,
                painter = painter
            )
        }
    }
}

@Composable
fun RadioButtonText(
    label: String,
    isEnabled: Boolean,
    style: TextStyle = AppTheme.designTokenTypography.typographyStyle5.copy(
        color = if (isEnabled)
            AppTheme.designTokenColors.textInputDefault
        else
            AppTheme.designTokenColors.textInputDisabled
    )
) {
    Text(
        text = label,
        textAlign = TextAlign.Start,
        style = style,
        modifier = Modifier
            .semantics { contentDescription = label }
            .clearAndSetSemantics {}
    )
}


@Composable
fun ImageCard(
    painter: Painter,
    contentDescription: String,
    title: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)

    ) {

        Box(modifier = Modifier.height(200.dp)) {
            Image(
                painter = painter,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop
            )
            
            Box(modifier = Modifier.fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black
                        ),
                        startY = 300f
                    )
                ))

            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(text = title, style = TextStyle(color = Color.White, fontSize = 16.sp))
            }
        }
    }

}

