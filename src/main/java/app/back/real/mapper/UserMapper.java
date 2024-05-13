package app.back.real.mapper;

import app.back.real.dto.UserDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("SELECT rut, dv, name, lastName, phone, birthday, email, password, rol " +
            "FROM user " +
            "WHERE email = #{email} ")
    UserDTO getUserByEmail(String email);

    @Insert("INSERT INTO user (rut, dv, name, lastName, phone, birthday, email, password, rol) " +
            "VALUES " +
            "    (#{user.rut}, #{user.dv}, #{user.name}, #{user.lastName}, #{user.phone}, #{user.birthday}, #{user.email}, " +
            "    #{user.password}, 'user' )")
    Integer registerUser(@Param("user") UserDTO user);
}
