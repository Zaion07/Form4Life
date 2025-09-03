package com.example.quadtelas

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class PreferenciasActivity : AppCompatActivity() {
    private val TAG = "PreferenciasActivity"

    private lateinit var switchNews: Switch
    private lateinit var spinnerTema: Spinner
    private lateinit var btnVoltar: Button
    private lateinit var btnProximo: Button

    private var nome = ""
    private var email = ""
    private var rua = ""
    private var cidade = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate")
        setContentView(R.layout.activity_preferencias)

        switchNews = findViewById(R.id.switchNews)
        spinnerTema = findViewById(R.id.spinnerTema)
        btnVoltar = findViewById(R.id.btnVoltarPref)
        btnProximo = findViewById(R.id.btnProximoPref)

        nome = intent.getStringExtra("nome") ?: Prefs.nome(this)
        email = intent.getStringExtra("email") ?: Prefs.email(this)
        rua = intent.getStringExtra("rua") ?: Prefs.rua(this)
        cidade = intent.getStringExtra("cidade") ?: Prefs.cidade(this)

        val temas = listOf("Claro", "Escuro")
        spinnerTema.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, temas)

        switchNews.isChecked = Prefs.newsletter(this)
        val temaSalvo = Prefs.tema(this)
        spinnerTema.setSelection(if (temaSalvo == "Escuro") 1 else 0)

        var initializedSpinner = false
        spinnerTema.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                if (!initializedSpinner) { initializedSpinner = true; return }

                val temaEscolhido = parent.getItemAtPosition(position).toString()

                // Salva preferências
                Prefs.savePreferencias(this@PreferenciasActivity, switchNews.isChecked, temaEscolhido)

                // Aplica tema APENAS aqui
                val desiredMode = if (temaEscolhido == "Escuro")
                    AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO

                if (AppCompatDelegate.getDefaultNightMode() != desiredMode) {
                    AppCompatDelegate.setDefaultNightMode(desiredMode)
                    recreate() // atualiza visual imediatamente
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        btnVoltar.setOnClickListener { finish() }

        btnProximo.setOnClickListener {
            val tema = spinnerTema.selectedItem.toString()
            val i = Intent(this, ResumoActivity::class.java).apply {
                putExtra("nome", nome)
                putExtra("email", email)
                putExtra("rua", rua)
                putExtra("cidade", cidade)
                putExtra("newsletter", switchNews.isChecked)
                putExtra("tema", tema)
            }
            startActivity(i)
        }
    }

    override fun onStart() { super.onStart(); Log.i(TAG, "onStart") }
    override fun onResume() { super.onResume(); Log.i(TAG, "onResume") }
    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause (salvando parcial)")
        Prefs.savePreferencias(this, switchNews.isChecked, spinnerTema.selectedItem.toString())
    }
    override fun onStop() { super.onStop(); Log.i(TAG, "onStop") }
    override fun onRestart() { super.onRestart(); Log.i(TAG, "onRestart") }
    override fun onDestroy() { super.onDestroy(); Log.i(TAG, "onDestroy") }
}
