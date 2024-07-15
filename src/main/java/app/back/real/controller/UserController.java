package app.back.real.controller;

import app.back.real.dto.ResponseDTO;
import app.back.real.dto.UserDTO;
import app.back.real.request.LoginRequest;
import app.back.real.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
@Log4j2
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> loginUser(@RequestBody LoginRequest request) {
        ResponseDTO.ResponseDTOBuilder respBuilder = ResponseDTO.builder();

        try {
            UserDTO user = userService.login(request.getEmail(), request.getPassword());
            boolean success = Objects.nonNull(user);

            respBuilder
                    .status(success)
                    .message(success ? "Usuario autenticado con éxito" : "Credenciales no son válidas")
                    .data(user);

            return new ResponseEntity<>(respBuilder.build(), success ? HttpStatus.OK : HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            log.error("Error al iniciar sesión: " + e.getMessage());
            respBuilder
                    .status(false)
                    .message("Error al iniciar sesión: " + e.getMessage());
            return new ResponseEntity<>(respBuilder.build(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> registerUser(@RequestBody UserDTO request) {
        ResponseDTO.ResponseDTOBuilder respBuilder = ResponseDTO.builder();

        try {
            Integer register = userService.registerUser(request);
            boolean success = Objects.nonNull(register);

            respBuilder
                    .status(success)
                    .message(success ? "Usuario registrado con éxito" : "Ocurrió un error al registrar el usuario")
                    .data(register);

            return new ResponseEntity<>(respBuilder.build(), success ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            log.error("Error al registrar usuario: " + e.getMessage());
            respBuilder
                    .status(false)
                    .message("Error al registrar usuario: " + e.getMessage());
            return new ResponseEntity<>(respBuilder.build(), HttpStatus.BAD_REQUEST);
        }
    }


}
