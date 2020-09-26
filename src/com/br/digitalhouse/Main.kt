package com.br.digitalhouse

fun main() {
    val principal = Principal()
    principal.fluxoNormal()
    println("\n".padEnd(25, '-'))
    principal.fluxoComErros()
}