package solid;

import solid.s.User;
import solid.s.UserService;

public class Main {

    public static void main(String[] args) {
        User user = new User("firstName", "lastName", "fake.mail.for.dev@gmail.com");
        UserService userService = new UserService();
        userService.registerUser(user);
    }

}
