package com.lhb.saoliao.entity;

import java.util.Date;
import java.io.Serializable;
import lombok.Data;
/**
 * (FriendsRequest)实体类
 *
 * @author LHb
 * @since 2020-03-22 13:42:38
 */
@Data
public class FriendsRequest implements Serializable {
    private static final long serialVersionUID = 387051517290039300L;
    
    private String id;
    
    private String sendUserId;
    
    private String acceptUserId;
    /**
    * 发送请求的事件
    */
    private Date requestDateTime;
}