package com.lhb.saoliao.controller;

import com.lhb.saoliao.entity.FriendsRequest;
import com.lhb.saoliao.service.FriendsRequestService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (FriendsRequest)表控制层
 *
 * @author LHb
 * @since 2020-03-22 13:42:38
 */
@RestController
@RequestMapping("friendsRequest")
public class FriendsRequestController {
    /**
     * 服务对象
     */
    @Resource
    private FriendsRequestService friendsRequestService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public FriendsRequest selectOne(String id) {
        return this.friendsRequestService.queryById(id);
    }

}