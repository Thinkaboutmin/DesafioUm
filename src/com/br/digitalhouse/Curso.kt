package com.br.digitalhouse

class Curso(val nome: String, val codigo: Int, qntMaxAlunos: Int) {
    var professorAdjunto: ProfessorAdjunto? = null
    var professorPrincipal: ProfessorPrincipal? = null
    val alunos = mutableListOf<Aluno>()

    override fun equals(other: Any?): Boolean {
        if (other != null && other is Curso) {
            return other.codigo == this.codigo
        }

        return super.equals(other)
    }

    fun adicionarUmAluno(umAluno: Aluno) {
        val aluno = alunos.find {it == umAluno}
        if (aluno == null) {
            alunos.add(umAluno)
        }
    }

    fun excluirAluno(umAluno: Aluno) {
        alunos.remove(umAluno)
    }
}