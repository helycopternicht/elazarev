package ru.elazarev.model.dao;

import ru.elazarev.model.Comment;
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
 * Data access object for Comment table.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 22.12.17
 */
public class CommentDao implements Dao<Comment> {
    /**
     * Request for getting comment by id.
     */
    private static final String GET_BY_ID = "SELECT * FROM comments WHERE id = ?;";
    /**
     * Request for getting all comments.
     */
    private static final String GET_ALL = "SELECT * FROM comments;";
    /**
     * Request for creating new comment.
     */
    private static final String CREATE = "INSERT INTO comments(author_id, comment, request_id) VALUES (?,?,?);";
    /**
     * Request for deleting comment.
     */
    private static final String DELETE = "DELETE FROM comments WHERE id = ?;";

    /**
     * Creates comment in bd.
     * @param e Comment
     * @return updated comment with real id.
     */
    @Override
    public Comment create(Comment e) {
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, e.getAuthor().getId());
            pst.setString(2, e.getComment());
            pst.setInt(3, e.getRequestId());
            pst.executeUpdate();

            rs = pst.getGeneratedKeys();
            if (rs.next()) {
                e.setId(rs.getInt("id"));
                e.setCreateDate(rs.getTimestamp("create_date").toLocalDateTime());
            }

        } catch (SQLException e1) {
            /*there should be logging*/
        } finally {
            JDBCUtils.closeAllQuietly(rs, pst, conn);
        }
        return e;
    }

    /**
     * Returns comment by ist id.
     * @param id id of comment.
     * @return comment
     * @throws NoSuchElementException if no comment with such id in db.
     */
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
                        rs.getTimestamp("create_date").toLocalDateTime(),
                        rs.getInt("request_id"));
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
     * @param e comment.
     * @throws NoSuchElementException no throws.
     */
    @Override
    public void update(Comment e) throws NoSuchElementException {
        throw new UnsupportedOperationException();
    }

    /**
     * Deletes comment from db if exists.
     * @param e comment to delete.
     * @return true if success.
     * @throws NoSuchElementException if no comment with such id in db.
     */
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

    /**
     * Returns list of all comments in db.
     * @return list of comments.
     */
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
                        rs.getTimestamp("create_date").toLocalDateTime(),
                        rs.getInt("request_id")));
            }
        } catch (SQLException e) {
            /*there should be logging*/
        } catch (NoSuchElementException e) {
            /*there should be logging*/
        } finally {
            JDBCUtils.closeAllQuietly(rs, st, conn);
        }

        return list;
    }
}
