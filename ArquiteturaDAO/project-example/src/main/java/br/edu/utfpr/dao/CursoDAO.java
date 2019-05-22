package br.edu.utfpr.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import br.edu.utfpr.dto.CursoDTO;
import lombok.extern.java.Log;

@Log
public class CursoDAO extends AbstractDAO<CursoDTO>{

    // Responsável por criar a tabela Curso no banco
    public CursoDAO() {
        try (Connection conn = DriverManager.getConnection("jdbc:derby:memory:database;create=true")) {

            log.info("Criando tabela curso ...");
            conn.createStatement().executeUpdate(
            "CREATE TABLE curso (" +
						"id int NOT NULL GENERATED ALWAYS AS IDENTITY CONSTRAINT id_curso_pk PRIMARY KEY," +
						"nome varchar(255)");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String getStringSQLCreate() {
        return "INSERT INTO curso (nome) values (?)";
    }

    @Override
    protected void mappingCreate(PreparedStatement stmt, CursoDTO entity) throws Exception {
        stmt.setString(1, entity.getNome());
    }

    @Override
    protected String getStringSQLList() {
        return "SELECT * FROM curso";
    }

    @Override
    protected CursoDTO populateList(ResultSet result) throws Exception {
        return CursoDTO.builder()
                .idCurso(result.getInt("id"))
                .nome(result.getString("nome"))
                .build();
    }

    @Override
    protected String getStringSQLDelete() {
        return "DELETE FROM curso WHERE id=?";
    }

    @Override
    protected String getStringSQLUpdate() {
        return "UPADTE curso SET nome=? WHERE id=?";
    }

    @Override
    protected void mappingUpdate(PreparedStatement stmt, CursoDTO entity) throws Exception {
        stmt.setString(1, entity.getNome());
        stmt.setInt(2, entity.getIdCurso());
    }
}