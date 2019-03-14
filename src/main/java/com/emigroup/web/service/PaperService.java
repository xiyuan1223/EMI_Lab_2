package com.emigroup.web.service;

import com.emigroup.web.dao.PaperMapper;
import com.emigroup.web.vo.Paper;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Transactional
@Service
public class PaperService {
    @Resource
    PaperMapper paperMapper;

    public static String cvImage = "cvImage";
    public String getFilePath(MultipartFile file, HttpServletRequest request){

        if (file.getSize()== 0) {
            return null;
        }

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



    /**
     * 获取年份集
     *
     * @return
     */
    public Set<Integer> findYear() {
        HashSet<Integer> listYear = new HashSet<Integer>();
        List<Paper> paperList = paperMapper.findAll();
        for (int i = 0; i < paperList.size(); i++) {
            listYear.add(Integer.parseInt(paperList.get(i).getDate().substring(0, 4)));
        }
        return listYear;

    }

    /**
     * 获取所有paper
     *
     * @return
     */
    public List<Paper> findPaper() {
        List<Paper> paperList = paperMapper.findAll();
        return paperList;
    }
    public Paper findById(int id){
        return paperMapper.findById(id);
    }

    /**
     * 增加paper
     * @return
     */
    public int insert(String title, String author, String journal, String date, String place, String type, String cardid, MultipartFile code, MultipartFile pdf, HttpServletRequest request){
        String codepath = getFilePath(code,request);
        String pdfpath = getFilePath(pdf,request);
        return paperMapper.insert(title,author,journal,date,place,type,cardid,codepath,pdfpath);
    }
    public int addPaper(Paper paper){
        return paperMapper.insert(paper.getTitle(), paper.getAuthor(), paper.getJournal(), paper.getDate(), paper.getPlace(), paper.getType(),paper.getCardid(),paper.getCodePath(),paper.getPdfPath());
    }

    public int update(Paper paper){
        return paperMapper.update(paper.getId(),paper.getTitle(),paper.getAuthor(),paper.getJournal(),paper.getDate(),paper.getPlace(),paper.getType(),paper.getCardid(),paper.getCodePath(),paper.getPdfPath());
    }

    public int deleteById(int id){
        return paperMapper.deleteById(id);
    }

}
