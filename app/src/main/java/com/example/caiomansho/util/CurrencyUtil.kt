package com.example.caiomansho.util

class CurrencyUtil {
    fun currencyTextToFloat(text: String): Float {
        return text
            .replace(Regex("[^0-9,]"), "")   // Remove R$, pontos e espaços
            .replace(",", ".")              // Troca vírgula por ponto
            .toFloatOrNull() ?: 0f
    }
}