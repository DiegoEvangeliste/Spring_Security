package devangelistesecurity.SpringSecurity.cotroller;

import devangelistesecurity.SpringSecurity.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<?> users = userService.findAll();
        return (!users.isEmpty()) ? ResponseEntity.ok(users) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
