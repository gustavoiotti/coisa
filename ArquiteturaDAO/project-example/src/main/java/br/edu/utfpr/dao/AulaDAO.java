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
    protected String getStringSQLIncluir() {
        return "INSERT INTO aula (qtd, tipo, data) VALUES (?, ?, ?)";
    }

    @Override
    protected void mapeamentoObjetoRelationalIncluir(PreparedStatement statement, AulaDAO a) throws Exception {
        statement.setInt(1, a.getId());
        statement.setString(2, a.getQtd());
        statement.setInt(3, a.getTipo());
        statement.setDouble(4, a.getData());

    }

    @Override
    protected String getStringSQLListar() {
        return "SELECT * FROM aula";
    }

    @Override
    protected AulaDAO populaObjetoListar(ResultSet result) throws Exception {
        return AulaDAO.builder()
                .id(result.getInt("idAula"))
                .qtd(result.getString("qtd"))
                .tipo(result.getString("tipo"))
                .curso((CursoDTO. builder).id(result.getInt("idCurso")).build()).build()
                .disciplina(DisciplinaDTO.builder().id(result.getInt("idDisciplina")).build())
                .build();
    }

    @Override
    protected String getStringSQLExcluir() {
        return "DELETE FROM aula WHERE id=?";
    }

    @Override
    protected String getStringSQLAlterar() {
        return "UPDATE cliente SET qtd=?, tipo=?, WHERE id=?";
    }

    @Override
    protected void mapeamentoObjetoRelationalAlterar(PreparedStatement statement, AulaDAO a) throws Exception {
        statement.setInt(1, a.getId());
        statement.setString(2, a.getQtd());
        statement.setInt(3, a.getTipo());
        statement.setDouble(4, a.getData())
    }

}
