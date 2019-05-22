package br.edu.utfpr.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDAO<T> {

    protected abstract String getStringSQLCreate();

    protected abstract void mappingCreate(PreparedStatement stmt, T entity) throws Exception;

    public boolean create(T entity) {
        try ( Connection conn = DriverManager.getConnection("jdbc:derby:memory:database")) {

            String sql = getStringSQLCreate();
            PreparedStatement stmt = conn.prepareStatement(sql);
            mappingCreate(stmt, entity);

            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0)
                return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    protected abstract String getStringSQLList();
    protected abstract T populateList(ResultSet result) throws Exception;

    public List<T> listAll() {

        List<T> list = new ArrayList<>();

        try ( Connection conn = DriverManager.getConnection("jdbc:derby:memory:database")) {

            String sql = getStringSQLList();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                list.add(populateList(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    protected abstract String getStringSQLDelete();

    public boolean delete(int id) {

        try (Connection conn = DriverManager.getConnection("jdbc:derby:memory:database")) {

            String sql = getStringSQLDelete();

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0)
                return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    protected abstract String getStringSQLUpdate();
    protected abstract void mappingUpdate(PreparedStatement stmt, T entity) throws Exception;

    public boolean update(T entity) {
        try ( Connection conn = DriverManager.getConnection("jdbc:derby:memory:database")) {

            String sql = getStringSQLUpdate();

            PreparedStatement stmt = conn.prepareStatement(sql);

            mappingUpdate(stmt, entity);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0)
                return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}