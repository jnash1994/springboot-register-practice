package Nash.example.register.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private Boolean sex;
    private String email;
    private String pwdQuestion;
    private String pwdAnswer;
    private Date regDate;
    private Date lastLoginDate;
    private String lastLoginIp;
}

