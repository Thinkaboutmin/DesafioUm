package com.br.digitalhouse
import java.util.Date

/*
 * Representação de uma matricula para o sistema.
 *
 * A data da mátricula é realizado no momento de criação, pegando a data atual.
 */
data class Matricula(val aluno: Aluno, val curso: Curso) : Model {
    val data: Date = Date()

    /*
     * Valida se o curso e aluno.
     */
    override fun validar(): Array<String> {
        val erros = mutableListOf<String>()
        erros.addAll(this.aluno.validar())
        erros.addAll(this.curso.validar())

        return erros.toTypedArray()
    }
}