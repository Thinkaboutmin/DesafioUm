package com.br.digitalhouse

class ProfessorTitular(nome: String, sobrenome: String, codigo: Int, val especialidade: String)
      : Professor(nome, sobrenome, codigo){
      override fun validar(): Array<String> {
            val erros = super.validar().toMutableList()
            if (especialidade.isBlank() || especialidade.isEmpty()) {
                  erros.add("A especialidade do professor titular está inválida")
            }

            return erros.toTypedArray()
      }
}