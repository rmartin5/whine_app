package com.example.whine_app

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

val consequences = listOf<String>("5 pushups", "No computer for a day", "No tv for a day", "10 jumping jacks", "No dessert for a day", "10 pushups", "No internet for a day")
val whine_comments = listOf<String>("That's a lot of whines", "Is that the whine-bulance?", "Again?!", "Yikes!", "Another whine bites the dust", "Add on the whines")

class MainActivity : AppCompatActivity() {

    lateinit var countView: TextView
    lateinit var consequenceView: TextView
    lateinit var buttonWhine: Button
    lateinit var buttonReset: Button

    companion object {
        const val COUNT_KEY = "COUNT_KEY"
    }
    // associate count value with textview to automatically update it
    private var count = 0
        set(value) {
            field = value
            countView.text = value.toString()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonWhine = findViewById<Button> (R.id.button_whine)
        buttonReset = findViewById<Button> (R.id.button_reset)
        countView = findViewById<TextView>(R.id.tv_count_output)
        consequenceView = findViewById<TextView>(R.id.tv_consequence)

        countView.text = count.toString()

        buttonWhine.setOnClickListener()
        {
            Log.i("MainTag", "Whine OnClick")
            
            Toast.makeText(this@MainActivity, whine_comments.random().toString(), Toast.LENGTH_LONG).show()

            count++

            if((count % 5) == 0) {
                consequenceView.text = consequences.random().toString()
            } else {
                consequenceView.text = ""
            }
        }

        buttonReset.setOnClickListener()
        {
            Log.i("MainTag", "Reset OnClick")
            count = 0
            consequenceView.text = ""
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i("MainTag", "OnSaveState" )
        outState.putInt(COUNT_KEY, count)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i("MainTag", "OnRestoreState")
        count = savedInstanceState.getInt(COUNT_KEY)
    }
}

