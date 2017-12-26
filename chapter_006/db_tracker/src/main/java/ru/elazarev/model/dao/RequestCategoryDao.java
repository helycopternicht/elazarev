package ru.elazarev.model.dao;

import ru.elazarev.model.RequestCategory;
import ru.elazarev.model.database.ConnectionManager;
import ru.elazarev.model.database.JDBCUtils;
import ru.elazarev.model.exceptions.NoSuchElementException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 21.12.17
 */
public class RequestCategoryDao implements Dao<RequestCategory> {

    private static final String GET_BY_ID = "SELECT * FROM request_categorys WHERE id = ?;";
    private static final String GET_ALL = "SELECT * FROM request_categorys;";
    private static final String CREATE = "INSERT INTO request_categorys(name) VALUES (?);";
    private static final String UPDATE = "UPDATE request_categorys SET name=? WHERE id=?;";
    private static final String DELETE = "DELETE FROM request_categorys WHERE id=?;";

    @Override
    public RequestCategory create(RequestCategory e) {
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, e.getName());
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
    public RequestCategory getById(int id) throws NoSuchElementException {
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement(GET_BY_ID);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                return new RequestCategory(rs.getInt("id"), rs.getString("name"));
            }

        } catch (SQLException e) {
            throw new NoSuchElementException("");
        } finally {
            JDBCUtils.closeAllQuietly(rs, pst, conn);
        }
        throw new NoSuchElementException("");
    }

    @Override
    public void update(RequestCategory e) throws NoSuchElementException {
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(UPDATE);
            pst.setString(1, e.getName());
            pst.setInt(2, e.getId());
            pst.executeUpdate();

        } catch (SQLException e1) {
            throw new NoSuchElementException("");
        } finally {
            JDBCUtils.closeAllQuietly(pst, conn);
        }
    }

    @Override
    public boolean delete(RequestCategory e) throws NoSuchElementException {
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
    public List<RequestCategory> getAll() {
        Connection conn = ConnectionManager.getConnection();
        Statement st = null;
        ResultSet rs = null;
        List<RequestCategory> list = new ArrayList<>();
        try {
            st = conn.createStatement();
            rs = st.executeQuery(GET_ALL);
            while (rs.next()) {
                list.add(new RequestCategory(rs.getInt("id"), rs.getString("name")));
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
