package br.edu.utfpr.excecao;

public class NomeProfessorJaExisteException extends Exception {
    public NomeProfessorJaExisteException(String descricao) {
        super(descricao);
    }
}