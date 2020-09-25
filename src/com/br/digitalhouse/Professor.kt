package com.br.digitalhouse

data class Professor(val nome: String, val sobrenome: String, val codigo: Int) {
    override fun equals(other: Any?): Boolean {
        if (other != null && other is Professor) {
            return other.codigo == this.codigo
        }

        return false
    }
}