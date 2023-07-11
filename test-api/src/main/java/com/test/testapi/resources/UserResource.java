package com.test.testapi.resources;

import com.test.testapi.domain.User;
import com.test.testapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserResource {
    @Autowired
    UserService userService;
    @PostMapping("/register")
    public ResponseEntity<Map<String ,String >> registerUser(@RequestBody Map<String, Object> userMap){
        String firstName = (String) userMap.get("firstName");
        String lastName = (String) userMap.get("lastName");
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");
        User user = userService.registerUser(firstName,lastName,email,password);
        Map<String, String> map = new HashMap<>();
        map.put("message", "Registered succesfully");
        map.put("userId", String.valueOf(user.getId()));
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
