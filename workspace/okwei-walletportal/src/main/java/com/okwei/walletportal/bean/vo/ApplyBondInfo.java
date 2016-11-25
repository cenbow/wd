package com.okwei.walletportal.bean.vo;

public class ApplyBondInfo {
    /**
     * 保证退出id
     */
    public int tid;
    /**
     * 状态
     */
    public int state;
    /**
     * 申请时间
     */
    public String time;
    
    /**
     * 处理时间
     */
    public String cltime;
    
    /**
     * 提款账户
     */
    public String account;

    /**
     * 不通过理由
     */
    public String reason;
    
    /**
     * 打款金额
     */
    public double amount;
    /**
     * 银行卡号
     */
    public String bankNo;
    /**
     * 正面照
     */
    public String imageBack;
    /**
     * 凭证
     */
    public String imageFront;
    
    /**
     * 提款类型
     */
    public int type;
    /**
     * 明细路径
     */
    public String detailpath;
    
    /**
     * 重新跳转链接
     */
    public String url;
    /**
     * 认证员微店号
     */
    public long rzweiid;
    
    /**
     * 打款时间
     */
    public String paytime;
    
    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public String getImageBack() {
        return imageBack;
    }

    public void setImageBack(String imageBack) {
        this.imageBack = imageBack;
    }

    public String getImageFront() {
        return imageFront;
    }

    public void setImageFront(String imageFront) {
        this.imageFront = imageFront;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDetailpath() {
        return detailpath;
    }

    public void setDetailpath(String detailpath) {
        this.detailpath = detailpath;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCltime() {
        return cltime;
    }

    public void setCltime(String cltime) {
        this.cltime = cltime;
    }

    public long getRzweiid() {
        return rzweiid;
    }

    public void setRzweiid(long rzweiid) {
        this.rzweiid = rzweiid;
    }

    public String getPaytime() {
        return paytime;
    }

    public void setPaytime(String paytime) {
        this.paytime = paytime;
    }
    
}

