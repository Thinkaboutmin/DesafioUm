package com.br.digitalhouse
import java.util.Date

class Matricula(val aluno: Aluno, val curso: Curso) : Model {
    val data: Date = Date()

    override fun validar(): Array<String> {
        val erros = mutableListOf<String>()
        erros.addAll(this.aluno.validar())
        erros.addAll(this.curso.validar())

        return erros.toTypedArray()
    }
}