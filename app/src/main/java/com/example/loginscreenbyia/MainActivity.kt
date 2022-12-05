package com.example.loginscreenbyia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View.inflate
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.*

//Compose
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView


class MainActivity : AppCompatActivity() {


    //Compose
    var email: String by mutableStateOf("")
    var password: String by mutableStateOf("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Compose
        val composeView = findViewById<ComposeView>(R.id.composeView)
        composeView.setContent {
            Column() {
                Hello()
                Interruptor()
                LoginScreen()
                MyApp()
            }

        }


        // Create an EditText for the username
        val usernameField = EditText(this)
        usernameField.setHint("Username")

        // Create an EditText for the password
        val passwordField = EditText(this)
        passwordField.setHint("Password")

        // Create a Button for logging in
        val loginButton = Button(this)
        loginButton.setText("Login")

        // Add the EditTexts and Button to the layout
        val layout = findViewById<LinearLayout>(R.id.activity_main)
        layout.addView(usernameField)
        layout.addView(passwordField)
        layout.addView(loginButton)

        loginButton.setOnClickListener {
            // Get the username and password from the EditTexts
            val username = usernameField.text.toString()
            val password = passwordField.text.toString()

            // Verify the username and password
            verifyCredentials(username, password)
        }
    }

    private fun verifyCredentials(username: String, password: String) {
        if ((username == "admin") && (password == "1234")) {
            Toast.makeText(this, "Usuario Correcto", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "No es Correcto", Toast.LENGTH_SHORT).show()
        }
    }

    @Composable
    fun MyApp() {
        Column {
            Text("Hello, world!")
            Text("This is my app.")
            Text("It uses Jetpack Compose and XML.")
            AndroidView({ context ->
                LayoutInflater.from(context).inflate(R.layout.mi_vista, null)
            })
        }
    }

    //Compose
    @Composable
    fun Hello() {
        Column() {
            Text(
                "Hello Jetpack Compose",
                modifier = Modifier.wrapContentWidth(Alignment.CenterHorizontally)
            )
        }
    }

    @Composable
    fun Interruptor() {
        var estadoInterruptor by rememberSaveable { mutableStateOf(true) }
        Icon(
            imageVector = if (estadoInterruptor) Icons.Rounded.Person else Icons.Rounded.Info,
            contentDescription = "Interruptor",
            modifier = Modifier.clickable { estadoInterruptor = !estadoInterruptor },
            tint = if (estadoInterruptor) Color.Green else Color.Red
        )
    }

    @Composable
    fun LoginScreen() {

        Column {
            // Logo
            Text(text = "My App")
            // Email field
            TextField(
                value = email,
                onValueChange = { email = it }
            )
            // Password field
            TextField(
                value = password,
                onValueChange = { password = it }
            )
            // Submit button
            Button(onClick = {
                // Submit login information
            }) {
                Text(text = "Login")
            }
        }
    }

    @Preview(showSystemUi = true, showBackground = true)
    @Composable
    fun Preview() {
        Column {
            Hello()
            Interruptor()
            LoginScreen()
            MyApp()
        }
    }
}


