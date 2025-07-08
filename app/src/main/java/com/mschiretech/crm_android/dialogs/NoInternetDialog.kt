package com.mschiretech.crm_android.dialogs

import android.R.attr.content
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.mschiretech.crm_android.core.internet.NetworkState
import com.mschiretech.crm_android.core.internet.observeNetworkState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoInternetDialog(showDialog: Boolean,onDismissRequest :()-> Unit) {

    val context = LocalContext.current
    val networkState by context.observeNetworkState().collectAsState(initial = NetworkState.Unavailable)

    BasicAlertDialog(onDismissRequest, Modifier, DialogProperties(),
        {
            Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Status Indicator
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .background(
                            color = when (networkState) {
                                NetworkState.Available -> Color.Green
                                NetworkState.Losing -> Color.Red
                                NetworkState.Lost, NetworkState.Unavailable -> Color.Red
                            },
                            shape = RoundedCornerShape(40.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = when (networkState) {
                            NetworkState.Available -> "✓"
                            NetworkState.Losing -> "⚠"
                            NetworkState.Lost, NetworkState.Unavailable -> "✗"
                        },
                        fontSize = 32.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Status Text
                Text(
                    text = when (networkState) {
                        NetworkState.Available -> "Connected"
                        NetworkState.Losing -> "Connection Losing"
                        NetworkState.Lost -> "Connection Lost"
                        NetworkState.Unavailable -> "No Internet"
                    },
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = when (networkState) {
                        NetworkState.Available -> Color.Green
                        NetworkState.Losing -> Color.Red
                        NetworkState.Lost, NetworkState.Unavailable -> Color.Red
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Status Description
                Text(
                    text = when (networkState) {
                        NetworkState.Available -> "You are connected to the internet"
                        NetworkState.Losing -> "Connection is becoming unstable"
                        NetworkState.Lost -> "Internet connection has been lost"
                        NetworkState.Unavailable -> "No internet connection available"
                    },
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
        }
    )
}