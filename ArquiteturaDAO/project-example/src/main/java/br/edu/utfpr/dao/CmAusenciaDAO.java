package br.edu.utfpr.dao;
import br.edu.utfpr.dto.CmAusenciaDTO;
import br.edu.utfpr.dto.CursoDTO;
import br.edu.utfpr.dto.DisciplinaDTO;
import lombok.extern.java.Log;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CmAusenciaDAO extends AbstractDAO{
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
    protected String getStringSQLCreate()  {
        return "INSERT INTO ausencia(data, motivo) VALUES (?, ?)";
    }

    @Override
    protected void mappingCreate(PreparedStatement stmt, Object entity) throws Exception {
        stmt.setInt(1, entity.getId());
        stmt.setDate(2, entity.getData());
        stmt.setString(3, entity.getMotivo());

    }

    @Override
    protected String getStringSQLList() { return "SELECT * FROM ausencia"; }

    @Override
    protected Object populateList(ResultSet result) throws Exception {
        return AulaDAO.builder()
                .id(result.getInt("id"))
                .qtd(result.getDate("data"))
                .tipo(result.getString("motivo"))
                .curso((CursoDTO. builder).id(result.getInt("idCurso")).build()).build()
                .disciplina(DisciplinaDTO.builder().id(result.getInt("idDisciplina")).build())
                .build();
    }

    @Override
    protected String getStringSQLDelete() {
        return "DELETE FROM ausencia WHERE id=?";
    }

    @Override
    protected String getStringSQLUpdate() {
        return "UPDATE ausencia SET data=?, motivo=? WHERE id=?";
    }

    @Override
    protected void mappingUpdate(PreparedStatement stmt, Object entity) throws Exception {
        stmt.setInt(1, entity.getId());
        stmt.setDate(2, entity.getData());
        stmt.setString(3, entity.getMotivo());
    }
}
