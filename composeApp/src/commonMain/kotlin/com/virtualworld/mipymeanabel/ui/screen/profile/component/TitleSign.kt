package com.virtualworld.mipymeanabel.ui.screen.profile.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mipymeanabel.composeapp.generated.resources.Res
import mipymeanabel.composeapp.generated.resources.YoungSerif_Regular
import org.jetbrains.compose.resources.Font

@Composable
fun TitleSign(visibleSignCard: Boolean,title:String) {
    AnimatedVisibility(
        visible = visibleSignCard,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
            Column(modifier = Modifier.padding(24.dp).padding(top = 32.dp)) {
                Text(
                    text = "Hola",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 32.sp,
                    fontFamily = FontFamily(Font(Res.font.YoungSerif_Regular) )
                )
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White
                )
            }
        }

}