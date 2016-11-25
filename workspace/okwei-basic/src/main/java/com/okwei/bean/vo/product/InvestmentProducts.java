package com.okwei.bean.vo.product;

public class InvestmentProducts {
    private Long merchantWeiId;
    private String merchantName;
    private Integer investmentDemandId;
    private String investmentDemandName;
    private Double storeBuyAmount;
    private int pageIndex;
    private int pageSize;
    private int totalPage;
    private int totalCount;
    private int form;
    private Object list;
    public Long getMerchantWeiId() {
        return merchantWeiId;
    }
    public void setMerchantWeiId(Long merchantWeiId) {
        this.merchantWeiId = merchantWeiId;
    }
    public String getMerchantName() {
        return merchantName;
    }
    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }
    public Integer getInvestmentDemandId() {
        return investmentDemandId;
    }
    public void setInvestmentDemandId(Integer investmentDemandId) {
        this.investmentDemandId = investmentDemandId;
    }
    public String getInvestmentDemandName() {
        return investmentDemandName;
    }
    public void setInvestmentDemandName(String investmentDemandName) {
        this.investmentDemandName = investmentDemandName;
    }
    public Double getStoreBuyAmount() {
        return storeBuyAmount;
    }
    public void setStoreBuyAmount(Double storeBuyAmount) {
        this.storeBuyAmount = storeBuyAmount;
    }
    public int getPageIndex() {
        return pageIndex;
    }
    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public int getTotalPage() {
        return totalPage;
    }
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    public int getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
    public int getForm() {
        return form;
    }
    public void setForm(int form) {
        this.form = form;
    }
    public Object getList() {
        return list;
    }
    public void setList(Object list) {
        this.list = list;
    }
    
}
