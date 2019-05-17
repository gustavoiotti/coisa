package br.edu.utfpr.dao;
import br.edu.utfpr.dto.CmAusenciaDTO;
import lombok.extern.java.Log;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CmAusenciaDAO implements CmAusenciaDAOInterface{
    private Connection conn = null;
    private Statement query;
    private String sql;

    public CmAusenciaDAO() {

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test2?user=root&password=admin")) {

            log.info("Criando tabela ausencia ...");
            conn.createStatement().executeUpdate(
                    "CREATE TABLE ausencia (" +
                            "id int NOT NULL GENERATED ALWAYS AS IDENTITY CONSTRAINT id_cm_pk PRIMARY KEY," +
                            "data date," +
                            "String motivo");



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean insereCmAusencia(CmAusenciaDTO cm) {

        sql = "INSERT INTO professor VALUES (?, ?, ?)";

        try{
            PreparedStatement query = conn.prepareStatement(sql);
            query.setInt(1, cm.getIdCm());
            query.setString(2, cm.getData());
            query.setInt(3, cm.getMotivo());


            query.execute();
            query.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public CmAusenciaDTO buscaCmAusencia(int idCm) {
        return null;
    }

    @Override
    public List<CmAusenciaDTO> listaTodos() {
        return null;
    }

    //Falta o Delete

}
