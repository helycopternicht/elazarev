package ru.elazarev.monitore.userstorage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to store and operate users.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 19.07.17
 */
@ThreadSafe
public class UserStorage {

    /**
     * Users map where key is users id and value is user.
     */
    @GuardedBy("this")
    private Map<Integer, User> users;

    /**
     * Default constructor.
     */
    public UserStorage() {
        users = new HashMap<>();
    }

    /**
     * Adds new user into storage.
     * @param id users id.
     * @param name users name
     * @param amountOfMoney users money
     */
    public synchronized void add(int id, String name, double amountOfMoney) {
        User newUser = new User(id, name, amountOfMoney);
        if (users.containsKey(id)) {
            update(newUser);
            return;
        }
        users.put(id, newUser);
    }

    /**
     * Remove user with id from storage.
     * @param id users id.
     */
    public synchronized void remove(int id) {
        users.remove(id);
    }

    /**
     * Update user in storage.
     * @param usr user to update
     */
    public synchronized void update(User usr) {
        users.put(usr.getId(), usr);
    }

    /**
     * Returns user by its id.
     * @param id id to find user
     * @return user or null
     */
    public synchronized User getUser(int id) {
        return users.get(id);
    }

    /**
     * Transfers money from user with sendersId to user with receiversId.
     * @param sendersId from user id
     * @param receiversId to user id
     * @param amount amount of money to transfer
     * @return true if transfer success and false else.
     */
    public synchronized boolean transfer(int sendersId, int receiversId, double amount) {
        if (!users.containsKey(sendersId) || !users.containsKey(receiversId)) {
            return false;
        }

        User fromUser = users.get(sendersId);
        User toUser = users.get(receiversId);
        if (fromUser.getMoney() < amount) {
            return false;
        }

        fromUser.setMoney(fromUser.getMoney() - amount);
        toUser.setMoney(toUser.getMoney() + amount);
        return true;
    }
}
