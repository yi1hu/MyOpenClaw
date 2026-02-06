package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysMessageMapper;
import com.ruoyi.system.domain.SysMessage;
import com.ruoyi.system.service.ISysMessageService;

/**
 * 留言Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-02-06
 */
@Service
public class SysMessageServiceImpl implements ISysMessageService 
{
    @Autowired
    private SysMessageMapper sysMessageMapper;

    /**
     * 查询留言
     * 
     * @param messageId 留言ID
     * @return 留言
     */
    @Override
    public SysMessage selectSysMessageByMessageId(Long messageId)
    {
        return sysMessageMapper.selectSysMessageByMessageId(messageId);
    }

    /**
     * 查询留言列表
     * 
     * @param sysMessage 留言
     * @return 留言
     */
    @Override
    public List<SysMessage> selectSysMessageList(SysMessage sysMessage)
    {
        return sysMessageMapper.selectSysMessageList(sysMessage);
    }

    /**
     * 新增留言
     * 
     * @param sysMessage 留言
     * @return 结果
     */
    @Override
    public int insertSysMessage(SysMessage sysMessage)
    {
        return sysMessageMapper.insertSysMessage(sysMessage);
    }

    /**
     * 修改留言
     * 
     * @param sysMessage 留言
     * @return 结果
     */
    @Override
    public int updateSysMessage(SysMessage sysMessage)
    {
        return sysMessageMapper.updateSysMessage(sysMessage);
    }

    /**
     * 批量删除留言
     * 
     * @param messageIds 需要删除的留言ID
     * @return 结果
     */
    @Override
    public int deleteSysMessageByMessageIds(Long[] messageIds)
    {
        return sysMessageMapper.deleteSysMessageByMessageIds(messageIds);
    }

    /**
     * 删除留言信息
     * 
     * @param messageId 留言ID
     * @return 结果
     */
    @Override
    public int deleteSysMessageByMessageId(Long messageId)
    {
        return sysMessageMapper.deleteSysMessageByMessageId(messageId);
    }
}