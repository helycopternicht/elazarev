package ru.elazarev.generic;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;


/**
 * Test for Store implementations.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 29.05.17
 */
public class StoreTest {

    /**
     * If element added in store that is can be updated.
     */
    @Test
    public void addUpdateUserTest() {
        UserStore<User> usStore = new UserStore<>();
        usStore.add(new User("1"));
        usStore.add(new User("2"));
        usStore.add(new User("3"));

        assertThat(usStore.update(new User("1"), new User("999")), is(true));
        assertThat(usStore.update(new User("3"), new User("888")), is(true));
        assertThat(usStore.update(new User("777"), new User("888")), is(false));
    }

    /**
     * If element in store that it can be deleted. And vice versa.
     */
    @Test
    public void deleteUserTest() {
        UserStore<User> usStore = new UserStore<>();
        usStore.add(new User("1"));
        usStore.add(new User("2"));
        usStore.add(new User("3"));

        assertThat(usStore.delete(new User("1")), is(true));
        assertThat(usStore.delete(new User("3")), is(true));
        assertThat(usStore.delete(new User("777")), is(false));
    }

    /**
     * If element added in store that is can be updated.
     */
    @Test
    public void addUpdateRoleTest() {
        RoleStore<Role> roleStore = new RoleStore<>();
        roleStore.add(new Role("1"));
        roleStore.add(new Role("2"));
        roleStore.add(new Role("3"));

        assertThat(roleStore.update(new Role("1"), new Role("999")), is(true));
        assertThat(roleStore.update(new Role("3"), new Role("888")), is(true));
        assertThat(roleStore.update(new Role("777"), new Role("888")), is(false));
    }

    /**
     * If element in store that it can be deleted. And vice versa.
     */
    @Test
    public void deleteRoleTest() {
        RoleStore<Role> roleStore = new RoleStore<>();
        roleStore.add(new Role("1"));
        roleStore.add(new Role("2"));
        roleStore.add(new Role("3"));

        assertThat(roleStore.delete(new Role("1")), is(true));
        assertThat(roleStore.delete(new Role("3")), is(true));
        assertThat(roleStore.delete(new Role("777")), is(false));
    }
}
