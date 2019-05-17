package br.edu.utfpr.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import br.edu.utfpr.dto.CursoDTO;
import lombok.extern.java.Log;

@Log
public class CursoDAO implements CursoDAOInterface {

    private Connection conn = null;
    private Statement query;
    private String sql;

    // Responsável por criar a tabela Curso no banco
    public CursoDAO() {
        try (Connection conn = DriverManager.getConnection("jdbc:derby:memory:database;create=true")) {

            log.info("Criando tabela curso ...");
            conn.createStatement().executeUpdate(
            "CREATE TABLE curso (" +
						"id int NOT NULL GENERATED ALWAYS AS IDENTITY CONSTRAINT id_curso_pk PRIMARY KEY," +
						"nome varchar(255)," +
						"codigoDisciplina int)");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean insereCurso(CursoDTO curso) {
        sql = "INSERT INTO curso VALUES (?, ?, ?, ?)";

        try{
            PreparedStatement query = conn.prepareStatement(sql);
            query.setInt(1, curso.getIdCurso());
            query.setString(2, curso.getNome());
            query.setString(3, curso.getSigla());
            query.setInt(4, curso.getCodigoDisciplina());

            query.execute();
            query.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public CursoDTO buscaPais(int idCurso) {

        CursoDTO pais = new CursoDTO();

        sql = "SELECT * FROM curso WHERE idCurso = " + idCurso;

        try{
            query = conn.createStatement();
            ResultSet rs = query.executeQuery(sql);

            while(rs.next()){
                curso.setIdPais(rs.getInt("idPais"));
                curso.setNome(rs.getString("nome"));
                curso.setCodigoDisciplina(rs.getInt("codigo"));
                curso.setCodigoDisciplina(rs.getInt("codigoDisciplina"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return curso;
    }

    @Override
    public boolean updatePais(CursoDTO curso) {
        sql = "UPDATE curso SET idCurso = ?, nome = ?, codigoDisciplina = ? WHERE idCurso = ?";

        try{
            PreparedStatement query = conn.prepareStatement(sql);
            query.setInt(1, curso.getIdCurso());
            query.setString(2, curso.getNome());
            query.setInt(3, curso.getCodigoDisciplina());
            query.setInt(4, curso.getIdCurso());

            query.execute();
            query.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deletaCurso(CursoDTO curso) {
        sql = "DELETE FROM curso WHERE idCurso = ?";

        try{
            PreparedStatement query = conn.prepareStatement(sql);
            query.setInt(1, curso.getIdCurso());
            query.execute();
            query.close();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<CursoDTO> listaTodos() {
        CursoDTO curso = new CursoDTO();
        List<CursoDTO> listaCurso = new ArrayList<CursoDTO>();

        sql = "SELECT * FROM curso";

        try{
            query = conn.createStatement();
            ResultSet rs = query.executeQuery(sql);

            while(rs.next()){
                curso.setIdCurso(rs.getInt("idCurso"));
                curso.setNome(rs.getString("nome"));
                curso.setCodigoDisciplina(rs.getInt("codigoDisciplina"));

                listaCurso.add(curso);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaCurso;
    }
}