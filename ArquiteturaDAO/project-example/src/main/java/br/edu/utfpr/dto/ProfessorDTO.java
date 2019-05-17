package br.edu.utfpr.dto;

import lombok.Builder;
import lombok.Data;
import br.edu.utfpr.excecao.NomeProfessorMenor5CaracteresException;

@Data
@Builder
public class ProfessorDTO {
    /*private int idProfessor;
    private String nome;
    private int idade;
    private String telefone;
    private String disciplina;
    private CursoDTO curso;*/

    private int idProfessor;
    private String nome;
    private int ra;
    private Boolean ativo;
    private CursoDTO curso;

    public ProfessorDTO(){
    }

    public ProfessorDTO(int idProfessor, String nome, int ra, Boolean ativo,  CursoDTO curso) {
        this.idProfessor = idProfessor;
        this.nome = nome;
        this.ra = ra;
        this.ativo = ativo;
        //this.disciplina = disciplina;
        this.curso = curso;
    }

    public void setNome(String nome) throws NomeProfessorMenor5CaracteresException {
        if (nome.length() < 5)
            throw new NomeProfessorMenor5CaracteresException(nome);
        this.nome = nome;
    }

}