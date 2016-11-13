package com.jie.bean;

/**
 * Created by jie on 16-7-18.
 */
public class AmazonUserComment {

    private String userId;//评论用户ID
    private String userName;//用户账户名
    private String time;//评论时间
    private float stars;//评论星级
    private String thingId;//物品ID
    private String content;//评论内容主体
    private String contentSummary;//评论内容sum
    private int helpPersons;//评论帮助个数
    private String thingStype;//物品的类型
    private String thingName;

    @Override
    public String toString() {
        return "AmazonUserComment{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", time='" + time + '\'' +
                ", stars=" + stars +
                ", thingId='" + thingId + '\'' +
                ", content='" + content + '\'' +
                ", contentSummary='" + contentSummary + '\'' +
                ", helpPersons=" + helpPersons +
                ", thingStype='" + thingStype + '\'' +
                ", thingName='" + thingName + '\'' +
                '}';
    }

    public void printMess(){
        System.out.println(toString());
    }

    public String getThingName() {
        return thingName;
    }

    public void setThingName(String thingName) {
        this.thingName = thingName;
    }

    public String getThingStype() {
        return thingStype;
    }

    public void setThingStype(String thingStype) {
        this.thingStype = thingStype;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public float getStars() {
        return stars;
    }

    public void setStars(float stars) {
        this.stars = stars;
    }

    public String getThingId() {
        return thingId;
    }

    public void setThingId(String thingID) {
        this.thingId = thingID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentSummary() {
        return contentSummary;
    }

    public void setContentSummary(String contentSummary) {
        this.contentSummary = contentSummary;
    }

    public int getHelpPersons() {
        return helpPersons;
    }

    public void setHelpPersons(int helpPersons) {
        this.helpPersons = helpPersons;
    }
}
