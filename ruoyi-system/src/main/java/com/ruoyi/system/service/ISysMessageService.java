package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysMessage;

/**
 * 留言Service接口
 * 
 * @author ruoyi
 * @date 2023-02-06
 */
public interface ISysMessageService 
{
    /**
     * 查询留言
     * 
     * @param messageId 留言ID
     * @return 留言
     */
    public SysMessage selectSysMessageByMessageId(Long messageId);

    /**
     * 查询留言列表
     * 
     * @param sysMessage 留言
     * @return 留言集合
     */
    public List<SysMessage> selectSysMessageList(SysMessage sysMessage);

    /**
     * 新增留言
     * 
     * @param sysMessage 留言
     * @return 结果
     */
    public int insertSysMessage(SysMessage sysMessage);

    /**
     * 修改留言
     * 
     * @param sysMessage 留言
     * @return 结果
     */
    public int updateSysMessage(SysMessage sysMessage);

    /**
     * 批量删除留言
     * 
     * @param messageIds 需要删除的留言ID
     * @return 结果
     */
    public int deleteSysMessageByMessageIds(Long[] messageIds);

    /**
     * 删除留言信息
     * 
     * @param messageId 留言ID
     * @return 结果
     */
    public int deleteSysMessageByMessageId(Long messageId);
}