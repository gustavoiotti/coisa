package br.edu.utfpr.negocio;

import java.util.List;

import br.edu.utfpr.dao.CursoDAO;
import br.edu.utfpr.dto.CursoDTO;
import br.edu.utfpr.excecao.NomePaisJaExisteException;

public class CursoNegocio {

    public void incluir(CursoDTO curso) throws NomePaisJaExisteException {
        if (this.listar().stream().anyMatch(p -> p.getNome().equalsIgnoreCase(curso.getNome())))
            throw new NomePaisJaExisteException(curso.getNome());

        CursoDAO dao = new CursoDAO();
        dao.insereCurso(curso);
    }

    public List<CursoDTO> listar() {
        CursoDAO dao = new CursoDAO();
        dao.listaTodos();
        throw new UnsupportedOperationException();
    }

    public void pesquisar(int id){
        CursoDAO dao = new CursoDAO();
        dao.buscaCurso(id);
    }

    public void atualizar(CursoDTO curso){
        CursoDAO dao = new CursoDAO();
        dao.updatePais(curso);
    }

    public void remover(CursoDTO curso){
        CursoDAO dao = new CursoDAO();
        dao.deletaCurso(curso);
    }

}