package justin_kim.careNeighbers.login;

import justin_kim.careNeighbers.login.LoginRequest;
import justin_kim.careNeighbers.user.User;
import justin_kim.careNeighbers.login.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@Valid @RequestBody LoginRequest loginRequest) {
        User authenticatedUser = authService.login(loginRequest.usernameOrEmail(), loginRequest.password());
        return ResponseEntity.ok(authenticatedUser);
    }
}

