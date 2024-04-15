package com.example.ct_mod3_new

import android.app.Activity
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.google.android.material.textfield.TextInputEditText

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout)

        var sharedPref = this.getSharedPreferences("sharedPrefs", Activity.MODE_PRIVATE)
        val editor = sharedPref.edit()

        val delButton = findViewById(R.id.deleteButton) as Button
        val addButton = findViewById(R.id.addButton) as Button
        val editButton = findViewById(R.id.editButton) as Button
        val viewButton = findViewById(R.id.viewButton) as Button

        val delEditor = findViewById(R.id.DeleteEditor) as TextInputEditText
        val addEditorName = findViewById(R.id.addEditorName) as TextInputEditText
        val addEditorNum = findViewById(R.id.addEditorNumber) as TextInputEditText
        val editEditorName = findViewById(R.id.EditEditorName) as TextInputEditText
        val editEditorNum = findViewById(R.id.editEditorNum) as TextInputEditText
        val displayContact = findViewById(R.id.displayContact) as TextView
        val displayEditor = findViewById(R.id.displayEditor) as TextInputEditText


        delButton.setOnClickListener {
            val name = delEditor.text.toString()

            sharedPref.edit().remove(name).apply()
        }
        viewButton.setOnClickListener {
            val name = displayEditor.text.toString()
            val number = sharedPref.getString(name, null)
            if (number == null) {
                displayContact.text = "Contact doesn't exist"
            } else {
                displayContact.text = number
            }

        }
        editButton.setOnClickListener{
            val name = editEditorName.text.toString()
            val number = editEditorNum.text.toString()

            sharedPref.edit().putString(name, number).apply()



        }
        addButton.setOnClickListener {
            val name = addEditorName.text.toString()
            val number = addEditorNum.text.toString()


            sharedPref.edit().putString(name, number).apply()
        }

    }
}