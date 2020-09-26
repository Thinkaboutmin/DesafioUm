package com.br.digitalhouse

/*
 * Classe de gerenciamento de cursos, alunos, matriculas e professores.
 */
class DigitalHouseManager {
    private val alunos = mutableListOf<Aluno>()
    private val cursos = mutableListOf<Curso>()
    private val matriculas = mutableListOf<Matricula>()
    private val professores = mutableListOf<Professor>()

    /*
     * Registra um curso a lista.
     *
     * O curso é validado, retornando falso caso tenha algum erro no modelo ou se o item
     * já existe na lista.
     */
    fun registrarCurso(nome: String, codigo: Int, qntMaxAlunos: Int): Boolean {
        val curso = Curso(nome, codigo, qntMaxAlunos)


        val cursaNaLista = cursos.find { it == curso }
        if (cursaNaLista == null) {
            val erros = curso.validar()
            if (erros.isEmpty()) {
                cursos.add(curso)
                return true
            }

            this.imprimirErros(erros, "Curso")
        }

        return false
    }

    /*
     * Exclui um curso da lista
     *
     * Essa função é livre de exceção, então mesmo se o curso não estiver na lista nenhuma mensagem ou erro
     * vai acontecer.
     */
    fun excluirCurso(codigo: Int) {
        val curso = cursos.find { it.codigo == codigo }
        if (curso != null) {
            cursos.remove(curso)
        }
    }

    /*
     * Registra um professor adjunto a lista de professores
     *
     * O professor adjunto é validado, retornando falso caso tenha algum erro no modelo ou se o item
     * já existe na lista.
     */
    fun registrarProfessorAdjunto(nome: String, sobrenome: String, codigo: Int, monitoria: Int): Boolean {
        val professorAdjunto = ProfessorAdjunto(nome, sobrenome, codigo, monitoria)
        val professor = professores.find { it is ProfessorAdjunto && it == professorAdjunto}
        if (professor == null) {
            val erros = professorAdjunto.validar()
            if (erros.isEmpty()) {
                professores.add(professorAdjunto)
                return true
            }

            this.imprimirErros(erros, "Professor Adjunto")
        }

        return false
    }

    /*
     * Registra um professor titular a lista de professores
     *
     * O professor titular é validado, retornando falso caso tenha algum erro no modelo ou se o item
     * já existe na lista.
     */
    fun registrarProfessorTitular(nome: String, sobrenome: String, codigo: Int, especialidade: String): Boolean {
        val professorTitular = ProfessorTitular(nome, sobrenome, codigo, especialidade)
        val professor = professores.find {it is ProfessorTitular && it == professorTitular}
        if (professor == null) {
            val erros = professorTitular.validar()
            if (erros.isEmpty()) {
                professores.add(professorTitular)
                return true
            }

            this.imprimirErros(erros, "Professor Titular")
        }

        return false
    }

    /*
     * Registra um aluno a lista de alunos
     *
     * O aluno é validado, retornando falso caso tenha algum erro no modelo ou se o item
     * já existe na lista.
     */
    fun registrarAluno(nome: String, sobrenome: String, codigo: Int): Boolean {
        val aluno = Aluno(nome, sobrenome, codigo)
        val alunoLista = alunos.find {it == aluno}
        if (alunoLista == null) {
            val erros = aluno.validar()
            if (erros.isEmpty()) {
                alunos.add(aluno)
                return true
            }

            this.imprimirErros(erros, "Aluno")
        }

        return false
    }

    /*
     * Matricula um aluno em um curso, gerando uma matricula.
     *
     * É verificado se o aluno e o curso existem, se sim retorna verdadeiro e matricula o aluno. Senão retorna falso
     * mais mensagem de erro ao usuário.
     */
    fun matricularAluno(codigoAluno: Int, codigoCurso: Int): Boolean {
        val aluno = alunos.find {it.codigo == codigoAluno}
        val curso = cursos.find {it.codigo == codigoCurso}
        if (aluno != null && curso != null) {
            curso.adicionarUmAluno(aluno)
            matriculas.add(Matricula(aluno, curso))
            return true
        }

        if (aluno == null) {
            println("Não foi possível encontrar o aluno com o código $codigoAluno")
        }
        if (curso == null) {
            println("Não foi possível encontrar o curso com o código $codigoCurso")
        }

        return false
    }

    /*
     * Aloca um professor adjunto e um titular a um curso.
     *
     * Caso o curso, professor adjunto ou professor titular não existam, é retornando falso mais mensagem ao usuário.
     * Caso contrario retorna verdadeira e coloca os professores no curso.
     */
    fun alocarProfessores(codigoCurso: Int, codigoProfessorTitular: Int, codigoProfessorAdjunto: Int): Boolean {
        val curso = cursos.find{it.codigo == codigoCurso}
        val professorTitular = professores.find{it is ProfessorTitular && it.codigo == codigoProfessorTitular}
        val professorAdjunto = professores.find{it is ProfessorAdjunto && it.codigo == codigoProfessorAdjunto}

        if (curso != null && professorTitular != null && professorAdjunto != null) {
            curso.professorTitular = professorTitular as ProfessorTitular
            curso.professorAdjunto = professorAdjunto as ProfessorAdjunto
            return true
        }

        if (curso == null) {
            println("Não foi possível encontrar o curso com o código $codigoCurso")
        }
        if (professorAdjunto == null) {
            println("Não foi possível encontrar o professor adjunto com o código $codigoProfessorAdjunto")
        }
        if(professorTitular == null) {
            println("Não foi possível encontrar o professor titular com o código $codigoProfessorTitular")
        }

        return false
    }

    /*
     * Consulta todas as matriculas de um determinado aluno.
     */
    fun consultarCursosMatriculado(codigoAluno: Int): Array<Matricula> {
        val aluno = alunos.find { it.codigo == codigoAluno }
        if (aluno == null) {
            println("Aluno não está registrado")
            return arrayOf()
        }
        val matriculasAluno = mutableListOf<Matricula>()
        for(matricula in this.matriculas) {
            if (matricula.aluno == aluno) {
                matriculasAluno.add(matricula)
            }
        }

        return matriculasAluno.toTypedArray()
    }

    /*
     * Imprimi erros de validação ao usuário.
     */
    private fun imprimirErros(erros: Array<String>, nome: String) {
        println("Parâmetros inválidos para $nome. Razão(ões):")
        for (erro in erros) {
            println("- $erro")
        }
    }
}