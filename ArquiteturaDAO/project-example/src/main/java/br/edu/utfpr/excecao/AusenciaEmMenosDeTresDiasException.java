package br.edu.utfpr.excecao;

public class AusenciaEmMenosDeTresDiasException extends Exception {
    public AusenciaEmMenosDeTresDiasException(String descricao) {
        super(descricao);
    }
}