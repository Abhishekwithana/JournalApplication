package net.engineeringabhishek.journalApp.Repository;

import net.engineeringabhishek.journalApp.repository.UserRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryImplTest {


    @Autowired
    UserRepositoryImpl userRepositoryImpl;

    @Test
    public void getUserForSATest() {
        userRepositoryImpl.getUserForSA();
    }
}
