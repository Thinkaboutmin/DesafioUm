package com.br.digitalhouse

class DigitalHouseManager {
    private val alunos = mutableListOf<Aluno>()
    private val cursos = mutableListOf<Curso>()
    private val matriculas = mutableListOf<Matricula>()
    private val professores = mutableListOf<Professor>()

    fun registrarCurso(nome: String, codigo: Int, qntMaxAlunos: Int) {
        val curso = Curso(nome, codigo, qntMaxAlunos)
        val cursaNaLista = cursos.find { it == curso }
        if (cursaNaLista == null) {
            cursos.add(curso)
        }
    }

    fun excluirCurso(codigo: Int) {
        val curso = cursos.find { it.codigo == codigo }
        if (curso != null) {
            cursos.remove(curso)
        }
    }

    fun registrarProfessorAdjunto(nome: String, sobrenome: String, codigo: Int, monitoria: Int) {
        val professorAdjunto = ProfessorAdjunto(nome, sobrenome, codigo, monitoria)
        val professor = professores.find { it is ProfessorAdjunto && it == professorAdjunto}
        if (professor == null) {
            professores.add(professorAdjunto)
        }
    }

    fun registrarProfessorTitular(nome: String, sobrenome: String, codigo: Int, especialidade: String) {
        val professorTitular = ProfessorTitular(nome, sobrenome, codigo, especialidade)
        val professor = professores.find {it is ProfessorTitular && it == professorTitular}
        if (professor != null) {
            professores.add(professorTitular)
        }
    }

    fun registrarAluno(nome: String, sobrenome: String, codigo: Int) {
        val aluno = Aluno(nome, sobrenome, codigo)
        val alunoLista = alunos.find {it == aluno}
        if (alunoLista == null) {
            alunos.add(aluno)
        }
    }

    fun matricularAluno(codigoAluno: Int, codigoCurso: Int) {
        val aluno = alunos.find {it.codigo == codigoAluno}
        val curso = cursos.find {it.codigo == codigoCurso}
        if (aluno != null && curso != null) {
            curso.adicionarUmAluno(aluno)
        }
    }

    fun alocarProfessores(codigoCurso: Int, codigoProfessorTitular: Int, codigoProfessorAdjunto: Int) {
        val curso = cursos.find{it.codigo == codigoCurso}
        val professorTitular = professores.find{it is ProfessorTitular && it.codigo == codigoProfessorTitular}
        val professorAdjunto = professores.find{it is ProfessorAdjunto && it.codigo == codigoProfessorAdjunto}

        if (curso != null && professorTitular != null && professorAdjunto != null) {
            curso.professorTitular = professorTitular as ProfessorTitular
            curso.professorAdjunto = professorAdjunto as ProfessorAdjunto
        }
    }
}