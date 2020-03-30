package com.lhb.saoliao.entity.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UsersVo implements Serializable {
    private static final long serialVersionUID = 4943628575592980581L;
    private String id;
    private String username;
    private String faceImage;
    private String faceImageBig;
    private String nickname;
    private String qrcode;
}
