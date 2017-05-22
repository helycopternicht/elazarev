package ru.elazarev.testtask;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import java.util.Iterator;
import java.util.List;

/**
 * Test for OnlineBank class.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 22.05.17
 */
public class OnlineBankTest {

    /**
     * If users added but account not added than list of accounts ie empty.
     */
    @Test
    public void whenAddUserThenListOfAccountsIsEmpty() {
        OnlineBank bank = new OnlineBank();
        bank.addUser(new User("Eugene", "AN1231238"));
        bank.addUser(new User("Ekaterina", "AK0989734"));

        List<Account> accounts =  bank.getUserAccounts(new User("Eugene", "AN1231238"));
        assertEquals(0, accounts.size());
    }

    /**
     * If user not added accounts list returns null.
     */
    @Test
    public void whenGetUserAccWithNotFoundUserThenAccListIsNull() {
        OnlineBank bank = new OnlineBank();
        bank.addUser(new User("Eugene", "AN1231238"));
        bank.addUser(new User("Ekaterina", "AK0989734"));

        assertNull(bank.getUserAccounts(new User("Ivan", "AN1239453")));
    }

    /**
     * getUserAccounts returns null if user is deleted.
     */
    @Test
    public void whenDeleteUserThenListOfAccIsNull() {
        OnlineBank bank = new OnlineBank();
        bank.addUser(new User("Eugene", "AN1231238"));
        bank.addUser(new User("Ekaterina", "AK0989734"));
        bank.deleteUser(new User("Eugene", "AN1231238"));
        assertNull(bank.getUserAccounts(new User("Eugene", "AN1231238")));
    }

    /**
     * addAccountToUser method test.
     */
    @Test
    public void whenAddAccountToUserThenListOfAccContainsIt() {
        OnlineBank bank = new OnlineBank();
        bank.addUser(new User("Eugene", "AN1231238"));
        bank.addUser(new User("Ekaterina", "AK0989734"));

        Account someAcc = new Account(1234567890);
        someAcc.setValue(300_000d);

        Account anotherAcc = new Account(1312336671);
        anotherAcc.setValue(1200d);

        Account ekatAcc = new Account(1823601236);
        anotherAcc.setValue(120_000d);

        bank.addAccountToUser(new User("Eugene", "AN1231238"), someAcc);
        bank.addAccountToUser(new User("Eugene", "AN1231238"), anotherAcc);
        bank.addAccountToUser(new User("Ekaterina", "AK0989734"), ekatAcc);


        assertEquals(2, bank.getUserAccounts(new User("Eugene", "AN1231238")).size());
        assertEquals(1, bank.getUserAccounts(new User("Ekaterina", "AK0989734")).size());

        assertTrue(bank.getUserAccounts(new User("Eugene", "AN1231238")).contains(someAcc));
        assertTrue(bank.getUserAccounts(new User("Eugene", "AN1231238")).contains(anotherAcc));
        assertFalse(bank.getUserAccounts(new User("Eugene", "AN1231238")).contains(ekatAcc));
    }

    /**
     * deleteAccountFromUser method test.
     */
    @Test
    public void whenDeleteAccountFromUserThenListOfAccNotContainsIt() {
        OnlineBank bank = new OnlineBank();
        bank.addUser(new User("Eugene", "AN1231238"));
        bank.addUser(new User("Ekaterina", "AK0989734"));

        Account someAcc = new Account(1234567890);
        someAcc.setValue(300_000d);

        Account anotherAcc = new Account(1312336671);
        anotherAcc.setValue(1200d);

        Account ekatAcc = new Account(1823601236);
        anotherAcc.setValue(120_000d);

        bank.addAccountToUser(new User("Eugene", "AN1231238"), someAcc);
        bank.addAccountToUser(new User("Eugene", "AN1231238"), anotherAcc);
        bank.addAccountToUser(new User("Ekaterina", "AK0989734"), ekatAcc);

        bank.deleteAccountFromUser(new User("Ekaterina", "AK0989734"), someAcc);
        bank.deleteAccountFromUser(new User("Eugene", "AN1231238"), someAcc);

        assertEquals(1, bank.getUserAccounts(new User("Eugene", "AN1231238")).size());
        assertEquals(1, bank.getUserAccounts(new User("Ekaterina", "AK0989734")).size());

        assertFalse(bank.getUserAccounts(new User("Eugene", "AN1231238")).contains(someAcc));
        assertTrue(bank.getUserAccounts(new User("Eugene", "AN1231238")).contains(anotherAcc));
    }

    /**
     * getUserAccounts method test.
     */
    @Test
    public void getUserAccountsTest() {
        OnlineBank bank = new OnlineBank();
        bank.addUser(new User("Eugene", "AN1231238"));
        bank.addUser(new User("Ekaterina", "AK0989734"));

        Account someAcc = new Account(1234567890);
        someAcc.setValue(300_000d);

        Account anotherAcc = new Account(1312336671);
        anotherAcc.setValue(1200d);

        bank.addAccountToUser(new User("Eugene", "AN1231238"), someAcc);
        bank.addAccountToUser(new User("Eugene", "AN1231238"), anotherAcc);

        assertEquals(2, bank.getUserAccounts(new User("Eugene", "AN1231238")).size());
        assertEquals(0, bank.getUserAccounts(new User("Ekaterina", "AK0989734")).size());


        Iterator<Account> iterator = bank.getUserAccounts(new User("Eugene", "AN1231238")).iterator();
        assertEquals(someAcc, iterator.next());
        assertEquals(anotherAcc, iterator.next());

        assertNull(bank.getUserAccounts(new User("Petja", "AM912874019241")));
    }

    /**
     * transferMpney method test.
     */
    @Test
    public void transferMoneyMethodTest() {
        OnlineBank bank = new OnlineBank();
        bank.addUser(new User("Eugene", "AN1231238"));
        bank.addUser(new User("Ekaterina", "AK0989734"));

        Account eugAcc = new Account(1234567890);
        eugAcc.setValue(100_000d);

        Account ekaAcc = new Account(1312336671);

        bank.addAccountToUser(new User("Eugene", "AN1231238"), eugAcc);
        bank.addAccountToUser(new User("Ekaterina", "AK0989734"), ekaAcc);

        assertFalse(bank.transferMoney(new User("John", "An123123123"), eugAcc, new User("Ekaterina", "AK0989734"), ekaAcc, 1000d));
        assertTrue(bank.transferMoney(new User("Eugene", "AN1231238"), eugAcc, new User("Ekaterina", "AK0989734"), ekaAcc, 1000d));
        assertFalse(bank.transferMoney(new User("Eugene", "AN1231238"), eugAcc, new User("Ekaterina", "AK0989734"), ekaAcc, 100_001d));
        assertTrue(eugAcc.getValue() == 100_000d - 1000d);
        assertTrue(ekaAcc.getValue() == 1000d);
    }
}
