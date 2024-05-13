package app.back.real.service;

import app.back.real.dto.UserDTO;
import app.back.real.entity.UserEntity;
import org.apache.ibatis.annotations.Param;

public interface UserService {

    UserDTO login(String email, String password);

    Integer registerUser(@Param("userEntity") UserDTO user);
}
