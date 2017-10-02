package cn.omsfuk.onsale.vo;

import lombok.Data;

import java.util.Date;

@Data
public class UserVO {
    
    private Integer id;
    
    private String username;
    
    private String phone;
    
    private String email;
    
    private String signature;
    
    private Integer mark;
    
    private Integer type;
    
    private String password;
    
    private Integer gender;
    
    private Date birthday;
    
    private Date date;
    
    private Integer fans;
}
