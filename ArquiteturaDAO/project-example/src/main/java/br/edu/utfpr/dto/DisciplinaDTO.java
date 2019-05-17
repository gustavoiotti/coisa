package br.edu.utfpr.dto;
import lombok.Builder;
import lombok.Data;

@Date
@Builder
public class DisciplinaDTO {
    private int idDisciplina;
    private String NomeD;
    private ProfessorDTO professor;
    private CursoDTO curso;
}
