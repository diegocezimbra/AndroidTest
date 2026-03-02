package br.com.androidtest.services.common

import android.content.Context

class AssetReader(private val context: Context) {
    fun readText(path: String): String =
        context.assets.open(path).bufferedReader().use { it.readText() }
}
