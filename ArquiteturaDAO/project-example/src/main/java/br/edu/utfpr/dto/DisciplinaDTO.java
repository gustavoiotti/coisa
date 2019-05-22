package br.edu.utfpr.dto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DisciplinaDTO {
    private int idDisciplina;
    private String nome;
    private ProfessorDTO professor;
    private CursoDTO curso;
}
