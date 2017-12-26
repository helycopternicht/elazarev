package ru.elazarev.model.dao;

import ru.elazarev.model.Comment;
import ru.elazarev.model.File;
import ru.elazarev.model.RequestStatus;
import ru.elazarev.model.UserRequest;
import ru.elazarev.model.database.ConnectionManager;
import ru.elazarev.model.database.JDBCUtils;
import ru.elazarev.model.exceptions.NoSuchElementException;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 22.12.17
 */
public class UserRequestDao implements Dao<UserRequest> {

    private static final String GET_BY_ID = "SELECT * FROM requests WHERE id = ?;";
    private static final String GET_ALL = "SELECT * FROM requests;";
    private static final String CREATE = "INSERT INTO requests(title, description, date, author_id, category_id, status) VALUES (?,?,?,?,?,?);";
    private static final String UPDATE = "UPDATE requests SET title=?, description=?, date=?, author_id=?, category_id = ?, status = ?;";
    private static final String DELETE = "DELETE FROM requests WHERE id=?;";
    private static final String GET_FILES = "SELECT FROM requests_files WHERE request_id = ?";
    private static final String GET_COMMENTS = "SELECT FROM comments WHERE request_id = ?";

    @Override
    public UserRequest create(UserRequest e) {

        Connection conn = ConnectionManager.getConnection();
        PreparedStatement st = null;
        ResultSet res = null;
        try {
            st = conn.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, e.getTitle());
            st.setString(2, e.getDescription());
            st.setTimestamp(3, Timestamp.from(Instant.now()));
            st.setInt(4, e.getAuthor().getId());
            st.setInt(5, e.getCategory().getId());
            st.setString(6, e.getStatus().toString());
            st.executeUpdate();

            res = st.getGeneratedKeys();
            if (res.next()) {
                e.setId(res.getInt("id"));
            }

        } catch (SQLException e1) {
            e1.printStackTrace();

        } finally {
            JDBCUtils.closeAllQuietly(st, conn);
        }
        return e;
    }

    @Override
    public UserRequest getById(int id) throws NoSuchElementException {
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement(GET_BY_ID);
            pst.setInt(1, id);

            UserDao udao = new UserDao();
            RequestCategoryDao cdao = new RequestCategoryDao();

            rs = pst.executeQuery();
            if (rs.next()) {
                UserRequest ur = new UserRequest(
                                rs.getInt("id"),
                                rs.getString("title"),
                                rs.getString("description"),
                                rs.getTimestamp("date").toLocalDateTime(),
                                udao.getById(rs.getInt("author_id")),
                                cdao.getById(rs.getInt("category_id")),
                                RequestStatus.valueOf(rs.getString("status").toUpperCase()));

                ur.setFiles(this.getFiles(ur.getId()));
                ur.setComments(this.getComments(ur.getId()));

            }

        } catch (SQLException e) {
            // TODO: переделать через эксепшн
            e.printStackTrace();
        } finally {
            JDBCUtils.closeAllQuietly(rs, pst, conn);
        }
        throw  new NoSuchElementException("No suck element with id = " + id);
    }

    @Override
    public void update(UserRequest e) throws NoSuchElementException {
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement pst = null;

        try {
            pst = conn.prepareStatement(UPDATE);
            pst.setString(1, e.getTitle());
            pst.setString(2, e.getDescription());
            pst.setTimestamp(3, Timestamp.valueOf(e.getDate()));
            pst.setInt(4, e.getAuthor().getId());
            pst.setInt(5, e.getCategory().getId());
            pst.setString(6, e.getStatus().toString());
            if (pst.executeUpdate() == 0) {
                throw new NoSuchElementException("No element with ID " + e.getId());
            }

        } catch (SQLException e1) {
            // TODO: переделать через эксепшн
            e1.printStackTrace();
        } finally {
            JDBCUtils.closeAllQuietly(pst, conn);
        }
    }

    @Override
    public boolean delete(UserRequest e) throws NoSuchElementException {
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
            // TODO: переделать через эксепшн
            e1.printStackTrace();
        } finally {
            JDBCUtils.closeAllQuietly(pst, conn);
        }
        return false;
    }

    @Override
    public List<UserRequest> getAll() {

        Connection conn = ConnectionManager.getConnection();
        Statement st = null;
        ResultSet rs = null;
        List<UserRequest> list = new ArrayList<>();
        try {
            st = conn.createStatement();
            rs = st.executeQuery(GET_ALL);
            UserDao u = new UserDao();
            RequestCategoryDao rc = new RequestCategoryDao();

            while (rs.next()) {
                UserRequest ur = new UserRequest(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getTimestamp("date").toLocalDateTime(),
                        u.getById(rs.getInt("author_id")),
                        rc.getById(rs.getInt("category_id")),
                        RequestStatus.valueOf(rs.getString("status").toUpperCase()));

                ur.setFiles(this.getFiles(ur.getId()));
                ur.setComments(this.getComments(ur.getId()));

            }

        } catch (SQLException e) {
            // TODO: переделать через эксепшн
            e.printStackTrace();
        } catch (NoSuchElementException e) {
            // TODO: переделать через эксепшн
            e.printStackTrace();
        } finally {
            JDBCUtils.closeAllQuietly(rs, st, conn);
        }
        return list;
    }

    public List<File> getFiles(int id) {
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        List<File> list = new ArrayList<>();
        try {
            st = conn.prepareStatement(GET_FILES);
            st.setInt(1, id);

            FileDao fdao = new FileDao();
            rs = st.executeQuery();
            while (rs.next()) {
                list.add(fdao.getById(rs.getInt("file_id")));
            }

        } catch (SQLException e) {
            // TODO: переделать через эксепшн
            e.printStackTrace();
        } catch (NoSuchElementException e) {
            // TODO: переделать через эксепшн
            e.printStackTrace();
        } finally {
            JDBCUtils.closeAllQuietly(rs, st, conn);
        }
        return list;
    }

    public List<Comment> getComments(int id) {
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Comment> list = new ArrayList<>();
        try {
            st = conn.prepareStatement(GET_COMMENTS);
            st.setInt(1, id);
            CommentDao cdao = new CommentDao();
            rs = st.executeQuery();
            while (rs.next()) {
                list.add(cdao.getById(rs.getInt("request_id")));
            }

        } catch (SQLException e) {
            // TODO: переделать через эксепшн
            e.printStackTrace();
        } catch (NoSuchElementException e) {
            // TODO: переделать через эксепшн
            e.printStackTrace();
        } finally {
            JDBCUtils.closeAllQuietly(rs, st, conn);
        }
        return list;
    }
}
