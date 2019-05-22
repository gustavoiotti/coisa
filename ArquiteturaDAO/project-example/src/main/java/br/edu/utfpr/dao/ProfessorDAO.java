package br.edu.utfpr.dao;

import br.edu.utfpr.dto.ProfessorDTO;
import br.edu.utfpr.excecao.NomeProfessorMenor5CaracteresException;
import lombok.extern.java.Log;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log
public class ProfessorDAO extends AbstractDAO<ProfessorDTO> {

    // Responsável por criar a tabela Professor no banco.
    public ProfessorDAO() {

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test2?user=root&password=admin")) {

            log.info("Criando tabela professor ...");
            conn.createStatement().executeUpdate(
            "CREATE TABLE professor (" +
						"id int NOT NULL GENERATED ALWAYS AS IDENTITY CONSTRAINT id_professor_pk PRIMARY KEY," +
						"nome varchar(255)," +
                        "ra int," +
                        "estaAtivo boolean,");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String getStringSQLCreate() {
        return "INSERT INTO professor(nome, ra, estaAtivo) VALUES (?, ?, ?)";
    }

    @Override
    protected void mappingCreate(PreparedStatement stmt, ProfessorDTO entity) throws Exception {
        stmt.setString(1, entity.getNome());
        stmt.setInt(2, entity.getRa());
        stmt.setBoolean(3, entity.getEstaAtivo());
    }

    @Override
    protected String getStringSQLList() {
        return "SELECT * FROM professor";
    }

    @Override
    protected ProfessorDTO populateList(ResultSet result) throws Exception {
        return ProfessorDTO.builder()
                .idProfessor(result.getInt("id"))
                .nome(result.getString("nome"))
                .ra(result.getInt("ra"))
                .estaAtivo(result.getBoolean("estaAtivo"))
                .build();
    }

    @Override
    protected String getStringSQLDelete() {
        return "DELETE FROM professor WHERE id=?";
    }

    @Override
    protected String getStringSQLUpdate() {
        return "UPDATE professor SET nome=?, ra=?, estaAtivo=? WHERE id=?";
    }

    @Override
    protected void mappingUpdate(PreparedStatement stmt, ProfessorDTO entity) throws Exception {
        stmt.setString(1, entity.getNome());
        stmt.setInt(2, entity.getRa());
        stmt.setBoolean(3, entity.getEstaAtivo());
        stmt.setInt(4, entity.getIdProfessor());
    }
}