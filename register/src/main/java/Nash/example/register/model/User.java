package Nash.example.register.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@AllArgsConstructor
@Data
public class User {
    private Integer id;
    @NotBlank(message="使用者名稱不能為空")
    @Length(min=4, max=12, message="使用者名稱長度必須在4到12字元之間")
    private String username;
    @NotBlank(message="密碼不能為空")
    @Length(min=4, max=8, message="密碼長度必須在4到18字元之間")
    private String password;
    private String confirmPassword;
    private Boolean sex;
    @NotBlank(message="郵件地址不能為空")
    @Email(message="郵件地址必須視有效的郵件地址")
    private String email;
    private String pwdQuestion;
    private String pwdAnswer;
    private Date regDate;
    private Date lastLoginDate;
    private String lastLoginIp;

}
