package devangelistesecurity.SpringSecurity.cotroller;

import devangelistesecurity.SpringSecurity.model.auth.AuthResponse;
import devangelistesecurity.SpringSecurity.model.auth.LoginRequest;
import devangelistesecurity.SpringSecurity.model.auth.RegisterRequest;
import devangelistesecurity.SpringSecurity.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> submitRegistrationRequest(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.submitRegistrationRequest(request));
    }

    @PostMapping("/validateRegister")
    public ResponseEntity<String> validateUserRegistration(@RequestBody AuthResponse authResponse, @RequestParam String token) {
            return ResponseEntity.ok(authService.validateUserRegistration(authResponse, token));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Bienvenido sin seguridad";
    }

}
