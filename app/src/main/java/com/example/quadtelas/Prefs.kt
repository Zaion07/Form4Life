// app/src/main/java/com/example/quadtelas/Prefs.kt
package com.example.quadtelas

import android.content.Context
import android.content.SharedPreferences

object Prefs {
    private const val FILE = "dados_usuario"
    private const val K_NOME = "nome"
    private const val K_EMAIL = "email"
    private const val K_RUA = "rua"
    private const val K_CIDADE = "cidade"
    private const val K_NEWS = "newsletter"
    private const val K_TEMA = "tema" // "Claro" ou "Escuro"

    private fun sp(ctx: Context): SharedPreferences =
        ctx.getSharedPreferences(FILE, Context.MODE_PRIVATE)

    fun savePerfil(ctx: Context, nome: String, email: String) {
        sp(ctx).edit().putString(K_NOME, nome).putString(K_EMAIL, email).apply()
    }

    fun saveEndereco(ctx: Context, rua: String, cidade: String) {
        sp(ctx).edit().putString(K_RUA, rua).putString(K_CIDADE, cidade).apply()
    }

    fun savePreferencias(ctx: Context, newsletter: Boolean, tema: String) {
        sp(ctx).edit().putBoolean(K_NEWS, newsletter).putString(K_TEMA, tema).apply()
    }

    fun saveTudo(
        ctx: Context, nome: String, email: String, rua: String, cidade: String,
        newsletter: Boolean, tema: String
    ) {
        sp(ctx).edit()
            .putString(K_NOME, nome)
            .putString(K_EMAIL, email)
            .putString(K_RUA, rua)
            .putString(K_CIDADE, cidade)
            .putBoolean(K_NEWS, newsletter)
            .putString(K_TEMA, tema)
            .apply()
    }

    fun nome(ctx: Context) = sp(ctx).getString(K_NOME, "") ?: ""
    fun email(ctx: Context) = sp(ctx).getString(K_EMAIL, "") ?: ""
    fun rua(ctx: Context) = sp(ctx).getString(K_RUA, "") ?: ""
    fun cidade(ctx: Context) = sp(ctx).getString(K_CIDADE, "") ?: ""
    fun newsletter(ctx: Context) = sp(ctx).getBoolean(K_NEWS, false)
    fun tema(ctx: Context) = sp(ctx).getString(K_TEMA, "Claro") ?: "Claro"
}
