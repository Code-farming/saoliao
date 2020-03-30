package com.lhb.saoliao.dao;

import com.lhb.saoliao.entity.FriendsRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (FriendsRequest)表数据库访问层
 *
 * @author LHb
 * @since 2020-03-22 13:42:38
 */
@Mapper
public interface FriendsRequestDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    FriendsRequest queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<FriendsRequest> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param friendsRequest 实例对象
     * @return 对象列表
     */
    List<FriendsRequest> queryAll(FriendsRequest friendsRequest);

    /**
     * 新增数据
     *
     * @param friendsRequest 实例对象
     * @return 影响行数
     */
    int insert(FriendsRequest friendsRequest);

    /**
     * 修改数据
     *
     * @param friendsRequest 实例对象
     * @return 影响行数
     */
    int update(FriendsRequest friendsRequest);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}