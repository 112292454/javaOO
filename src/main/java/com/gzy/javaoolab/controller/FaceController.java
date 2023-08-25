package com.gzy.javaoolab.controller;

import com.gzy.javaoolab.entity.Face;
import com.gzy.javaoolab.entity.User;
import com.gzy.javaoolab.service.FaceService;
import com.gzy.javaoolab.utils.JwtUtils;


import com.gzy.javaoolab.dto.FaceImgReq;
import com.gzy.javaoolab.service.UserService;
import com.gzy.javaoolab.utils.FaceModelInstance;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import com.gzy.javaoolab.vo.Result;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;


@RestController
@RequestMapping(value = "/face")
@Api
public class FaceController {
    Logger logger = LoggerFactory.getLogger(FaceController.class);

    @Resource
    private FaceService faceService;

    @Resource
    private UserService userService;

    @Resource
    private JwtUtils jwtUtils;


    @ApiOperation(value = "人脸识别接口")
    @PostMapping("/login")
    @ResponseBody
    public Result<Object> login(@RequestBody FaceImgReq body) {
        logger.info("人脸识别，用户: {}", body.getName());
        User user=userService.loadByName(body.getName());
        if(user == null){
            return Result.error(HttpServletResponse.SC_NOT_FOUND,"用户不存在");
        }
        long id = user.getId();
        ArrayList<String> faces = body.getFaceImages();
        int count = 0;
        for (String imgBase64 : faces) {
            byte[] decoded = Base64.getDecoder().decode(imgBase64);
            int label = FaceModelInstance.getInstance().predict(decoded);

            logger.info("识别结果:{}", label);

            if(label != 0 && label == id) {
                count++;
            }
        }
        logger.info("准确率{}",(double)count/faces.size());

        if(count > faces.size()/2){
            Map<String,Object> data=new HashMap<>();
            data.put("email",user.getEmail());
            String token= jwtUtils.createJwt(String.valueOf(user.getId()),user.getName(),data);
            Map<String,Object> res=new HashMap<>();
            res.put("token", token);
            res.put("uid", user.getId());
            userService.addLoginTime(user.getId(), new Date());
            return Result.success("登陆成功").data(res);
        }

        return Result.error(HttpServletResponse.SC_UNAUTHORIZED,"登陆验证未通过");
    }

    @ApiOperation(value = "人脸注册接口")
    @PostMapping("/register")
    @ResponseBody
    public Result<String> register(@RequestBody FaceImgReq body) {
        User user=userService.loadByName(body.getName());

        if(user == null){
            return Result.error(HttpServletResponse.SC_NOT_FOUND,"用户不存在");
        }
        long id = user.getId();
        logger.info("人脸注册，用户: {}", body.getName());
        ArrayList<String> faces = body.getFaceImages();
        int count = 0;
        for (String imgBase64 : faces) {
            byte[] decoded = Base64.getDecoder().decode(imgBase64);

            Face face = new Face();
            face.setLabel((int) id);
            face.setImg(decoded);
            faceService.insert(face);
            count++;
        }

        FaceModelInstance.getInstance().train();

        return Result.success();

    }
}
