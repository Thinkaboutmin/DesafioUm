package com.br.digitalhouse

class Principal {
    private var codigoAluno = 0
    private var codigoCurso = 0
    private var codigoProfessor = 0
    private var codigoMatricula = 0

    /*
     * Temos certeza absoluta que tudo realizado dentro dessa função não tera erros
     * de validação.
     *
     * Resumo do fluxo:
     * - Criar uma lista de alunos
     * - Criar uma lista de cursos
     * - Criar uma lista de Professores (adjuntos e titulares)
     * - Criar um DigitalHouseManger e registrar todos os itens das listas
     * - Matricular alguns alunos nos cursos
     */
    fun fluxoNormal() {
        val manager = DigitalHouseManager()
        println("Registro alunos")
        manager.registrarAluno("Joãozinho", "da Silva", codigoAluno++)
        manager.registrarAluno("Caio", "Santana", codigoAluno++)
        manager.registrarAluno("Maria", "da Cruz", codigoAluno++)
        manager.registrarAluno("Rafaela", "Vargas", codigoAluno++)
        manager.registrarAluno("Jomar", "Júnior", codigoAluno++)
        println("Fim do registro de alunos")

        println("Registrando cursos")
        manager.registrarCurso("Fullstack", 20001, 3)
        manager.registrarCurso("Android", 20002, 2)
        println("Fim do registro de cursos")

        println("Registrando professores")
        manager.registrarProfessorAdjunto("Sub Nerd", "do Kotlin", codigoProfessor++, 0)
        manager.registrarProfessorTitular("Nerd", "do Kotlin", codigoProfessor++, "Kotlin")
        println("Fim do registro de professores")

        println("Alocando professores aos cursos")
        manager.alocarProfessores(20001, codigoProfessor - 1, codigoProfessor - 2)
        manager.alocarProfessores(20002, codigoProfessor - 1, codigoProfessor - 2)
        println("Fim das alocações")

        println("Cadastrando alunos no curso 20001")
        manager.matricularAluno(codigoAluno - 5, 20001)
        manager.matricularAluno(codigoAluno - 4, 20001)
        manager.matricularAluno(codigoAluno - 3, 20001)
        println("Fim do cadastramento")

        println("Cadastrando alunos no curso 20002")
        manager.matricularAluno(codigoAluno - 2, 20002)
        manager.matricularAluno(codigoAluno - 1, 20002)
        println("Fim do cadastramento")
    }

    /*
     * Fluxo com vários erros, tanto de modelo quanto de regra de negócio.
     */
    fun fluxoComErros() {

    }
}