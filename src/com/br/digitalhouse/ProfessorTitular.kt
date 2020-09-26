package com.br.digitalhouse

/*
 * Representação de um professor titular para o sistema.
 */
class ProfessorTitular(nome: String, sobrenome: String, codigo: Int, val especialidade: String)
      : Professor(nome, sobrenome, codigo){
      /*
       * Valida se a especialização não está vazia ou com espaços em branco.
       *
       * Naturalmente, é validado também as propriedades de um Professor.
       */
      override fun validar(): Array<String> {
            val erros = super.validar().toMutableList()
            if (especialidade.isBlank() || especialidade.isEmpty()) {
                  erros.add("A especialidade do professor titular está inválida")
            }

            return erros.toTypedArray()
      }
}