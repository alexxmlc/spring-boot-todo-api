package spring.learn.todo_api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

import spring.learn.todo_api.dto.JwtResponse;
import spring.learn.todo_api.dto.LoginRequest;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserRepository userRepository;

    // Creates the token initially using the password to verify the user
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
        try {

            // The input comes in as loginRequest
            // Wrap it in an unauthorized token (UsernamePasswordAuthenticationToken)
            // Then send the unauthorized token to AuthenticationManager
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            // If the password from the DB matches with the one that the user entered in
            // login form
            // Authentication is now valid and we can generate a token for this request
            String token = jwtUtils.generateJwtToken(authentication);
            Optional<User> optionalUser = userRepository.findByUsername(loginRequest.getUsername());

            if (optionalUser.isPresent()) {
                User user = optionalUser.get();

                // Put the token and the user into a DTO
                JwtResponse jwtResponse = new JwtResponse(
                        token,
                        user.getId(),
                        user.getUsername(),
                        user.getEmail(),
                        List.of(user.getRoles().split(",")));

                // Send it back as JSON
                return ResponseEntity.ok(jwtResponse);
            }

        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // Fallback
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}
