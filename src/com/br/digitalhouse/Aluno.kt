package com.br.digitalhouse

data class Aluno(val nome: String, val sobrenome: String, val codigo: Int) : Model{
    override fun equals(other: Any?): Boolean {
        if (other != null && other is Aluno) {
            return other.codigo == this.codigo
        }

        return super.equals(other)
    }

    override fun validar(): Array<String> {
        val erros = mutableListOf<String>()

        if (this.nome.isEmpty() || this.nome.isBlank()) {
            erros.add("Nome do Aluno está inválido")
        }
        if (this.sobrenome.isEmpty() || this.sobrenome.isBlank()) {
            erros.add("Sobrenome do Aluno está inválido")
        }

        return erros.toTypedArray()
    }
}