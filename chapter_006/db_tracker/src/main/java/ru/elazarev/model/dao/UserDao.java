package ru.elazarev.model.dao;

import ru.elazarev.model.User;
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
 * Data access object for users table.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 21.12.17
 */
public class UserDao implements Dao<User> {
    /**
     * Request text for getting user by id.
     */
    private static final String GET_BY_ID = "SELECT * FROM USERS WHERE id = ?;";
    /**
     * Request text for getting all users.
     */
    private static final String GET_ALL = "SELECT * FROM USERS;";
    /**
     * Request text for create user.
     */
    private static final String CREATE = "INSERT INTO USERS(login, password, is_admin) VALUES (?,?,?);";
    /**
     * Request text for update user.
     */
    private static final String UPDATE = "UPDATE USERS SET login=?, password=?, is_admin=? WHERE id=?;";
    /**
     * Request text for delete user.
     */
    private static final String DELETE = "DELETE FROM USERS WHERE id=?;";
    /**
     * Request text for getting user by login.
     */
    private static final String GET_BY_LOGIN = "SELECT * FROM users where login = ?";

    /**
     * Creates user in db by model.
     * @param e users model.
     * @return updated user with real id and created date.
     */
    @Override
    public User create(User e) {
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, e.getLogin());
            st.setString(2, e.getPassword());
            st.setBoolean(3, e.isAdmin());
            st.executeUpdate();

            ResultSet set = st.getGeneratedKeys();
            if (set.next()) {
                e.setId(set.getInt("id"));
                e.setCreateDate(set.getTimestamp("create_date").toLocalDateTime());
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        } finally {
            JDBCUtils.closeAllQuietly(st, connection);
        }
        return e;
    }

    /**
     * Returns user model by login if it exists.
     * @param login users login.
     * @return users model.
     * @throws NoSuchElementException if no users with such login.
     */
    public User getByLogin(String login) throws NoSuchElementException {
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(GET_BY_LOGIN);
            st.setString(1, login);
            ResultSet r = st.executeQuery();
            if (r.next()) {
                return new User(r.getInt("id"),
                        r.getString("login"),
                        r.getString("password"),
                        r.getTimestamp("create_date").toLocalDateTime(),
                        r.getBoolean("is_admin"));
            }

        } catch (SQLException e) {
            /*there should be logging*/
        } finally {
            JDBCUtils.closeAllQuietly(st, connection);
        }

        throw new NoSuchElementException("User with login " + login + " is not found");
    }

    /**
     * Returns user model by id if it exists.
     * @param id user id.
     * @return users model.
     * @throws NoSuchElementException if no users with such id.
     */
    @Override
    public User getById(int id) throws NoSuchElementException {

        Connection connection = ConnectionManager.getConnection();
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(GET_BY_ID);
            st.setInt(1, id);
            ResultSet r = st.executeQuery();
            if (r.next()) {
                return new User(r.getInt("id"),
                        r.getString("login"),
                        r.getString("password"),
                        r.getTimestamp("create_date").toLocalDateTime(),
                        r.getBoolean("is_admin"));
            }

        } catch (SQLException e) {
            /*there should be logging*/
        } finally {
            JDBCUtils.closeAllQuietly(st, connection);
        }

        throw new NoSuchElementException("User with id " + id + " is not found");
    }

    /**
     * Updates user in db.
     * @param e user model.
     * @throws NoSuchElementException if no such users in db
     */
    @Override
    public void update(User e) throws NoSuchElementException {
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(UPDATE);
            st.setString(1, e.getLogin());
            st.setString(2, e.getPassword());
            st.setBoolean(3, e.isAdmin());
            st.setInt(4, e.getId());
            st.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new NoSuchElementException("User with id " + e.getId() + " is not found");
        } finally {
            JDBCUtils.closeAllQuietly(st, connection);
        }
    }

    /**
     * Deletes user from db.
     * @param e user model.
     * @return true if success.
     */
    @Override
    public boolean delete(User e) {
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(DELETE);
            st.setInt(1, e.getId());
            return st.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            JDBCUtils.closeAllQuietly(st, connection);
        }
    }

    /**
     * Returns list of all users in db.
     * @return list of users model or empty list.
     */
    @Override
    public List<User> getAll() {

        Connection connection = ConnectionManager.getConnection();
        PreparedStatement st = null;
        List<User> list = new ArrayList<>();

        try {
            st = connection.prepareStatement(GET_ALL);
            ResultSet r = st.executeQuery();
            while (r.next()) {
                list.add(new User(r.getInt("id"),
                        r.getString("login"),
                        r.getString("password"),
                        r.getTimestamp("create_date").toLocalDateTime(),
                        r.getBoolean("is_admin")));

            }

        } catch (SQLException e) {
            /*there should be logging*/
        } finally {
            JDBCUtils.closeAllQuietly(st, connection);
        }
        return list;
    }
}
