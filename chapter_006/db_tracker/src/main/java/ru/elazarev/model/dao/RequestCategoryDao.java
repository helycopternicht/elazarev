package ru.elazarev.model.dao;

import ru.elazarev.model.RequestCategory;
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
 * Data access object class for request_category table.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 21.12.17
 */
public class RequestCategoryDao implements Dao<RequestCategory> {
    /**
     * Request text for getting category by id.
     */
    private static final String GET_BY_ID = "SELECT * FROM request_categorys WHERE id = ?;";
    /**
     * Request text for getting all categorys.
     */
    private static final String GET_ALL = "SELECT * FROM request_categorys;";
    /**
     * Request text for create category.
     */
    private static final String CREATE = "INSERT INTO request_categorys(name) VALUES (?);";
    /**
     * Request text for update category.
     */
    private static final String UPDATE = "UPDATE request_categorys SET name=? WHERE id=?;";
    /**
     * Request text for delete category.
     */
    private static final String DELETE_ITEM = "DELETE FROM request_categorys WHERE id=?;";
    /**
     * Request text for getting category by name.
     */
    private static final String GET_BY_NAME = "SELECT * FROM request_categorys WHERE name = ?;";

    /**
     * Creates category.
     * @param e category model.
     * @return updated category model with real id.
     */
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
            /*there should be logging*/
        } finally {
            JDBCUtils.closeAllQuietly(rs, pst, conn);
        }
        return e;
    }

    /**
     * Returns category by id if it exists.
     * @param id id of category.
     * @return category model.
     * @throws NoSuchElementException if no category with such id in bd.
     */
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

    /**
     * Returns category by name.
     * @param name name of category.
     * @return category model.
     * @throws NoSuchElementException if no category with such id in bd.
     */
    public RequestCategory getByName(String name) throws NoSuchElementException {
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement(GET_BY_NAME);
            pst.setString(1, name);
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

    /**
     * Update Category in db.
     * @param e category model to update.
     * @throws NoSuchElementException if no category with such id in bd.
     */
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

    /**
     * Deletes category from db.
     * @param e category model.
     * @return true if success.
     * @throws NoSuchElementException if no category with such id in bd.
     */
    @Override
    public boolean delete(RequestCategory e) throws NoSuchElementException {
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement pst = null;

        try {
            pst = conn.prepareStatement(DELETE_ITEM);
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
     * Returns list of all category model having in db.
     * @return list of category models.
     */
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
            /*there should be logging*/
        } finally {
            JDBCUtils.closeAllQuietly(rs, st, conn);
        }

        return list;
    }
}
