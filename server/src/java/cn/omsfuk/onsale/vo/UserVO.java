package cn.omsfuk.onsale.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
public class UserVO {
    
    private Integer id;
    
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9]*$")
    private String username;

    @Pattern(regexp = "^1[3|4|5|8][0-9]\\d{8}$")
    private String phone;

    @Pattern(regexp = "^[a-zA-Z0-9_]+@(([a-zA-z0-9]-*)+\\.){1,3}[a-zA-z\\-]+$")
    private String email;
    
    private String signature = "";
    
    private Integer mark = 0;
    
    private Integer type = 0;

    @Length(min = 6, max = 18)
    @JsonIgnore
    private String password;
    
    private Integer gender = 0;
    
    private Date birthday;

    @JsonFormat(locale="zh", timezone="UTC", pattern="yyyy-MM-dd HH:mm:ss")
    private Date date;
    
    private Integer fans = 0;

    private String portrait;

}
