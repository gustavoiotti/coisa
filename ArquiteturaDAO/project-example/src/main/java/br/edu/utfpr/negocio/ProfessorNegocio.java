package br.edu.utfpr.negocio;

import java.util.List;

import br.edu.utfpr.dao.ProfessorDAO;
import br.edu.utfpr.dto.ProfessorDTO;
import br.edu.utfpr.excecao.NomeProfessorJaExisteException;

public class ProfessorNegocio extends AbstractNegocio<ProfessorDTO> {
    @Override
    public void persistirEntidade(ProfessorDTO entidade) {

    }

    @Override
    public List<ProfessorDTO> listar() {
        return null;
    }


    /*public void incluir(ProfessorDTO prof) throws NomeProfessorJaExisteException {

        if (this.listar().stream().anyMatch(c -> c.getNome().equalsIgnoreCase(prof.getNome())))
            throw new NomeProfessorJaExisteException(prof.getNome());

        ProfessorDAO dao = new ProfessorDAO();
        dao.insereProfessor(prof);
    }

    public List<ProfessorDTO> listar() {

        ProfessorDAO dao = new ProfessorDAO();
        dao.listaTodos();

        throw new UnsupportedOperationException();
    }

    public void pesquisar(int id){
        ProfessorDAO dao = new ProfessorDAO();
        dao.buscaProfessor(id);
    }

    public void atualizar(ProfessorDTO dto){
        ProfessorDAO dao = new ProfessorDAO();
        dao.updateProfessor(dto);
    }

    public void remover(ProfessorDTO dto){
        ProfessorDAO dao = new ProfessorDAO();
        dao.deletaProfessor(dto);
    }*/
}