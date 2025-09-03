package com.example.quadtelas

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class EnderecoActivity : AppCompatActivity() {
    private val TAG = "EnderecoActivity"

    private lateinit var inputRua: EditText
    private lateinit var inputCidade: EditText
    private lateinit var btnVoltar: Button
    private lateinit var btnProximo: Button

    private var nome: String = ""
    private var email: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate")
        setContentView(R.layout.activity_endereco)

        inputRua = findViewById(R.id.inputRua)
        inputCidade = findViewById(R.id.inputCidade)
        btnVoltar = findViewById(R.id.btnVoltarEndereco)
        btnProximo = findViewById(R.id.btnProximoEndereco)

        nome = intent.getStringExtra("nome") ?: Prefs.nome(this)
        email = intent.getStringExtra("email") ?: Prefs.email(this)

        inputRua.setText(Prefs.rua(this))
        inputCidade.setText(Prefs.cidade(this))

        btnVoltar.setOnClickListener { finish() }

        btnProximo.setOnClickListener {
            val i = Intent(this, PreferenciasActivity::class.java).apply {
                putExtra("nome", nome)
                putExtra("email", email)
                putExtra("rua", inputRua.text.toString())
                putExtra("cidade", inputCidade.text.toString())
            }
            startActivity(i)
        }
    }

    override fun onStart() { super.onStart(); Log.i(TAG, "onStart") }
    override fun onResume() { super.onResume(); Log.i(TAG, "onResume") }
    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause (salvando parcial)")
        Prefs.saveEndereco(this, inputRua.text.toString(), inputCidade.text.toString())
    }
    override fun onStop() { super.onStop(); Log.i(TAG, "onStop") }
    override fun onRestart() { super.onRestart(); Log.i(TAG, "onRestart") }
    override fun onDestroy() { super.onDestroy(); Log.i(TAG, "onDestroy") }
}
