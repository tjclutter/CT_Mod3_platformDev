package com.example.ct_mod3_new

import android.app.Activity
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.google.android.material.textfield.TextInputEditText

class MainActivity : ComponentActivity() {
    //allows class to run once created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //conect activity to layout file
        setContentView(R.layout.layout)

        //create shared Preference
        var sharedPref = this.getSharedPreferences("sharedPrefs", Activity.MODE_PRIVATE)

        //create references to buttons in layout file
        val delButton = findViewById(R.id.deleteButton) as Button
        val addButton = findViewById(R.id.addButton) as Button
        val editButton = findViewById(R.id.editButton) as Button
        val viewButton = findViewById(R.id.viewButton) as Button

        //create references to EditText elements in layout file
        val delEditor = findViewById(R.id.DeleteEditor) as TextInputEditText
        val addEditorName = findViewById(R.id.addEditorName) as TextInputEditText
        val addEditorNum = findViewById(R.id.addEditorNumber) as TextInputEditText
        val editEditorName = findViewById(R.id.EditEditorName) as TextInputEditText
        val editEditorNum = findViewById(R.id.editEditorNum) as TextInputEditText
        val displayContact = findViewById(R.id.displayContact) as TextView
        val displayEditor = findViewById(R.id.displayEditor) as TextInputEditText

        //once the delete button is click the code in the body will execute
        delButton.setOnClickListener {
            //take text in delEditor text box
            val name = delEditor.text.toString()
            //remove contact from shared preference with name
            sharedPref.edit().remove(name).apply()
        }
        //executes once view button is clicked
        viewButton.setOnClickListener {
            //get name and number from EditText
            val name = displayEditor.text.toString()
            val number = sharedPref.getString(name, null)
            //if number is null tell user contact doesn't exist
            if (number == null) {
                displayContact.text = "Contact doesn't exist"
            //if contact does exist display number
            } else {
                displayContact.text = number
            }

        }

        //execute once edit button is clicked
        editButton.setOnClickListener{
            //get info from EditText boxes
            val name = editEditorName.text.toString()
            val number = editEditorNum.text.toString()

            //check if name is in the contact book
            if (sharedPref.contains(name)) {
                //update contact with new number
                sharedPref.edit().putString(name, number).apply()
            }


        }

        //execute once add button is clicked
        addButton.setOnClickListener {
            //retrieve name and number from textbox
            val name = addEditorName.text.toString()
            val number = addEditorNum.text.toString()
            //create new contact in shared preference
            sharedPref.edit().putString(name, number).apply()
        }

    }
}