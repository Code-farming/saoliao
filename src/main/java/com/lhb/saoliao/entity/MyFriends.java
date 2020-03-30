package com.lhb.saoliao.entity;

import java.io.Serializable;
import lombok.Data;
/**
 * (MyFriends)实体类
 *
 * @author LHb
 * @since 2020-03-22 13:47:20
 */
@Data
public class MyFriends implements Serializable {
    private static final long serialVersionUID = 723691692204403556L;
    
    private String id;
    /**
    * 用户id
    */
    private String myUserId;
    /**
    * 用户的好友id
    */
    private String myFriendUserId;
}