package app.back.real.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {

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
