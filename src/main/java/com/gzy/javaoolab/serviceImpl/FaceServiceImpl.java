//package com.gzy.javaoolab.serviceImpl;
//
//import com.gzy.javaoolab.service.FaceService;
//import jakarta.annotation.Resource;
//import org.springframework.stereotype.Service;
//import com.gzy.javaoolab.entity.Face;
//import com.gzy.javaoolab.dao.FaceMapper;
//import com.gzy.javaoolab.vo.Result;
//
//import java.util.List;
//
//@Service
//public class FaceServiceImpl implements FaceService {
//
//    @Resource
//    private FaceMapper faceMapper;
//
//    @Override
//    public Object insert(Face face) {
//        faceMapper.insert(face);
//        return Result.success();
//    }
//
//    @Override
//    public List<Face> loadAll() {
//        return faceMapper.loadAll();
//    }
//}
