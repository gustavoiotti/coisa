package br.edu.utfpr.dao;

import br.edu.utfpr.dto.ProfessorDTO;
import br.edu.utfpr.excecao.NomeProfessorMenor5CaracteresException;
import lombok.extern.java.Log;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log
public class ProfessorDAO implements ProfessorDAOInterface {

    private Connection conn = null;
    private Statement query;
    private String sql;

    // Responsável por criar a tabela Cliente no banco.
    public ProfessorDAO() {

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test2?user=root&password=admin")) {

            log.info("Criando tabela professor ...");
            conn.createStatement().executeUpdate(
            "CREATE TABLE professor (" +
						"id int NOT NULL GENERATED ALWAYS AS IDENTITY CONSTRAINT id_professor_pk PRIMARY KEY," +
						"nome varchar(255)," +
						"telefone varchar(30)," + 
						"idade int,");



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean insereProfessor(ProfessorDTO professor) {

        sql = "INSERT INTO professor VALUES (?, ?, ?, ?, ?, ?)";

        try{
            PreparedStatement query = conn.prepareStatement(sql);
            query.setInt(1, professor.getIdProfessor());
            query.setString(2, professor.getNome());
            query.setString(3, professor.getTelefone());
            query.setInt(4, professor.getIdade());

            query.execute();
            query.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ProfessorDTO buscaProfessor(int idProfessor) {

        ProfessorDTO professor = new ProfessorDTO();

        sql = "SELECT * FROM professor WHERE idProfessor = " + idProfessor;

        try{
            query = conn.createStatement();
            ResultSet rs = query.executeQuery(sql);

            while(rs.next()){
                professor.setIdProfessor(rs.getInt("idProfessor"));
                professor.setNome(rs.getString("nome"));
                professor.setTelefone(rs.getString("telefone"));
                professor.setIdade(rs.getInt("idade"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NomeProfessorMenor5CaracteresException e) {
            e.printStackTrace();
        }
        return professor;
    }

    @Override
    public boolean updateProfessor(ProfessorDTO professor) {
        sql = "UPDATE professor SET idProfessor = ?, nome = ?, telefone = ?, idade = ?, " +
                "WHERE idProfessor = ?";

        try{
            PreparedStatement query = conn.prepareStatement(sql);
            query.setInt(1, professor.getIdProfessor());
            query.setString(2, professor.getNome());
            query.setString(3, professor.getTelefone());
            query.setInt(4, professor.getIdade());
            query.setInt(7, professor.getIdCliente());

            query.execute();
            query.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deletaProfessor(ProfessorDTO professor) {
        sql = "DELETE FROM professor WHERE idProfessor = ?";

        try{
            PreparedStatement query = conn.prepareStatement(sql);
            query.setInt(1, professor.getIdProfessor());
            query.execute();
            query.close();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<ProfessorDTO> listaTodos() {
        List<ProfessorDTO> professores = new ArrayList<ProfessorDTO>();
        ProfessorDTO professor = new ProfessorDTO();

        sql = "SELECT * FROM professor";

        try{
            query = conn.createStatement();
            ResultSet rs = query.executeQuery(sql);

            while(rs.next()){
                professor.setIdProfessor(rs.getInt("idProfessor"));
                professor.setNome(rs.getString("nome"));
                professor.setTelefone(rs.getString("telefone"));

                professor.setIdade(rs.getInt("idade"));


                professores.add(professor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NomeProfessorMenor5CaracteresException e) {
            e.printStackTrace();
        }
        return professores;
    }
}