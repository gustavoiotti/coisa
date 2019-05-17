package br.edu.utfpr.dao;

import br.edu.utfpr.dto.CursoDTO;

import java.util.List;

public interface CursoDAOInterface {

    public boolean insereCurso(CursoDTO curso);

    public CursoDTO buscaCurso(int id);

    public boolean updateCurso(CursoDTO curso);

    public boolean deletaCurso(CursoDTO curso);

    public List<CursoDTO> listaTodos();
}
