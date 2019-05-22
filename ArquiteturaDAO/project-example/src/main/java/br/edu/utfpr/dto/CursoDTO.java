package br.edu.utfpr.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
public class CursoDTO {
    private int idCurso;
    private String nome;

    public CursoDTO() {
    }

    public CursoDTO(int idCurso, String nome) {
        this.idCurso = idCurso;
        this.nome = nome;
    }

}