package com.example.personalprofile


import android.os.Bundle
import android.content.Intent
import android.view.View
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


import android.content.Context
import android.content.SharedPreferences


import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.activity_main2.*

var name: String? = null
var email: String? = null
var password: String? = null
var age = 0
var isRemembered = false

//shared pref
lateinit var sharedPreferences: SharedPreferences
lateinit var spEditor: SharedPreferences.Editor
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


       val button = findViewById<Button>(R.id.registration)
        button.setOnClickListener {
            val intent = Intent(this, Main2Activity::class.java)
            // start your next activity
            startActivity(intent)
        }

        //init share pref
        sharedPreferences = getSharedPreferences("USER_INFO_SP", Context.MODE_PRIVATE)

        //get data from shared preferences
        name = sharedPreferences.getString("NAME", "")
        age = sharedPreferences.getInt("AGE", 0)
        email = sharedPreferences.getString("EMAIL", "")
        password = sharedPreferences.getString("PASSWORD", "")
        isRemembered = sharedPreferences.getBoolean("REMEMBER", false)

        //set data to views
        nameEt.setText(name)
        ageEt.setText("" + age)
        emailEt.setText(email)
        passwordEt.setText(password)
        rememberCb.setChecked(isRemembered)

        //click to input data from views
        saveBtn.setOnClickListener(View.OnClickListener {
            //get data from views
            name = "" + nameEt.getText().toString().trim()
            age = ageEt.getText().toString().trim().toInt()
            email = "" + emailEt.getText().toString().trim()
            password = "" + passwordEt.getText().toString().trim()
            if (rememberCb.isChecked()) {
                isRemembered = true
                //save data to shared preferences
                spEditor = sharedPreferences.edit()
                spEditor.putString("NAME", name)
                spEditor.putInt("AGE", age)
                spEditor.putString("EMAIL", email)
                spEditor.putString("PASSWORD", password)
                spEditor.putBoolean("REMEMBER", true)
                spEditor.apply()
                Toast.makeText(this@MainActivity, "Info is remembered...", Toast.LENGTH_SHORT)
                    .show()
            } else {
                isRemembered = false
                //don't save | remove data from shared preferences
                spEditor = sharedPreferences.edit()
                spEditor.clear()
                spEditor.apply()
                Toast.makeText(this@MainActivity, "Info is not remembered...", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }







}










