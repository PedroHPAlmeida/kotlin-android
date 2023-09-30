package br.com.alura.orgs.utils

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale

fun formataParaMoedaPtBr(valor: BigDecimal): String {
        val formatador: NumberFormat = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
        return formatador.format(valor)
}
