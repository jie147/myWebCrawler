package com.jie.bean;

import java.util.Arrays;

/**
 * Created by jie on 16-7-22.
 */
public class ThingMes {
    private String thingId;
    private String thingName;
    private String keywords;

    private String prodDetails;

    private String money;

    private double sumStars;

    private String classType;

    private String content;

    @Override
    public String toString() {
        return "ThingMes{" +
                "thingId='" + thingId + '\'' +
                ", thingName='" + thingName + '\'' +
                ", keywords='" + keywords + '\'' +
                ", prodDetails='" + prodDetails + '\'' +
                ", money=" + money +
                ", sumStars=" + sumStars +
                ", classType='" + classType + '\'' +
                '}';
    }

    public void printMes(){
        System.out.println(toString());
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setProdDetails(String prodDetails) {
        this.prodDetails = prodDetails;
    }

    public String getProdDetails() {
        return prodDetails;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getThingId() {
        return thingId;
    }

    public void setThingId(String thingId) {
        this.thingId = thingId;
    }

    public String getThingName() {
        return thingName;
    }

    public void setThingName(String thingName) {
        this.thingName = thingName;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public double getSumStars() {
        return sumStars;
    }

    public void setSumStars(double sumStars) {
        this.sumStars = sumStars;
    }

}
