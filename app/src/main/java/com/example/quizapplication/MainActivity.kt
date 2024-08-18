package com.example.quizapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //ig they are the only interactive elements
        val btnStart : Button = findViewById(R.id.btn_start)
        val etName : EditText = findViewById(R.id.et_name)
        btnStart.setOnClickListener{

            if (etName.text.isEmpty()){
                Toast.makeText(this,
                    "Please Enter your name", Toast.LENGTH_LONG).show()
            }
            else{
                //creates an 'intent' to start 'quiquestionact'
                //this - current context
                val intent = Intent(this, QuizQuestionsActivity::class.java)
                intent.putExtra(Constants.USER_NAME, etName.text.toString())        //to pass additional data
                //starts it - quizqsn will be opened and displayed
                startActivity(intent)
                //closes the current main activuty bt preventing the user from returning to it by pressing the back button
                finish()
            }
        }
    }
}