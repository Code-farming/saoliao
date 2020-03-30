package com.lhb.saoliao.service;

import com.lhb.saoliao.entity.ChatMsg;
import java.util.List;

/**
 * (ChatMsg)表服务接口
 *
 * @author LHb
 * @since 2020-03-22 13:44:56
 */
public interface ChatMsgService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ChatMsg queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<ChatMsg> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param chatMsg 实例对象
     * @return 实例对象
     */
    ChatMsg insert(ChatMsg chatMsg);

    /**
     * 修改数据
     *
     * @param chatMsg 实例对象
     * @return 实例对象
     */
    ChatMsg update(ChatMsg chatMsg);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}