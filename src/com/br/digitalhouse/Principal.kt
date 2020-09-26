package com.br.digitalhouse

/*
 * Container de fluxos do sistema.
 */
class Principal {
    private var codigoAluno = 0
    private var codigoCurso = 0
    private var codigoProfessor = 0

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
        println("\nRegistro alunos\n")
        manager.registrarAluno("Joãozinho", "da Silva", codigoAluno++)
        manager.registrarAluno("Caio", "Santana", codigoAluno++)
        manager.registrarAluno("Maria", "da Cruz", codigoAluno++)
        manager.registrarAluno("Rafaela", "Vargas", codigoAluno++)
        manager.registrarAluno("Jomar", "Júnior", codigoAluno++)
        println("\nFim do registro de alunos\n")

        println("\nRegistrando cursos\n")
        manager.registrarCurso("Fullstack", 20001, 3)
        manager.registrarCurso("Android", 20002, 2)
        println("\nFim do registro de cursos\n")

        println("\nRegistrando professores\n")
        manager.registrarProfessorAdjunto("Sub Nerd", "do Kotlin", codigoProfessor++, 0)
        manager.registrarProfessorTitular("Nerd", "do Kotlin", codigoProfessor++, "Kotlin")
        println("\nFim do registro de professores\n")

        println("\nAlocando professores aos cursos\n")
        manager.alocarProfessores(20001, codigoProfessor - 1, codigoProfessor - 2)
        manager.alocarProfessores(20002, codigoProfessor - 1, codigoProfessor - 2)
        println("\nFim das alocações\n")

        println("\nCadastrando alunos no curso 20001\n")
        manager.matricularAluno(codigoAluno - 5, 20001)
        manager.matricularAluno(codigoAluno - 4, 20001)
        manager.matricularAluno(codigoAluno - 3, 20001)
        println("\nFim do cadastramento\n")

        println("\nCadastrando alunos no curso 20002\n")
        manager.matricularAluno(codigoAluno - 2, 20002)
        manager.matricularAluno(codigoAluno - 1, 20002)
        println("\nFim do cadastramento\n")

        println("\nConsultar matriculas de um aluno\n")
        val matriculas = manager.consultarCursosMatriculado(codigoAluno - 1)
        for (matricula in matriculas) {
            println("O ${matricula.aluno.nome} está matriculado no curso de ${matricula.curso.nome}")
        }
        println("\nFim da consulta de matricuals do aluno\n")
    }

    /*
     * Fluxo com vários erros, tanto de modelo quanto de regra de negócio.
     */
    fun fluxoComErros() {
        val manager = DigitalHouseManager()

        println("\nRegistro de usuário com erro\n")
        manager.registrarAluno("", "", 0)
        println("\nFim do registro de usuário com erro\n")

        println("\nRegistro de curso com erro\n")
        manager.registrarCurso("", 0, -1)
        println("\nFim do registro de curso com erro\n")

        println("\nRegistro de professor titular com erro\n")
        manager.registrarProfessorTitular("", "", 0, "")
        println("\nFim do registro de professor titular com erro\n")

        println("\nRegistro de professor adjuvante com erro\n")
        manager.registrarProfessorAdjunto("", "", 0, 0)
        println("\nFim do registro de professor adjuvante com erro\n")

        println("\nMatricular aluno com erro\n")
        manager.matricularAluno(0, 0)
        println("\nFim da matricula com erro\n")

        println("\nAlocar professores com erro\n")
        manager.alocarProfessores(0, 0, 0)
        println("\nFim da alocação de professores com erro\n")

        println("\nMatricula de alunos no curso cheio\n")
        manager.registrarAluno("Teste", "Testin", codigoAluno++)
        manager.registrarCurso("Teste", codigoCurso++, 1)
        manager.matricularAluno(codigoAluno - 1, codigoCurso - 1)
        // Sim é o mesmo, mas a validação de tamanho deve ocorrer primeiro já que ela é a menos custosa.
        manager.matricularAluno(codigoAluno - 1, codigoCurso - 1)
        println("\nFim da matricula de alunos no curso cheio\n")
    }
}