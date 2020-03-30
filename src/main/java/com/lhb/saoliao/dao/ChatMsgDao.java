package com.lhb.saoliao.dao;

import com.lhb.saoliao.entity.ChatMsg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (ChatMsg)表数据库访问层
 *
 * @author LHb
 * @since 2020-03-22 13:44:56
 */
@Mapper
public interface ChatMsgDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ChatMsg queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<ChatMsg> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param chatMsg 实例对象
     * @return 对象列表
     */
    List<ChatMsg> queryAll(ChatMsg chatMsg);

    /**
     * 新增数据
     *
     * @param chatMsg 实例对象
     * @return 影响行数
     */
    int insert(ChatMsg chatMsg);

    /**
     * 修改数据
     *
     * @param chatMsg 实例对象
     * @return 影响行数
     */
    int update(ChatMsg chatMsg);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}