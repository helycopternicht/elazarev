package ru.elazarev.model.dao;

import ru.elazarev.model.File;
import ru.elazarev.model.database.ConnectionManager;
import ru.elazarev.model.database.JDBCUtils;
import ru.elazarev.model.exceptions.NoSuchElementException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 22.12.17
 */
public class FileDao implements Dao<File> {

    private static final String GET_BY_ID = "SELECT * FROM files WHERE id = ?;";
    private static final String GET_ALL = "SELECT * FROM files;";
    private static final String CREATE = "INSERT INTO files(url) VALUES (?);";
    private static final String DELETE = "DELETE FROM files WHERE id = ?;";

    @Override
    public File create(File e) {
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, e.getUrl());
            pst.executeUpdate();

            rs = pst.getGeneratedKeys();
            if (rs.next()) {
                e.setId(rs.getInt("id"));
            }

        } catch (SQLException e1) {
            // TODO: make with exception
            e1.printStackTrace();
        } finally {
            JDBCUtils.closeAllQuietly(rs, pst, conn);
        }
        return e;
    }

    @Override
    public File getById(int id) throws NoSuchElementException {
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            UserDao udao = new UserDao();

            pst = conn.prepareStatement(GET_BY_ID);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                return new File(
                        rs.getInt("id"),
                        rs.getString("url"));
            }

        } catch (SQLException e) {
            throw new NoSuchElementException("");
        } finally {
            JDBCUtils.closeAllQuietly(rs, pst, conn);
        }
        throw new NoSuchElementException("");
    }

    @Override
    public void update(File e) throws NoSuchElementException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(File e) throws NoSuchElementException {
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(DELETE);
            pst.setInt(1, e.getId());
            int count = pst.executeUpdate();

            if (count > 0) {
                return true;
            }
        } catch (SQLException e1) {
            return false;
        } finally {
            JDBCUtils.closeAllQuietly(pst, conn);
        }
        return false;
    }

    @Override
    public List<File> getAll() {
        Connection conn = ConnectionManager.getConnection();
        Statement st = null;
        ResultSet rs = null;
        UserDao udao = new UserDao();
        List<File> list = new ArrayList<>();
        try {
            st = conn.createStatement();
            rs = st.executeQuery(GET_ALL);
            while (rs.next()) {
                list.add(new File(
                        rs.getInt("id"),
                        rs.getString("url")));
            }
        } catch (SQLException e) {
            // TODO: make with exception
            e.printStackTrace();
        } finally {
            JDBCUtils.closeAllQuietly(rs, st, conn);
        }

        return list;
    }
}
