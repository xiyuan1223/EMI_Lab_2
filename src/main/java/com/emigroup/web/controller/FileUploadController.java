package com.emigroup.web.controller;

import com.emigroup.web.service.AttachmentServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

@RequestMapping("/addimage")
@Controller
public class FileUploadController {

    @Resource
    AttachmentServiceImpl attachmentService;
    @RequestMapping("/uploadImage.do")
    @ResponseBody
    public void receiveImage(@RequestPart("upload") MultipartFile file, HttpServletRequest request,HttpServletResponse response) {
        Map<String,String> map = attachmentService.ckEditorUploadImage(file,request);
//        return attachmentService.ckEditorUploadImage(file, request);
        try {
            PrintWriter out = response.getWriter();
            String CKEditorFuncNum = request.getParameter("CKEditorFuncNum");
            String imgUrl=  map.get("url");
            System.out.println(imgUrl+"上传图片路径");
            out.println("<script type=\"text/javascript\">");
            out.println("window.parent.CKEDITOR.tools.callFunction("
                    + CKEditorFuncNum + ",'" +imgUrl+ "','')");
            out.println("</script>");
        }catch (Exception e){
            e.printStackTrace();
        }
        }






}
