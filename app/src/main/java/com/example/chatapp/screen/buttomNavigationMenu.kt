package com.example.chatapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.chatapp.DestinationScreen
import com.example.chatapp.R
import com.example.chatapp.navigateTo

enum class buttomNavigationItem(val icon: Int, val navDestination: DestinationScreen) {
    PROFILE(R.drawable.baseline_profile, DestinationScreen.Profile),
    CHATLIST(R.drawable.baseline_chat, DestinationScreen.ChatList),
    STATUSLIST(R.drawable.baseline_status, DestinationScreen.StatusList)
}

@Composable
fun bottomNavigationMenu(selectedItem: buttomNavigationItem, navController: NavController) {
    Row(
        modifier = Modifier
//            .fillMaxSize()
            .wrapContentHeight()
            .padding(top = 4.dp)
//            .background(Color.White)
    ) {
        for (item in buttomNavigationItem.values()) {
            Image(
                painter = painterResource(id = item.icon),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .padding(4.dp)
                    .weight(1f)
                    .clickable {
                        navigateTo(
                            navController,
                            item.navDestination.route
                        )
                    },
                colorFilter = if (item == selectedItem) ColorFilter.tint(Color.Black)
                else ColorFilter.tint(Color.Gray)
            )
        }
    }
}