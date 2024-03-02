package devangelistesecurity.SpringSecurity.service;

import devangelistesecurity.SpringSecurity.repository.UserRepository;
import devangelistesecurity.SpringSecurity.model.auth.AuthResponse;
import devangelistesecurity.SpringSecurity.model.auth.LoginRequest;
import devangelistesecurity.SpringSecurity.model.auth.RegisterRequest;
import devangelistesecurity.SpringSecurity.model.entity.UserEntity;
import devangelistesecurity.SpringSecurity.model.enums.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final  JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse submitRegistrationRequest(RegisterRequest request) {
        String token = jwtService.getToken(request.getUsername());

        log.info("Token generated: " + token);

        return AuthResponse.builder()
                .token(token)
                .registerRequest(request)
                .build();
    }

    public String validateUserRegistration(AuthResponse authResponse, String token) {
        if (authResponse.getToken().equals(token)){
            RegisterRequest request = authResponse.getRegisterRequest();
            UserEntity user = UserEntity.builder()
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .firstname(request.getFirstname())
                    .lastname(request.getLastname())
                    .country(request.getCountry())
                    .role(Role.USER)
                    .build();

            userRepository.save(user);

            return jwtService.getToken(user.getUsername());
        }
        return "NO SE PUDO VERIFICAR CON EXITO EL REGISTRO DEL USUARIO";
    }

    public String login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        return jwtService.getToken(request.getUsername());
    }
}
