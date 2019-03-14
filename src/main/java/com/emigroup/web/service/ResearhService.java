package com.emigroup.web.service;

import com.emigroup.web.dao.CvMapper;
import com.emigroup.web.dao.ResearchMapper;
import com.emigroup.web.vo.Cv;
import com.emigroup.web.vo.Research;
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
public class ResearhService {
    @Resource
    ResearchMapper researchMapper;

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
    public String insert(MultipartFile file,String title,String abs,String type,String text,String cardid,MultipartFile code,MultipartFile pdf,HttpServletRequest request){

        String preImage = getFilePath(file,request);
        String codepath = getFilePath(code,request);
        String pdfpath = getFilePath(pdf,request);
        researchMapper.insert(title,abs,type,preImage,text,cardid,codepath,pdfpath);
        return "success";
    }


    public int deleteById(int  id){
        return researchMapper.deleteById(id);
    }

    public int update(Research research){
        return researchMapper.update(research.getId(),research.getTitle(),research.getAbs(),research.getType(),research.getPreImage(),research.getText(),research.getCardid(),research.getCodePath(),research.getPdfPath());
    }
    public Research findById(int id){
        return researchMapper.findById( id);
    }

    public List<Research> findAll(){
        return researchMapper.findAll();
    }

}
