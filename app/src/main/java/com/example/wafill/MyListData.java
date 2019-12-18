package com.example.wafill;

public class MyListData {
    private String description, subTitle, txtDate;
    private int imgId;

    public MyListData(String description, String subTitle, String txtDate, int imgId) {
        this.description = description;
        this.subTitle = subTitle;
        this.txtDate = txtDate;
        this.imgId = imgId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getTxtDate() {
        return txtDate;
    }

    public void setTxtDate(String txtDate) {
        this.txtDate = txtDate;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}
