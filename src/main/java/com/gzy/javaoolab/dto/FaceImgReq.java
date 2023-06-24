package com.gzy.javaoolab.dto;

import jakarta.validation.constraints.NotEmpty;

import java.util.ArrayList;

public class FaceImgReq {
    @NotEmpty(message = "用户ID不能为空")
    private String name;

    @NotEmpty(message = "人脸图片不能为空")
    private ArrayList<String> faceImages;

    //getter and setter


    public String getName() {
        return name;
    }

    public void setId(String name) {
        this.name = name;
    }

    public ArrayList<String> getFaceImages() {
        return faceImages;
    }
}
