package app.back.real.service.impl;

import app.back.real.dto.UserDTO;
import app.back.real.entity.UserEntity;
import app.back.real.mapper.UserMapper;
import app.back.real.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDTO login(String email, String password) {
        try {
            log.info("Iniciando sesion");
            UserDTO user = userMapper.getUserByEmail(email);
            if (user != null) {
                // Desencriptar la contraseña almacenada en la base de datos
                String decryptPassword = user.getPassword();
                // Verificar si la contraseña ingresada coincide con la contraseña almacenada desencriptada
                if (passwordEncoder.matches(password, decryptPassword)) {
                    // Contraseña válida, se puede devolver el usuario
                    return user;
                } else {
                    log.error("Contraseña incorrecta para el usuario: " + email);
                    return null;
                }
            } else {
                log.error("Usuario no encontrado para el email: " + email);
                return null;
            }
        } catch (Exception e) {
            log.error("Error al iniciar sesión: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer registerUser(UserDTO user) {
        try {
            log.info("Usuario registrado");
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userMapper.registerUser(user);

        }catch (Exception e) {
            log.error("Error al registrar usuario: " + e.getMessage());
            e.printStackTrace();
            return null;
        }

    }
}
