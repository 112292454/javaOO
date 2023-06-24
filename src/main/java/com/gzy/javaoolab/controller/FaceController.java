package com.gzy.javaoolab.controller;

import com.gzy.javaoolab.entity.Face;
import com.gzy.javaoolab.service.FaceService;


import com.gzy.javaoolab.dto.FaceImgReq;
import com.gzy.javaoolab.utils.FaceModelInstance;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import com.gzy.javaoolab.vo.Result;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Base64;


@RestController
@RequestMapping(value = "/face")
@Api
public class FaceController {
    Logger logger = LoggerFactory.getLogger(FaceController.class);

    @Resource
    private FaceService faceService;


    @ApiOperation(value = "人脸识别接口")
    @PostMapping("/login")
    @ResponseBody
    public Result<String> login(@RequestBody FaceImgReq body) {
        logger.info("人脸识别，用户ID{}", body.getId());
        ArrayList<String> faces = body.getFaceImages();
        int count = 0;
        for (String imgBase64 : faces) {
            byte[] decoded = Base64.getDecoder().decode(imgBase64);

            logger.info("识别结果:{}", FaceModelInstance.getInstance().predict(decoded));

            count++;
        }

        return Result.success();
    }

    @ApiOperation(value = "人脸注册接口")
    @PostMapping("/register")
    @ResponseBody
    public Result<String> register(@RequestBody FaceImgReq body) {
        logger.info("人脸注册，用户ID{}", body.getId());
        ArrayList<String> faces = body.getFaceImages();
        int count = 0;
        for (String imgBase64 : faces) {
            byte[] decoded = Base64.getDecoder().decode(imgBase64);

            Face face = new Face();
            face.setLabel((int) body.getId());
            face.setImg(decoded);
            faceService.insert(face);
            count++;
        }

        FaceModelInstance.getInstance().train();

        return Result.success();

    }
}
