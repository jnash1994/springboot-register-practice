package Nash.example.register.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity //宣告為 Entity 類型，並映射至一個資料表
@Table(name = "user") //映射資料表的名稱，預設是使用 class 名稱作為資料表名稱
//name：自定義資料表名稱，資料表名稱為 user
@Getter
@Setter
public class UserModel {
    @Id //宣告為主鍵(Primary Key)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //GenerationType.IDENTITY：自動增長型，最常使用
    //GenerationType.TABLE：透過別的表格來定義，最少用
    //GenerationType.SEQUENCE：序列方式生成
    //GenerationType.AUTO：程式自行決定
    private int id;
    @Column(unique = true)
    @NotBlank(message = "帳號不可為空")
    //@UniqueUsername
    private String username;

    @Column(unique = true)
    @Email(message = "信箱格式錯誤")
    @NotBlank(message = "信箱不可為空")
    //@UniqueEmail
    private String email;

    @Column
    @Size(min = 8, message = "密碼不可少於8位")
    @NotBlank(message = "密碼不可為空")
    private String password;

}



