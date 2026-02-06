package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.SysMessage;
import com.ruoyi.system.service.ISysMessageService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 留言Controller
 * 
 * @author ruoyi
 * @date 2023-02-06
 */
@RestController
@RequestMapping("/system/message")
public class SysMessageController extends BaseController
{
    @Autowired
    private ISysMessageService sysMessageService;

    /**
     * 查询留言列表
     */
    @PreAuthorize("@ss.hasPermi('system:message:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysMessage sysMessage)
    {
        startPage();
        List<SysMessage> list = sysMessageService.selectSysMessageList(sysMessage);
        return getDataTable(list);
    }

    /**
     * 导出留言列表
     */
    @PreAuthorize("@ss.hasPermi('system:message:export')")
    @Log(title = "留言", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysMessage sysMessage)
    {
        List<SysMessage> list = sysMessageService.selectSysMessageList(sysMessage);
        ExcelUtil<SysMessage> util = new ExcelUtil<SysMessage>(SysMessage.class);
        util.exportExcel(response, list, "留言数据");
    }

    /**
     * 获取留言详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:message:query')")
    @GetMapping(value = "/{messageId}")
    public AjaxResult getInfo(@PathVariable("messageId") Long messageId)
    {
        return AjaxResult.success(sysMessageService.selectSysMessageByMessageId(messageId));
    }

    /**
     * 新增留言
     */
    @PreAuthorize("@ss.hasPermi('system:message:add')")
    @Log(title = "留言", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysMessage sysMessage)
    {
        return toAjax(sysMessageService.insertSysMessage(sysMessage));
    }

    /**
     * 修改留言
     */
    @PreAuthorize("@ss.hasPermi('system:message:edit')")
    @Log(title = "留言", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysMessage sysMessage)
    {
        return toAjax(sysMessageService.updateSysMessage(sysMessage));
    }

    /**
     * 删除留言
     */
    @PreAuthorize("@ss.hasPermi('system:message:remove')")
    @Log(title = "留言", businessType = BusinessType.DELETE)
    @DeleteMapping("/{messageIds}")
    public AjaxResult remove(@PathVariable Long[] messageIds)
    {
        return toAjax(sysMessageService.deleteSysMessageByMessageIds(messageIds));
    }
    
    /**
     * 公开留言接口 - 不需要登录验证
     */
    @PostMapping("/public/add")
    public AjaxResult addPublic(@RequestBody SysMessage sysMessage)
    {
        // 设置默认状态为正常
        sysMessage.setStatus("0");
        // 设置创建时间为当前时间
        sysMessage.setCreateTime(new java.util.Date());
        return toAjax(sysMessageService.insertSysMessage(sysMessage));
    }
}