package com.lhb.saoliao.service;

import com.lhb.saoliao.entity.FriendsRequest;
import java.util.List;

/**
 * (FriendsRequest)表服务接口
 *
 * @author LHb
 * @since 2020-03-22 13:42:38
 */
public interface FriendsRequestService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    FriendsRequest queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<FriendsRequest> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param friendsRequest 实例对象
     * @return 实例对象
     */
    FriendsRequest insert(FriendsRequest friendsRequest);

    /**
     * 修改数据
     *
     * @param friendsRequest 实例对象
     * @return 实例对象
     */
    FriendsRequest update(FriendsRequest friendsRequest);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}