package ru.elazarev.testtask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Online bank class. Used to manage Users and users accounts.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 22.05.17
 */
public class OnlineBank {
    /**
     * Users database.
     */
    private Map<User, List<Account>> db;

    /**
     * Default constructor.
     */
    public OnlineBank() {
        this.db = new HashMap<>();
    }

    /**
     * Method adds user to database.
     * @param user - user to add.
     */
    public void addUser(User user) {
        if (!this.db.containsKey(user)) {
            this.db.put(user, new ArrayList<>());
        }
    }

    /**
     * Deletes user from database.
     * @param user - user to delete
     */
    public void deleteUser(User user) {
        this.db.remove(user);
    }

    /**
     * Adds account to user in database if it exist.
     * @param user - who own account
     * @param account - account to add
     */
    public void addAccountToUser(User user, Account account) {
        List<Account> accounts = this.getUsrAccounts(user);
        if (accounts != null) {
            accounts.add(account);
        }
    }

    /**
     * Deletes account from user in database if user and account are exist.
     * @param user - whos account
     * @param account - account to delete
     */
    public void deleteAccountFromUser(User user, Account account) {
        List<Account> accounts = this.getUsrAccounts(user);
        if (accounts != null) {
            accounts.remove(account);
        }
    }

    /**
     * Returns list of users account or null if user is not exist in database.
     * @param user - user whos accounts need to find
     * @return list of user accounts
     */
    public List<Account> getUserAccounts(User user) {
        return this.getUsrAccounts(user);
    }

    /**
     * Transfer amount money from srcUser from srcAccount to stUser to dstAccount if users
     * and accounts are exists and if balance of srcAccount is enough.
     * @param srcUser - user who transfer money
     * @param srcAccount - account transfer money
     * @param dstUser - user receiving money
     * @param dstAccount - account receiving money
     * @param amount - amount of money
     * @return - true if operation is accomplished or false if is not.
     */
    public boolean transferMoney(User srcUser, Account srcAccount, User dstUser, Account dstAccount, double amount) {
        List<Account> srcAccList = this.getUsrAccounts(srcUser);
        if (srcAccList == null || !srcAccList.contains(srcAccount) || srcAccount.getValue() < amount) {
            return false;
        }

        List<Account> dstAccList = this.getUsrAccounts(dstUser);
        if (dstAccList == null || !dstAccList.contains(dstAccount)) {
            return false;
        }

        srcAccount.setValue(srcAccount.getValue() - amount);
        dstAccount.setValue(dstAccount.getValue() + amount);
        return true;
    }

    /**
     * Returns all accounts of user or null.
     * @param usr - user to find accounts
     * @return list of accounts or null
     */
    private List<Account> getUsrAccounts(User usr) {
        return this.db.get(usr);
    }
}
