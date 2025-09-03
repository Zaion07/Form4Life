package com.example.quadtelas

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ResumoActivity : AppCompatActivity() {
    private val TAG = "ResumoActivity"

    private lateinit var txtNome: TextView
    private lateinit var txtEmail: TextView
    private lateinit var txtRua: TextView
    private lateinit var txtCidade: TextView
    private lateinit var txtNews: TextView
    private lateinit var txtTema: TextView
    private lateinit var btnEditar: Button
    private lateinit var btnFinalizar: Button

    private var nome = ""
    private var email = ""
    private var rua = ""
    private var cidade = ""
    private var newsletter = false
    private var tema = "Claro"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate")
        setContentView(R.layout.activity_resumo)

        txtNome = findViewById(R.id.txtResumoNome)
        txtEmail = findViewById(R.id.txtResumoEmail)
        txtRua = findViewById(R.id.txtResumoRua)
        txtCidade = findViewById(R.id.txtResumoCidade)
        txtNews = findViewById(R.id.txtResumoNews)
        txtTema = findViewById(R.id.txtResumoTema)
        btnEditar = findViewById(R.id.btnEditarPerfil)
        btnFinalizar = findViewById(R.id.btnFinalizar)

        nome = intent.getStringExtra("nome") ?: Prefs.nome(this)
        email = intent.getStringExtra("email") ?: Prefs.email(this)
        rua = intent.getStringExtra("rua") ?: Prefs.rua(this)
        cidade = intent.getStringExtra("cidade") ?: Prefs.cidade(this)
        newsletter = intent.getBooleanExtra("newsletter", Prefs.newsletter(this))
        tema = intent.getStringExtra("tema") ?: Prefs.tema(this)

        preencherResumo()

        btnEditar.setOnClickListener {
            startActivity(Intent(this, PerfilActivity::class.java))
            finish()
        }

        btnFinalizar.setOnClickListener {
            Prefs.saveTudo(this, nome, email, rua, cidade, newsletter, tema)
            Toast.makeText(this, "Salvo com sucesso!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun preencherResumo() {
        txtNome.text = "Nome: $nome"
        txtEmail.text = "Email: $email"
        txtRua.text = "Rua: $rua"
        txtCidade.text = "Cidade: $cidade"
        txtNews.text = "Newsletter: ${if (newsletter) "Sim" else "Não"}"
        txtTema.text = "Tema: $tema"
    }

    override fun onStart() { super.onStart(); Log.i(TAG, "onStart") }
    override fun onResume() { super.onResume(); Log.i(TAG, "onResume") }
    override fun onPause() { super.onPause(); Log.i(TAG, "onPause") }
    override fun onStop() { super.onStop(); Log.i(TAG, "onStop") }
    override fun onRestart() { super.onRestart(); Log.i(TAG, "onRestart") }
    override fun onDestroy() { super.onDestroy(); Log.i(TAG, "onDestroy") }
}
