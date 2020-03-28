package ro.web.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.web.store.model.User;
import ro.web.store.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Endpoint
     * mapeaza un request HTTP la metoda addUser
     * @param u
     * @return
     */
    @PostMapping( "/addUser")
    public ResponseEntity addUser(User u){
        User newUser = userService.addUser(u);
       return  new ResponseEntity<User>(newUser, HttpStatus.OK);
    }
    @PostMapping( "/deleteUser")
    public ResponseEntity deleteUser(User u){
       // User newUser = userService.addUser(u);
        return  new ResponseEntity<User>(HttpStatus.OK);
    }

}
