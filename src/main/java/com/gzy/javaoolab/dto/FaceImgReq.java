package com.gzy.javaoolab.dto;

import jakarta.validation.constraints.NotEmpty;

import java.util.ArrayList;

public class FaceImgReq {
    @NotEmpty(message = "用户ID不能为空")
    private long id;

    @NotEmpty(message = "人脸图片不能为空")
    private ArrayList<String> faceImages;

    //getter and setter


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ArrayList<String> getFaceImages() {
        return faceImages;
    }
}
