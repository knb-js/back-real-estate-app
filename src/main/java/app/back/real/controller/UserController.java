package app.back.real.controller;

import app.back.real.dto.UserDTO;
import app.back.real.request.LoginRequest;
import app.back.real.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
@Log4j2
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public UserDTO loginUser(@RequestBody LoginRequest request) {
        try {
            UserDTO user = userService.login(request.getEmail(), request.getPassword());
            if (user != null) {
                log.info("Usuario autenticado");
                return user;
            }
        }catch (Exception e) {
            log.error("Error al iniciar sesión: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
        return null;
    }

    @PostMapping("/register")
     public ResponseEntity<?> registerUser(@RequestBody UserDTO request) {
        try {
            Integer register = userService.registerUser(request);
            if (register != null) {
                return ResponseEntity.ok("Se ha registrado el usuario con éxito");
            }
        }catch (Exception e) {
            log.error("Error al registrar usuario: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
        return null;
     }

}
