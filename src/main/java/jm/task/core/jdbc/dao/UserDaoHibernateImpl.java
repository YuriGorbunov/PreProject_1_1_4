package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().openSession() ) {
            session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS`db_users_hibernate`.`users` (" +
                    "`id` INT NOT NULL AUTO_INCREMENT, " +
                    "`name` VARCHAR(45) NOT NULL, " +
                    "`lastName` VARCHAR(45) NOT NULL, " +
                    "`age` INT(3) NOT NULL, PRIMARY KEY (`id`), " +
                    "UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);").executeUpdate();
            System.out.println("Table 'users' was created successfully");
            session.getTransaction().commit();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS users;").executeUpdate();
            System.out.println("Table 'users' was deleted successfully");
            session.getTransaction().commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            User user = new User();
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);
            session.save(user);
            System.out.println("User " + name + " " + lastName + " was added in DB");
            session.getTransaction().commit();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            User user = new User();
            user.setId(id);
            session.delete("id", user);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query query = session.createNativeQuery("SELECT * FROM users;").addEntity(User.class);
            List<User> userList = query.list();
            System.out.println(userList);
            session.getTransaction().commit();
            return userList;
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createSQLQuery("TRUNCATE users;").executeUpdate();
            System.out.println("Table 'users' was cleared");
            session.getTransaction().commit();
        }
    }
}
