package com.gzy.javaoolab.utils;

import com.gzy.javaoolab.entity.Face;
import com.gzy.javaoolab.service.FaceService;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_face.FaceRecognizer;
import org.bytedeco.opencv.opencv_face.FisherFaceRecognizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.nio.IntBuffer;
import java.util.List;

import static org.bytedeco.opencv.global.opencv_core.CV_32SC1;


public class FaceModelInstance {
    private static final FaceModelInstance INSTANCE = new FaceModelInstance();

    private FaceModelInstance() {
    }

    FaceRecognizer faceRecognizer;

    @Autowired
    private FaceService faceService;
    Logger logger = LoggerFactory.getLogger(FaceModelInstance.class);

    public static FaceModelInstance getInstance() {
        return INSTANCE;
    }

    public void init() {
        faceRecognizer = FisherFaceRecognizer.create();
        train();
    }

    public void train() {
        List<Face> dataSet = faceService.loadAll();
        if (dataSet.size() == 0) {
            logger.info("训练数据集为空");
            return;
        }
        MatVector images = new MatVector(dataSet.size());
        Mat labels = new Mat(dataSet.size(), 1, CV_32SC1);
        IntBuffer labelsBuf = labels.createBuffer();
        int count = 0;
        for (Face data : dataSet) {
            Mat mat = null;
            BufferedImage image = null;
            try {
                ByteArrayInputStream stream = new ByteArrayInputStream(data.getImg());
                image = ImageIO.read(stream);
            } catch (Exception e) {
                e.printStackTrace();
            }
            mat = new OpenCVFrameConverter.ToMat().convert(new Java2DFrameConverter().convert(image));

            images.put(count, mat);
            labelsBuf.put(count, (int) data.getLabel());
//            try{
//                imwrite("./faces"+count+".jpg",mat);
////                Files.write(Paths.get("./faces"+count+".jpg"),mat);
//            }catch (Exception e) {
//                e.printStackTrace();
//            }
            count++;
        }

        logger.info("训练数据集大小：{}", dataSet.size());
        faceRecognizer.train(images, labels);
    }

    public int predict(byte[] img) {
        Mat mat = null;
        BufferedImage image = null;
        try {
            ByteArrayInputStream stream = new ByteArrayInputStream(img);
            image = ImageIO.read(stream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mat = new OpenCVFrameConverter.ToMat().convert(new Java2DFrameConverter().convert(image));


        IntPointer label = new IntPointer(1);
        DoublePointer confidence = new DoublePointer(1);
        faceRecognizer.predict(mat, label, confidence);
        logger.info("Predicted label: " + label.get(0) + "Confidence: " + confidence.get(0));
        if(confidence.get(0)>4000){
            return 0;
        }
        return label.get(0);
    }
}
