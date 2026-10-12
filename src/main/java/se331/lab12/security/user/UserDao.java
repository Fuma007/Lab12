package se331.lab12.security.user;

public interface UserDao {
    User findByUsername(String username);

    User save(User user);
}