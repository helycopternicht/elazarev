package ru.elazarev.model.dao;

import ru.elazarev.model.Comment;
import ru.elazarev.model.File;
import ru.elazarev.model.RequestStatus;
import ru.elazarev.model.UserRequest;
import ru.elazarev.model.database.ConnectionManager;
import ru.elazarev.model.database.JDBCUtils;
import ru.elazarev.model.exceptions.NoSuchElementException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.time.Instant;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;

/**
 * Data access object class for requests table.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 22.12.17
 */
public class UserRequestDao implements Dao<UserRequest> {
    /**
     * Request text for getting request by id.
     */
    private static final String GET_BY_ID = "SELECT * FROM requests WHERE id = ?;";
    /**
     * Request text for getting request by title.
     */
    private static final String GET_BY_TITLE = "SELECT * FROM requests WHERE title LIKE ?;";
    /**
     * Request text for getting all requests.
     */
    private static final String GET_ALL = "SELECT * FROM requests;";
    /**
     * Request text for create request.
     */
    private static final String CREATE = "INSERT INTO requests(title, description, date, author_id, category_id, status) VALUES (?,?,?,?,?,?);";
    /**
     * Request text for update request.
     */
    private static final String UPDATE = "UPDATE requests SET title=?, description=?, date=?, author_id=?, category_id = ?, status = ?;";
    /**
     * Request text for delete request.
     */
    private static final String DELETE = "DELETE FROM requests WHERE id=?;";
    /**
     * Request text for get files of request.
     */
    private static final String GET_FILES = "SELECT FROM requests_files WHERE request_id = ?";
    /**
     * Request text for get comments of request.
     */
    private static final String GET_COMMENTS = "SELECT * FROM comments WHERE request_id = ?";

    /**
     * Creates request in db.
     * @param e model of request to create.
     * @return updated request model with real data from db.
     */
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

    /**
     * Returns request by id.
     * @param id id of request.
     * @return request model.
     * @throws NoSuchElementException if no such request in db.
     */
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
                return ur;
            }

        } catch (SQLException e) {
            /*there should be logging*/
        } finally {
            JDBCUtils.closeAllQuietly(rs, pst, conn);
        }
        throw  new NoSuchElementException("No suck element with id = " + id);
    }

    /**
     * Updates request in bd.
     * @param e entity model.
     * @throws NoSuchElementException if no such request in bd.
     */
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
            /*there should be logging*/
        } finally {
            JDBCUtils.closeAllQuietly(pst, conn);
        }
    }

    /**
     * Deletes request from bd.
     * @param e entity model.
     * @return true if success.
     * @throws NoSuchElementException if no such request in bd.
     */
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
            /*there should be logging*/
        } finally {
            JDBCUtils.closeAllQuietly(pst, conn);
        }
        return false;
    }

    /**
     * Creates request model from result set.
     * @param rs results set.
     * @return request model.
     * @throws SQLException if something go wrong.
     * @throws NoSuchElementException if no such user or category have in db.
     */
    private UserRequest requestFromResultSet(ResultSet rs) throws SQLException, NoSuchElementException {
        UserRequest ur = new UserRequest(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getTimestamp("date").toLocalDateTime(),
                new UserDao().getById(rs.getInt("author_id")),
                new RequestCategoryDao().getById(rs.getInt("category_id")),
                RequestStatus.valueOf(rs.getString("status").toUpperCase()));

        ur.setFiles(this.getFiles(ur.getId()));
        ur.setComments(this.getComments(ur.getId()));
        return  ur;
    }

    /**
     * Returns list of all requests in db.
     * @return list of requests models.
     */
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
                list.add(requestFromResultSet(rs));
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

    /**
     * Returns all files of request with specified id.
     * @param id id of request.
     * @return list of file models.
     */
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
            /*there should be logging*/
        } catch (NoSuchElementException e) {
            /*there should be logging*/
        } finally {
            JDBCUtils.closeAllQuietly(rs, st, conn);
        }
        return list;
    }

    /**
     * Returns all comments of request with specified id.
     * @param id id of request.
     * @return list of comment models.
     */
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
            //*there should be logging*/
        } catch (NoSuchElementException e) {
            /*there should be logging*/
        } finally {
            JDBCUtils.closeAllQuietly(rs, st, conn);
        }
        return list;
    }

    /**
     * Returns list of requests with title like specified title.
     * @param title title to find.
     * @return list of requests or empty list.
     */
    public List<UserRequest> getByTitle(String title) {
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<UserRequest> result = new ArrayList<>();
        try {
            pst = conn.prepareStatement(GET_BY_TITLE);
            pst.setString(1, "%" + title + "%");

            UserDao udao = new UserDao();
            RequestCategoryDao cdao = new RequestCategoryDao();

            rs = pst.executeQuery();
            while (rs.next()) {
                result.add(requestFromResultSet(rs));
            }

        } catch (SQLException e) {
            /*there should be logging*/
        } catch (NoSuchElementException e) {
            /*there should be logging*/
            // in this case we dont get this exception
        } finally {
            JDBCUtils.closeAllQuietly(rs, pst, conn);
        }
        return result;
    }
}
