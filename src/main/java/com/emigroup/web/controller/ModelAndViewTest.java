package com.emigroup.web.controller;


import com.alibaba.fastjson.JSONObject;
import com.emigroup.web.service.CvService;
import com.emigroup.web.vo.Cv;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("/test")
@Controller
public class ModelAndViewTest {

    @Resource
    CvService cvService;


    @ResponseBody
    @RequestMapping(value = {"/model"},method = RequestMethod.POST)
    public JSONObject testModel(){
        System.out.println("test model start");

//        ModelAndView  model  =  new ModelAndView();
//        model.addObject("obj1", "hello");
//        model.addObject("obj2","world");
//        return model;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "猫");
        jsonObject.put("address","深圳");

        List<Cv> userlist = cvService.findAll();
        jsonObject.put("user",userlist);
        return jsonObject;
    }



}
