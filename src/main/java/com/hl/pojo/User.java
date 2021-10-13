package com.hl.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    String UUID;
    String email;
    String password;
    String userName;
    Date creatTime;
    String creatByUUID;
    Date updateTime;
    String updateByUUID;

}
