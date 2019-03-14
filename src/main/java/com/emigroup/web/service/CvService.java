package com.emigroup.web.service;

import com.emigroup.web.dao.CvMapper;
import com.emigroup.web.vo.Cv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class CvService {

    @Resource
    CvMapper cvMapper;
    @Autowired
    private Environment env;
    public static String cvImage = "cvImage";
    public String getFilePath(MultipartFile file, HttpServletRequest request){


        String originalName = file.getOriginalFilename();
        originalName = originalName.replace(" ","");
        // generate file name
        String localFileName = System.currentTimeMillis() + "-" + originalName;

//        String projectRealPath = env.getProperty("upload.path");
        //获取系统路径
//        File resourcefile = new File("src/main/resources/static/");
        File resourcefile = new File("./emi_resource");
        String projectRealPath = resourcefile.getAbsolutePath();

        // get the real path to store received images
        String realPath = projectRealPath +File.separator+cvImage;
        File imageDir = new File(realPath);
        if(!imageDir.exists()) {
            imageDir.mkdirs();
        }

        String localFilePath = realPath + ""+File.separator + localFileName;
        try {
            file.transferTo(new File(localFilePath));
        } catch (IllegalStateException e) {
            e.printStackTrace();
            // log here
        } catch (IOException e) {
            e.printStackTrace();
            // log here
        }
        //网络路径（配置了映射）
        String imageContextPath = "/images/"+ cvImage + File.separator + localFileName;
        System.out.println("received file original name: " + originalName);
        System.out.println("stored local file name: " + localFileName);
        System.out.println("file stored path: " + localFilePath);
        System.out.println("returned url: " + imageContextPath);

        return imageContextPath;
    }

    public String insert(MultipartFile file,String name,String position,String userdes,String email,String interest,String office,String award,String cardid,HttpServletRequest request){

        String imgpath = getFilePath(file,request);
        cvMapper.insert(name,position,userdes,imgpath,email,interest,office,award,cardid);
        return "success";
    }

    public List<Cv> findAll(){
        return cvMapper.findAll();
    }

    public Cv findById(int id){
        return cvMapper.findById(id);
    }

    public int deleteById(int id){
        return cvMapper.deleteById(id);
    }


    public int update(Cv cv){
        return cvMapper.update(cv.getId(), cv.getUsername(),cv.getPosition(),cv.getUserdes(),cv.getImgPath(),cv.getEmail(),cv.getInterest(),cv.getOffice(),cv.getAward(),cv.getCardid());

    }
}
