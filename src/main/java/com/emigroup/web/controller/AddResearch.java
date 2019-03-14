package com.emigroup.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.emigroup.web.service.ResearhService;
import com.emigroup.web.vo.Research;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/research")
@Controller
public class AddResearch {
    @Resource
    ResearhService researhService;
    @ResponseBody
    @RequestMapping(value={"/addresearch"},method=RequestMethod.POST)
    public String addresearch(@RequestParam("imgfile") MultipartFile file, @RequestParam("title") String title,
                              @RequestParam("type")String type, @RequestParam("abs") String abs,
                        @RequestParam("text") String text,@RequestParam("cardid")String cardid,@RequestParam("code")MultipartFile code,@RequestParam("pdf")MultipartFile pdf,HttpServletRequest request){
        System.out.println("tile: = "+title);
        System.out.println("abs: = "+abs);
        System.out.println("text: = "+text);
        System.out.println("text: = "+type);


        researhService.insert(file,title,abs,type,text,cardid,code,pdf,request);

        return "success";
    }


    @ResponseBody
    @RequestMapping(value={"/findAll"},method=RequestMethod.POST)
    public List<Research> getResearch(){
        return researhService.findAll();
    }

    @ResponseBody
    @RequestMapping(value={"/getbyid"},method=RequestMethod.POST)
    public Research getByid(int id){
        return researhService.findById(id);
    }
    @ResponseBody
    @RequestMapping(value={"/updateresearch"},method=RequestMethod.POST)
    public String updateNews(Research research){
        researhService.update(research);
        return "success";


    }
    @ResponseBody
    @RequestMapping(value={"/deleteResearch"},method=RequestMethod.POST)
    public String deleteById(int id){
        researhService.deleteById(id);
        return "success";
    }

    @ResponseBody
    @RequestMapping(value={"/jsontest"},method=RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String testJson(@RequestParam(value="awards",required = true)JSONObject awards){
        System.out.println(awards);

        return "successful";


    }
}
