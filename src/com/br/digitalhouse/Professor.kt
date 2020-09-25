package com.br.digitalhouse

abstract class Professor(val nome: String, val sobrenome: String, val codigo: Int) : Model {
    override fun equals(other: Any?): Boolean {
        if (other != null && other is Professor) {
            return other.codigo == this.codigo
        }

        return super.equals(other)
    }

    override fun validar(): Array<String> {
        val erros = mutableListOf<String>()
        if (this.nome.isEmpty() || this.nome.isBlank()) {
            erros.add("O nome do professor está inválido")
        }
        if (this.sobrenome.isEmpty() || this.sobrenome.isBlank()) {
            erros.add("O sobrenome do professor está inválido")
        }

        return erros.toTypedArray()
    }
}