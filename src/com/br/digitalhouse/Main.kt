package com.br.digitalhouse

fun main() {
    val principal = Principal()
    principal.fluxoNormal()
    println("\n".padEnd(80, '-'))
    principal.fluxoComErros()
}