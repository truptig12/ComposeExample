package com.herts.composeexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import kotlinx.coroutines.delay

class EffectHandlersEx : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            var text = remember{
                mutableStateOf("")
            }

          LaunchedEffect(key1 =  text){
              delay(1000L)
              print("Text is $text")
          }
        }
    }
}
