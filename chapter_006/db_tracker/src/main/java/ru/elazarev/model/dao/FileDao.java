package ru.elazarev.model.dao;

import ru.elazarev.model.File;
import ru.elazarev.model.database.ConnectionManager;
import ru.elazarev.model.database.JDBCUtils;
import ru.elazarev.model.exceptions.NoSuchElementException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data access object class for File table.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 22.12.17
 */
public class FileDao implements Dao<File> {
    /**
     * Request text for getting file by id.
     */
    private static final String GET_BY_ID = "SELECT * FROM files WHERE id = ?;";
    /**
     * Request text for getting all files.
     */
    private static final String GET_ALL = "SELECT * FROM files;";
    /**
     * Request text for create file.
     */
    private static final String CREATE = "INSERT INTO files(url) VALUES (?);";
    /**
     * Request text for delete file.
     */
    private static final String DELETE = "DELETE FROM files WHERE id = ?;";

    /**
     * Creates file in bd.
     * @param e file model.
     * @return updated file model with real id.
     */
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
            /*there should be logging*/
        } finally {
            JDBCUtils.closeAllQuietly(rs, pst, conn);
        }
        return e;
    }

    /**
     * Returns file by id.
     * @param id id of file in bd.
     * @return file model.
     * @throws NoSuchElementException if no file with such id in bd.
     */
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

    /**
     * Unsupported operation.
     * @param e file model.
     * @throws NoSuchElementException no throws.
     */
    @Override
    public void update(File e) throws NoSuchElementException {
        throw new UnsupportedOperationException();
    }

    /**
     * Deletes file from bd.
     * @param e file model.
     * @return true if success.
     * @throws NoSuchElementException if no file with such id in bd.
     */
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

    /**
     * Returns all files in bd.
     * @return list of file models.
     */
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
            /*there should be logging*/
        } finally {
            JDBCUtils.closeAllQuietly(rs, st, conn);
        }

        return list;
    }
}
