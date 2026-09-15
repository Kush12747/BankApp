package learn.BankApp.Service;

import learn.BankApp.Models.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final List<User> users = new ArrayList<>();

    public UserService() {
        users.add(new User(1, "Kush Gandhi", "kushgandhi2099.com", LocalDateTime.now()));
    }

    public List<User> getAllUsers() {
        return users;
    }

    public User getUserById(int id) {
        return users.stream().filter(user -> user.getUserId() == id).findFirst().orElse(null);
    }

    public User createUser(User user) {
        users.add(user);
        return user;
    }


}
