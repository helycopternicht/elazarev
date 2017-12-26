package ru.elazarev.model.dao;

import ru.elazarev.model.Comment;
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
public class CommentDao implements Dao<Comment> {

    private static final String GET_BY_ID = "SELECT * FROM comments WHERE id = ?;";
    private static final String GET_ALL = "SELECT * FROM comments;";
    private static final String CREATE = "INSERT INTO comments(author_id, comment) VALUES (?,?);";
    private static final String DELETE = "DELETE FROM comments WHERE id = ?;";

    @Override
    public Comment create(Comment e) {
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, e.getAuthor().getId());
            pst.setString(2, e.getComment());
            pst.executeUpdate();

            rs = pst.getGeneratedKeys();
            if (rs.next()) {
                e.setId(rs.getInt("id"));
                e.setCreateDate(rs.getTimestamp("create_date").toLocalDateTime());
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
    public Comment getById(int id) throws NoSuchElementException {
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            UserDao udao = new UserDao();

            pst = conn.prepareStatement(GET_BY_ID);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                return new Comment(
                        rs.getInt("id"),
                        udao.getById(rs.getInt("author_id")),
                        rs.getString("comment"),
                        rs.getTimestamp("create_date").toLocalDateTime());
            }

        } catch (SQLException e) {
            throw new NoSuchElementException("");
        } finally {
            JDBCUtils.closeAllQuietly(rs, pst, conn);
        }
        throw new NoSuchElementException("");
    }

    @Override
    public void update(Comment e) throws NoSuchElementException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(Comment e) throws NoSuchElementException {
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
    public List<Comment> getAll() {
        Connection conn = ConnectionManager.getConnection();
        Statement st = null;
        ResultSet rs = null;
        UserDao udao = new UserDao();
        List<Comment> list = new ArrayList<>();
        try {
            st = conn.createStatement();
            rs = st.executeQuery(GET_ALL);
            while (rs.next()) {
                list.add(new Comment(
                        rs.getInt("id"),
                        udao.getById(rs.getInt("author_id")),
                        rs.getString("comment"),
                        rs.getTimestamp("create_date").toLocalDateTime()));
            }
        } catch (SQLException e) {
            // TODO: make with exception
            e.printStackTrace();
        } catch (NoSuchElementException e) {
            // TODO: make with exception
            e.printStackTrace();
        } finally {
            JDBCUtils.closeAllQuietly(rs, st, conn);
        }

        return list;
    }
}
