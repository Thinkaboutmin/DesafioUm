package com.br.digitalhouse

/*
 * Representação de um Professor Adjunto para o sistema.
 */
class ProfessorAdjunto(nome: String, sobrenome:String, codigo: Int, val monitoria: Int)
    : Professor(nome, sobrenome, codigo) {

}