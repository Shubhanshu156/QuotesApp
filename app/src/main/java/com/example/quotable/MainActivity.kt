package com.example.quotable

import android.graphics.BlurMaskFilter
import android.graphics.Typeface.NORMAL
//import android.graphics.Paint
//import android.graphics.fonts.FontStyle
import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.layout.RowScopeInstance.align

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.text.style.TextDrawStyle.Unspecified.brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quotable.ui.theme.QuotableTheme
import com.example.quotable.viewmodels.Quotesviewmodel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuotableTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val myViewModel by viewModel<Quotesviewmodel>()

                    Column(
                        modifier = Modifier
                            .fillMaxSize()

                            .background(
                                brush = Brush.linearGradient(
                                    colors = listOf(Color(0xFFF5F7F6), Color(0xFF5CA0F2)),
                                    start = Offset(0f, 0f),
                                    end = Offset.Infinite
                                )
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center // add this parameter

                    ) {
                        if (myViewModel.quotelist.isNotEmpty() && myViewModel.position.value < myViewModel.quotelist.size) {
                            Column(
                                modifier = Modifier

                                    .width(300.dp)
                                    .wrapContentHeight()
                                    .padding(16.dp)
                                    .clip(RoundedCornerShape(15))

                                    .background(Color(0xFFFFFFFF))
                                    .align(Alignment.CenterHorizontally)


                            ) {
                        val state = myViewModel.quotelist
                        val position = myViewModel.position.value
                        Text(
                            text = state[position].content,
                            modifier = Modifier
                                .padding(top = 5.dp, end = 15.dp, start = 15.dp),
                            textAlign = TextAlign.Justify,
                            fontSize = 16.sp,
                            color = Color(0xFF5CA0F2),
                            lineHeight = 1.4.em,
                            fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                        )
                        Text(
                            text = state[position].author,
                            modifier = Modifier
                                .padding(end = 5.dp)
                                .align(Alignment.CenterHorizontally),
                            fontSize = 20.sp,
                            color = Color(0xFFFF7F50),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                else {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }


                Button(
                            modifier = Modifier
                                .padding(top = 20.dp)
                                .clip(RoundedCornerShape(10)),
                            colors = ButtonDefaults.buttonColors( Color(0xFFFF7F50)),
                            onClick = { if (!myViewModel.isLoading.value) myViewModel.getquotelist() }
                        ) {
                            Text(text = "Next",  color = Color(0xFFFFFFFF),)
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    QuotableTheme {
        Greeting("Android")
    }
}

