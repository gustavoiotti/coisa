package br.edu.utfpr.dao;

import br.edu.utfpr.dto.CmAusenciaDTO;

import java.util.List;

public interface CmAusenciaDAOInterface {
    //CmAusenciaDAO getCmAusenciaDAO();

    public boolean insereCmAusencia(CmAusenciaDTO cmAusencia);

    public CmAusenciaDTO buscaCmAusencia(int idCm);

    public List<CmAusenciaDTO> listaTodos();
}
