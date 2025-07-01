package net.engineeringabhishek.journalApp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import net.engineeringabhishek.journalApp.cache.AppCache;
import net.engineeringabhishek.journalApp.entity.User;
import net.engineeringabhishek.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/admin")
@Tag(name = "Admin APIs")
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
