package com.lhb.saoliao.entity;

import java.util.Date;
import java.io.Serializable;
import lombok.Data;
/**
 * (ChatMsg)实体类
 *
 * @author LHb
 * @since 2020-03-22 13:44:56
 */
@Data
public class ChatMsg implements Serializable {
    private static final long serialVersionUID = -99614206998892929L;
    
    private String id;
    
    private String sendUserId;
    
    private String acceptUserId;
    
    private String msg;
    /**
    * 消息是否签收状态
1：签收
0：未签收

    */
    private Integer signFlag;
    /**
    * 发送请求的事件
    */
    private Date createTime;
}