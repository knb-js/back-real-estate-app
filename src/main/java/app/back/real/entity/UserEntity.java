package app.back.real.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    private String rut;
    private String dv;
    private String name;
    private String lastName;
    private String phone;
    private String birthday;
    private String email;
    private String password;
    private String rol;

}
