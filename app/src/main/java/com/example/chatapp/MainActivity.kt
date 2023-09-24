package com.example.chatapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chatapp.screen.StatusScreen
import com.example.chatapp.screen.chatlistscreen
import com.example.chatapp.screen.loginscreen
import com.example.chatapp.screen.profilescreen
import com.example.chatapp.screen.signupScreen
import com.example.chatapp.screen.singlechatscreen
import com.example.chatapp.screen.statuslistscreen
import com.example.chatapp.ui.theme.ChatappTheme
import dagger.hilt.android.AndroidEntryPoint

sealed class DestinationScreen(val route: String) {
    object Signup : DestinationScreen("signup")
    object Login : DestinationScreen("login")
    object Profile : DestinationScreen("profile")
    object ChatList : DestinationScreen("chatList")
    object SingleChat : DestinationScreen("singleChat/{chatId}") {
        fun createRoute(id: String) = "singleChat/$id"
    }

    object StatusList : DestinationScreen("statusList")
    object Status : DestinationScreen("status/{userId}") {
        fun createRoute(userId: String?) = "status/$userId"
    }
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChatappTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ChatAppNavigation()
                }
            }
        }
    }
}


@Composable
fun ChatAppNavigation() {
    val navController = rememberNavController()
    val vm = hiltViewModel<CAViewModel>()

    NotificationMessage(vm = vm)


    NavHost(navController = navController, startDestination = DestinationScreen.Signup.route) {
        composable(DestinationScreen.Signup.route) {
           signupScreen(navController, vm)

        }
        composable(DestinationScreen.Login.route) {
            loginscreen(navController, vm)
        }
        composable(DestinationScreen.Profile.route) {
            profilescreen(navController = navController, vm = vm)
        }
        composable(DestinationScreen.StatusList.route) {
            statuslistscreen(navController = navController, vm)
        }

        composable(DestinationScreen.ChatList.route) {
            chatlistscreen(navController = navController, vm = vm)
        }
        composable(DestinationScreen.SingleChat.route) {
            val chatId = it.arguments?.getString("chatId")
            chatId?.let {
                singlechatscreen(navController = navController, vm = vm, chatId = it)
            }
        }
        composable(DestinationScreen.Status.route) {
            val userId = it.arguments?.getString("userId")
            userId?.let {
              StatusScreen(navController = navController, vm = vm, userId = it)
            }
        }
    }
}