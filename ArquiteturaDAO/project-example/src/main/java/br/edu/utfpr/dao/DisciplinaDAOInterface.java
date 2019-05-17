package br.edu.utfpr.dao;

import br.edu.utfpr.dto.DisciplinaDTO;


import java.util.List;

public interface DisciplinaDAOInterface {
    public boolean insereDisciplina(DisciplinaDTO disciplina);

    public DisciplinaDTO buscaDisciplina(int idDisciplina);

    public boolean updateDisciplina(DisciplinaDTO disciplina);

    public boolean deletaDisciplina(DisciplinaDTO disciplina);

    public List<DisciplinaDTO> listaTodos();
}
