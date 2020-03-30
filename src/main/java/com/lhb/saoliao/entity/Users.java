package com.lhb.saoliao.entity;

import java.io.Serializable;
import lombok.Data;
/**
 * (Users)实体类
 *
 * @author LHb
 * @since 2020-03-22 13:42:03
 */
@Data
public class Users implements Serializable {
    private static final long serialVersionUID = 107313688311635858L;
    
    private String id;
     /**
     * 用户名，账号，慕信号
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 我的头像，如果没有默认给一张
     */
    private String faceImage;

    private String faceImageBig;
    /**
     * 昵称
     */
    private String nickname;
    /**
    * 新用户注册后默认后台生成二维码，并且上传到fastdfs
    */
    private String qrcode;
    
    private String cid;
}