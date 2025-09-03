package com.example.quadtelas

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class PerfilActivity : AppCompatActivity() {
    private val TAG = "PerfilActivity"

    private lateinit var inputNome: EditText
    private lateinit var inputEmail: EditText
    private lateinit var btnProximo: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate")
        setContentView(R.layout.activity_perfil)

        inputNome = findViewById(R.id.inputNome)
        inputEmail = findViewById(R.id.inputEmail)
        btnProximo = findViewById(R.id.btnProximoPerfil)

        inputNome.setText(Prefs.nome(this))
        inputEmail.setText(Prefs.email(this))

        btnProximo.setOnClickListener {
            val i = Intent(this, EnderecoActivity::class.java).apply {
                putExtra("nome", inputNome.text.toString())
                putExtra("email", inputEmail.text.toString())
            }
            startActivity(i)
        }
    }

    override fun onStart() { super.onStart(); Log.i(TAG, "onStart") }
    override fun onResume() { super.onResume(); Log.i(TAG, "onResume") }
    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause (salvando parcial)")
        Prefs.savePerfil(this, inputNome.text.toString(), inputEmail.text.toString())
    }
    override fun onStop() { super.onStop(); Log.i(TAG, "onStop") }
    override fun onRestart() { super.onRestart(); Log.i(TAG, "onRestart") }
    override fun onDestroy() { super.onDestroy(); Log.i(TAG, "onDestroy") }
}
