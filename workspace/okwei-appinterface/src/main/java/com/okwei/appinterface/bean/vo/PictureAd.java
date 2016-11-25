package com.okwei.appinterface.bean.vo;

import java.io.Serializable;

/**
 * Created by tang on 16/7/15.
 */
public class PictureAd implements Serializable {

    private static final long serialVersionUID = -4513694210091535627L;

    /**
     * adId : 123
     * positionType : 0
     * title : XXX品牌特价活动
     * url : http://xxx/xxx
     * imageUrl : http://xxx/xxx.jpg
     * publishTime : 2016-04-29 10:00:00
     */

    private int adId;
    private int positionType;
    private String title;
    private String url;
    private String imageUrl;
    private String publishTime;

    public int getAdId() {
        return adId;
    }

    public void setAdId(int adId) {
        this.adId = adId;
    }

    public int getPositionType() {
        return positionType;
    }

    public void setPositionType(int positionType) {
        this.positionType = positionType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }
}
