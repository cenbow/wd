package com.okwei.pay.bean.vo;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UPushMessage entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_PushMessage")
public class UPushMessage implements java.io.Serializable
{

    // Fields

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private Long pushId;
    private Long pushWeiId;// 发送人
    private Long reciptWeiId;// 接收人
    private String pushContent;// 发送内容
    private Short msgType;// 消息类型
    private Date createTime;// 时间
    private String objectId; //事件主键
    private String senderAvatar;//发送人头像
    private String extra;
    private String objectUrl;
    private String title="";
    
    
    
    
    
    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getObjectUrl() {
		return objectUrl;
	}

	public void setObjectUrl(String objectUrl) {
		this.objectUrl = objectUrl;
	}

	public String getSenderAvatar() {
		return senderAvatar;
	}

	public void setSenderAvatar(String senderAvatar) {
		this.senderAvatar = senderAvatar;
	}

	@Column(name = "Extra")
    public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	// Constructors
    @Column(name = "ObjectID")
    public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	/** default constructor */

    public UPushMessage()
    {
    }

    /** full constructor */

    // Property accessors
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "PushID",unique = true,nullable = false)
    public Long getPushId()
    {
        return this.pushId;
    }

    public void setPushId(Long pushId)
    {
        this.pushId = pushId;
    }

    @Column(name = "PushWeiID")
    public Long getPushWeiId()
    {
        return this.pushWeiId;
    }

    public void setPushWeiId(Long pushWeiId)
    {
        this.pushWeiId = pushWeiId;
    }

    @Column(name = "ReciptWeiID")
    public Long getReciptWeiId()
    {
        return this.reciptWeiId;
    }

    public void setReciptWeiId(Long reciptWeiId)
    {
        this.reciptWeiId = reciptWeiId;
    }

    @Column(name = "PushContent",length = 256)
    public String getPushContent()
    {
        return this.pushContent;
    }

    public void setPushContent(String pushContent)
    {
        this.pushContent = pushContent;
    }

    @Column(name = "MsgType")
    public Short getMsgType()
    {
        return this.msgType;
    }

    public void setMsgType(Short msgType)
    {
        this.msgType = msgType;
    }

    @Column(name = "CreateTime",length = 0)
    public Date getCreateTime()
    {
        return this.createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

}