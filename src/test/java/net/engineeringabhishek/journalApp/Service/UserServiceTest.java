package net.engineeringabhishek.journalApp.Service;

import net.engineeringabhishek.journalApp.entity.User;
import net.engineeringabhishek.journalApp.repository.UserRepository;
import net.engineeringabhishek.journalApp.service.UserService;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

//    @ParameterizedTest
    @ArgumentsSource(UserArgumentProvider.class)
    public void testSaveNewUser(User user) {
        assert(userService.saveNewUser(user));
    }

}
