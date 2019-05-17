package br.edu.utfpr.dao;

import br.edu.utfpr.dto.ProfessorDTO;

import java.util.List;

public interface ProfessorDAOInterface {

    public boolean insereProfessor(ProfessorDTO professor);

    public ProfessorDTO buscaProfessor(int id);

    public boolean updateProfessor(ProfessorDTO professor);

    public boolean deletaProfessor(ProfessorDTO professor);

    public List<ProfessorDTO> listaTodos();



}
