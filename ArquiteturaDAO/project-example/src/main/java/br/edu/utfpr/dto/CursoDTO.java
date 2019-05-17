package br.edu.utfpr.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CursoDTO {
    private int idCurso;
    private String nome;
    private int codigoDisciplina;

    public CursoDTO() {
    }

    public CursoDTO(int idCurso, String nome, int codigoDisciplina) {
        this.idCurso = idCurso;
        this.nome = nome;
        this.codigoDisciplina = codigoDisciplina;
    }
}