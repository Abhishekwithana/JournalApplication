package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRespository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public boolean saveNewUser(User userEntry) {
        try {
            userEntry.setPassword(passwordEncoder.encode(userEntry.getPassword()));
            userEntry.setRoles(Arrays.asList("USER"));
            userRespository.save(userEntry);
            return true;
        }catch (Exception e) {
            log.error("Error Occurred FOR user {}", userEntry.getUserName(), e);
            log.info("hahahahehe");
            log.warn("hahahahehe");
            log.debug("hahahahehe");
            log.trace("hahahahehe");
            return false;
        }
    }
    public void saveAdmin(User userEntry) {
        userEntry.setPassword(passwordEncoder.encode(userEntry.getPassword()));
        userEntry.setRoles(Arrays.asList("USER", "ADMIN"));
        userRespository.save(userEntry);
    }

    public void saveEntry(User userEntry) {
        userRespository.save(userEntry);
    }

    public List<User> getAll() {
        return userRespository.findAll();
    }

    public Optional<User> getEntryById(ObjectId objectId) {
        return userRespository.findById(objectId);
    }

    public void deleteEntryById(ObjectId id) {
        userRespository.deleteById(id);
    }

    public User findByUserName(String name) {
        return userRespository.findByUserName(name);
    }
}
