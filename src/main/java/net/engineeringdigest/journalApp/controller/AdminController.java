package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.cache.AppCache;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController  {

    @Autowired
    UserService userSerivce;

    @Autowired
    AppCache appCache;

    @GetMapping("/all-users")
    public ResponseEntity<?> allUsers() {
        List<User> list = userSerivce.getAll();
        if(!list.isEmpty()) {
            return new ResponseEntity<>(list,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create-admin-user")
    public void addAdmin(@RequestBody User user) {
        userSerivce.saveAdmin(user);
    }

    @GetMapping("clear-app-cache")
    public void clearAppCache() {
        appCache.init();
    }
}
