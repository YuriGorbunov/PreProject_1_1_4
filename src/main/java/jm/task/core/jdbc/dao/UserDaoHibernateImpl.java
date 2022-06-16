package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.SessionUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.List;

public class UserDaoHibernateImpl extends SessionUtil implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        openTransactionSession();
        Session session = getSession();
        session.createSQLQuery("CREATE TABLE IF NOT EXISTS`db_users`.`users` (" +
                "`id` INT NOT NULL AUTO_INCREMENT, " +
                "`name` VARCHAR(45) NOT NULL, " +
                "`lastName` VARCHAR(45) NOT NULL, " +
                "`age` INT(3) NOT NULL, PRIMARY KEY (`id`), " +
                "UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);").executeUpdate();
        System.out.println("Table 'users' was created successfully");
        closeTransactionSession();
    }

    @Override
    public void dropUsersTable() {
        openTransactionSession();
        Session session = getSession();
        session.createSQLQuery("DROP TABLE IF EXISTS users;").executeUpdate();
        System.out.println("Table 'users' was deleted successfully");
        closeTransactionSession();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        openTransactionSession();
        Session session = getSession();
        User user = new User();
        user.setName(name);
        user.setLastName(lastName);
        user.setAge(age);
        session.save(user);
        System.out.println(user);
        closeTransactionSession();
    }

    @Override
    public void removeUserById(long id) {
        openTransactionSession();
        Session session = getSession();
        User user = new User();
        user.setId(id);
        session.delete("id", user);
        closeTransactionSession();
    }

    @Override
    public List<User> getAllUsers() {
        openTransactionSession();
        Session session = getSession();
        Query query = session.createNativeQuery("SELECT * FROM users;").addEntity(User.class);
        List<User> userList = query.list();
        closeTransactionSession();
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        openTransactionSession();
        Session session = getSession();
        session.createSQLQuery("TRUNCATE users;").executeUpdate();
        System.out.println("Table 'users' was cleared");
        closeTransactionSession();
    }
}
