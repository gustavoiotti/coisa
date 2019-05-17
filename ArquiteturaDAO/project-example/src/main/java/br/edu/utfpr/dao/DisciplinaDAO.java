package br.edu.utfpr.dao;

import br.edu.utfpr.dto.CursoDTO;
import br.edu.utfpr.dto.DisciplinaDTO;

import java.sql.*;
import java.util.List;

public class DisciplinaDAO implements DisciplinaDAOInterface {
    private Connection conn = null;
    private Statement query;
    private String sql;



    public DisciplinaDAO() {
        try (Connection conn = DriverManager.getConnection("jdbc:derby:memory:database;create=true")) {

            log.info("Criando tabela disciplina ...");
            conn.createStatement().executeUpdate(
                    "CREATE TABLE disciplina (" +
                            "id int NOT NULL GENERATED ALWAYS AS IDENTITY CONSTRAINT id_disciplina_pk PRIMARY KEY," +
                            "nome varchar(255),");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean insereDisciplina(DisciplinaDTO disciplina) {
        sql = "INSERT INTO disciplina VALUES (?, ?)";

        try{
            PreparedStatement query = conn.prepareStatement(sql);
            query.setInt(1, disciplina.getIdDisciplina());
            query.setString(2, disciplina.getNome());


            query.execute();
            query.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public DisciplinaDTO buscaDisciplina(int idDisciplina) {
        return null;
    }

    @Override
    public boolean updateDisciplina(DisciplinaDTO disciplina) {
        return false;
    }

    @Override
    public boolean deletaDisciplina(DisciplinaDTO disciplina) {
        return false;
    }

    @Override
    public List<DisciplinaDTO> listaTodos() {
        return null;
    }

}
