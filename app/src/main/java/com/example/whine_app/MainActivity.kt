package com.example.whine_app

import android.os.Bundle
import android.util.Log
//import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

val consequences = listOf<String>("5 pushups", "No computer for a day", "No tv for a day", "10 jumping jacks", "No dessert for a day", "10 pushups", "No internet for a day")

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var buttonWhine = findViewById<Button> (R.id.button_whine)
        var countView = findViewById<TextView>(R.id.tv_count_output)
        var consequenceView = findViewById<TextView>(R.id.tv_consequence)

        // TODO pull from DB
        countView.setText("0")

        buttonWhine?.setOnClickListener()
        {
            // TODO update to a helpful static message
            Toast.makeText(this@MainActivity, R.string.message, Toast.LENGTH_LONG).show()
            val temp = Integer.valueOf(countView.text.toString()) + 1
            Log.i("Temp is ", temp.toString())
            countView?.text= temp.toString()

            if((temp % 5) == 0) {
                consequenceView?.text = consequences.random().toString()
            } else {
                consequenceView?.text = ""
            }
        }
    }
}

