package app.back.real.request;

import lombok.Data;

@Data
public class UserRequest {

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
