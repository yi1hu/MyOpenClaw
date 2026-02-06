package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 留言对象 sys_message
 * 
 * @author ruoyi
 * @date 2023-02-06
 */
public class SysMessage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 留言ID */
    private Long messageId;

    /** 用户姓名 */
    @Excel(name = "用户姓名")
    private String userName;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String contactInfo;

    /** 留言内容 */
    @Excel(name = "留言内容")
    private String messageContent;

    /** IP地址 */
    @Excel(name = "IP地址")
    private String ipAddress;

    /** 用户代理 */
    @Excel(name = "用户代理")
    private String userAgent;

    /** 留言状态（0正常 1拉黑） */
    @Excel(name = "留言状态", readConverterExp = "0=正常,1=拉黑")
    private String status;

    public void setMessageId(Long messageId) 
    {
        this.messageId = messageId;
    }

    public Long getMessageId() 
    {
        return messageId;
    }

    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }

    public void setContactInfo(String contactInfo) 
    {
        this.contactInfo = contactInfo;
    }

    public String getContactInfo() 
    {
        return contactInfo;
    }

    public void setMessageContent(String messageContent) 
    {
        this.messageContent = messageContent;
    }

    public String getMessageContent() 
    {
        return messageContent;
    }

    public void setIpAddress(String ipAddress) 
    {
        this.ipAddress = ipAddress;
    }

    public String getIpAddress() 
    {
        return ipAddress;
    }

    public void setUserAgent(String userAgent) 
    {
        this.userAgent = userAgent;
    }

    public String getUserAgent() 
    {
        return userAgent;
    }

    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("messageId", getMessageId())
            .append("userName", getUserName())
            .append("contactInfo", getContactInfo())
            .append("messageContent", getMessageContent())
            .append("ipAddress", getIpAddress())
            .append("userAgent", getUserAgent())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}