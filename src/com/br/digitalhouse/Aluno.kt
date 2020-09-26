package com.br.digitalhouse

/*
 * Representação de um estudante para o sistema.
 */
data class Aluno(val nome: String, val sobrenome: String, val codigo: Int) : Model{
    /*
     * Compara o aluno pelo código e não pelos outros atributos.
     */
    override fun equals(other: Any?): Boolean {
        if (other != null && other is Aluno) {
            return other.codigo == this.codigo
        }

        return super.equals(other)
    }

    /*
     * Valida se o nome e sobrenome não estão vázio ou somente com espaços em branco.
     */
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