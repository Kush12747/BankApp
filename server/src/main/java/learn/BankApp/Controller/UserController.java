package learn.BankApp.Controller;

import learn.BankApp.Models.User;
import learn.BankApp.Service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userservice;

    public UserController(UserService userservice) {
        this.userservice = userservice;
    }

    @GetMapping
    public List<User> getUsers() {
        return userservice.getAllUsers();
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable int userId) {
        return userservice.getUserById(userId);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userservice.createUser(user);
    }
}
