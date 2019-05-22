package br.edu.utfpr.dao;

import java.sql.Connection;
import java.sql.DriverManager;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.edu.utfpr.dto.CursoDTO;
import br.edu.utfpr.dto.DisciplinaDTO;
import lombok.extern.java.Log;

public class AulaDAO extends AbstractDAO {
    // Responsável por criar a tabela Cliente no banco.
    public AulaDAO() {

        try ( Connection conn = DriverManager.getConnection("jdbc:derby:memory:database;create=true")) {

            log.info("Criando tabela aula ...");
            conn.createStatement().executeUpdate(
                    "CREATE TABLE aula ("
                            + "id int NOT NULL GENERATED ALWAYS AS IDENTITY CONSTRAINT id_aula_pk PRIMARY KEY,"
                            + "qtd int,"
                            + "tipo boolean,"
                            + "data Date,");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected String getStringSQLCreate()  {
        return "INSERT INTO aula(qtd, tipo, data) VALUES (?, ?, ?)";
    }

    @Override
    protected void mappingCreate(PreparedStatement stmt, Object entity) throws Exception {
        stmt.setInt(1, entity.getId());
        stmt.setString(2, entity.getQtd());
        stmt.setInt(3, entity.getTipo());
        stmt.setDouble(4, entity.getData());
    }

    @Override
    protected String getStringSQLList() { return "SELECT * FROM professor"; }

    @Override
    protected Object populateList(ResultSet result) throws Exception {
        return AulaDAO.builder()
                .id(result.getInt("idAula"))
                .qtd(result.getString("qtd"))
                .tipo(result.getString("tipo"))
                .curso((CursoDTO. builder).id(result.getInt("idCurso")).build()).build()
                .disciplina(DisciplinaDTO.builder().id(result.getInt("idDisciplina")).build())
                .build();
    }

    @Override
    protected String getStringSQLDelete() {
        return "DELETE FROM aula WHERE id=?";
    }

    @Override
    protected String getStringSQLUpdate() {
        return "UPDATE professor SET qtd=?, tipo=?, data=? WHERE id=?";
    }

    @Override
    protected void mappingUpdate(PreparedStatement stmt, Object entity) throws Exception {
        stmt.setInt(1, entity.getId());
        stmt.setString(2, entity.getQtd());
        stmt.setInt(3, entity.getTipo());
        stmt.setDouble(4, entity.getData());
    }
}
