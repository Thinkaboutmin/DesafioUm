package com.br.digitalhouse

/*
 * Base para modelagem, todos os modelos devem implementar essa inteface.
 */
interface Model {
    fun validar(): Array<String>
}