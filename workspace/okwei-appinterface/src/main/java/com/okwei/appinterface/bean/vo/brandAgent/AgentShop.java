package com.okwei.appinterface.bean.vo.brandAgent;

import java.io.Serializable;

/**
 * Created by tang on 16/7/15.
 */
public class AgentShop extends BaseShop implements Serializable {

    private static final long serialVersionUID = 408051711236820277L;

    /**
     * userId : 1111
     * userName : XXX
     * shopName : XXX品牌特价活动
     * shopImg : http://xxx/xxx
     * identityType : 1
     * identityName : 城主
     * agentBrandWid : 123
     * agentType : 0
     * phone : 15818998333
     * address : 广东省 深圳市
     * parentWid : 1983
     * parentName : xxx微店
     */

    private String identityName;
    private Long agentBrandWid;
    private Integer agentType;
    private String phone;
    private String address;
    private Long parentWid;
    private String parentName;

    public AgentShop() {
    }

    public AgentShop(Long userId, String userName, String shopName, String shopImg, int identityType, String identityName, Long agentBrandWid, Integer agentType, String phone, String address, Long parentWid, String parentName) {
        super(userId, userName, shopName, shopImg, identityType);
        this.identityName = identityName;
        this.agentBrandWid = agentBrandWid;
        this.agentType = agentType;
        this.phone = phone;
        this.address = address;
        this.parentWid = parentWid;
        this.parentName = parentName;
    }

    public String getIdentityName() {
        return identityName;
    }

    public void setIdentityName(String identityName) {
        this.identityName = identityName;
    }

    public Long getAgentBrandWid() {
        return agentBrandWid;
    }

    public void setAgentBrandWid(Long agentBrandWid) {
        this.agentBrandWid = agentBrandWid;
    }

    public Integer getAgentType() {
        return agentType;
    }

    public void setAgentType(Integer agentType) {
        this.agentType = agentType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getParentWid() {
        return parentWid;
    }

    public void setParentWid(Long parentWid) {
        this.parentWid = parentWid;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}
