package br.edu.utfpr.dao;

import br.edu.utfpr.dto.CursoDTO;
import br.edu.utfpr.dto.DisciplinaDTO;
import br.edu.utfpr.dto.ProfessorDTO;
import lombok.extern.java.Log;

import java.sql.*;
import java.util.List;

@Log
public abstract class DisciplinaDAO extends AbstractDAO<DisciplinaDTO> {

    public DisciplinaDAO() {
        try (Connection conn = DriverManager.getConnection("jdbc:derby:memory:database;create=true")) {

            log.info("Criando tabela disciplina ...");
            conn.createStatement().executeUpdate(
                    "CREATE TABLE disciplina (" +
                            "id int NOT NULL GENERATED ALWAYS AS IDENTITY CONSTRAINT id_disciplina_pk PRIMARY KEY,"
                            + "nome varchar(255),"
                            + "id_professor int,"
                            + "id_curso int");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String getStringSQLCreate() {
        return "INSERT INTO disciplina (nome, id_professor, id_curso) VALUES (?, ?, ?)";
    }

    @Override
    protected void mappingCreate(PreparedStatement stmt, DisciplinaDTO entity) throws Exception {
        stmt.setString(1, entity.getNome());
        stmt.setInt(2, entity.getProfessor().getIdProfessor());
        stmt.setInt(3, entity.getCurso().getIdCurso());
    }

    @Override
    protected String getStringSQLList() {
        return "SELECT * from disciplina";
    }

    @Override
    protected DisciplinaDTO populateList(ResultSet result) throws Exception {
        return DisciplinaDTO.builder()
                .idDisciplina(result.getInt("id"))
                .nome(result.getString("nome"))
                .professor(ProfessorDTO.builder().idProfessor((result.getInt("id_professor"))).build())
                .curso(CursoDTO.builder().idCurso(result.getInt("id_curso")).build())
                .build();
    }

    @Override
    protected String getStringSQLDelete() {
        return "DELETE FROM disciplina WHERE id=?";
    }

    @Override
    protected String getStringSQLUpdate() {
        return "UPDATE disciplina SET nome=?, id_professor=?, id_curso=? WHERE id=?";
    }

    @Override
    protected void mappingUpdate(PreparedStatement stmt, DisciplinaDTO entity) throws Exception {
        stmt.setString(1, entity.getNome());
        stmt.setInt(2, entity.getProfessor().getIdProfessor());
        stmt.setInt(3, entity.getCurso().getIdCurso());
        stmt.setInt(4, entity.getIdDisciplina());
    }
}
