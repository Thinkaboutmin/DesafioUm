package com.br.digitalhouse

data class Aluno(val nome: String, val sobrenome: String, val codigo: Int) {
    override fun equals(other: Any?): Boolean {
        if (other != null && other is Aluno) {
            return other.codigo == this.codigo
        }

        return super.equals(other)
    }
}