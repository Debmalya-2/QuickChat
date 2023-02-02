package com.debmalya.quickchat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
    private lateinit var edt_email: EditText
    private lateinit var edt_password: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnSignup: Button

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()
        mAuth = FirebaseAuth.getInstance()
        edt_email = findViewById(R.id.edt_email)
        edt_password = findViewById(R.id.edt_password)
        btnLogin = findViewById(R.id.btnLogin)
        btnSignup = findViewById(R.id.btnSignup)
        btnSignup.setOnClickListener{
            val intent = Intent(this,Signup::class.java)
            startActivity(intent)
        }
        btnLogin.setOnClickListener{
            val email = edt_email.text.toString()
            val password = edt_password.text.toString()
            login(email,password);
        }
    }

    private fun login(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this){task->
            if(task.isSuccessful){
                val intent = Intent(this, MainActivity::class.java)
                finish()
                startActivity(intent)
            }
            else{
                Toast.makeText(this,"Invalid credentials!!",Toast.LENGTH_SHORT).show();
            }
        }
    }
}