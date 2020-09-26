package com.br.digitalhouse

class Curso(val nome: String, val codigo: Int, val qntMaxAlunos: Int) : Model{
    var professorAdjunto: ProfessorAdjunto? = null
    var professorTitular: ProfessorTitular? = null
    private val alunos = mutableListOf<Aluno>()

    override fun equals(other: Any?): Boolean {
        if (other != null && other is Curso) {
            return other.codigo == this.codigo
        }

        return super.equals(other)
    }

    fun adicionarUmAluno(umAluno: Aluno): Boolean {
        val aluno = this.alunos.find {it == umAluno}
        if (this.alunos.size == this.qntMaxAlunos) {
            return false
        }

        if (aluno == null) {
            this.alunos.add(umAluno)
            return true
        }

        return false
    }

    fun excluirAluno(umAluno: Aluno) {
        alunos.remove(umAluno)
    }

    override fun validar(): Array<String> {
        val erros = mutableListOf<String>()
        if (this.qntMaxAlunos < 0) {
            erros.add("O número máximo de alunos do curso não pode ser negativo")
        }
        if (this.nome.isEmpty() || this.nome.isBlank()) {
            erros.add("O nome do curso está inválido")
        }
        if (this.professorAdjunto != null) {
            // Essa classe não será utilizada de forma assíncrona... né?
            erros.addAll(this.professorAdjunto!!.validar())
        }
        if (this.professorTitular != null) {
            // Mesmo caso do professor adjunto.
            erros.addAll(this.professorTitular!!.validar())
        }

        // FIXME: A validação de Collections é extremamente confusa e não da opções faceis para que sejam tratadas.
        // FIXME: A principal razão é o design da interface mas evitem colocar alunos aqui sem validar eles antes... Ok?
        return erros.toTypedArray()
    }
}