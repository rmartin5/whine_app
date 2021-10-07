package com.example.whine_app

import android.os.Bundle
import android.util.Log
//import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var buttonWhine = findViewById<Button> (R.id.button_whine)
        var countView = findViewById<TextView>(R.id.tv_count_output)

        // TODO pull from DB
        countView.setText("0")

        buttonWhine?.setOnClickListener()
        {
            // TODO update to a helpful static message
            Toast.makeText(this@MainActivity, R.string.message, Toast.LENGTH_LONG).show()
            val temp = Integer.valueOf(countView.text.toString()) + 1
            Log.i("Temp is ", temp.toString())
            countView?.text= temp.toString()

            //TODO check frequency and reactive if needed
        }
    }
}

