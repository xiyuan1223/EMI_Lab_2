package com.emigroup.web.controller;


import com.alibaba.fastjson.JSONArray;
import com.emigroup.web.service.AwardService;
import com.emigroup.web.vo.Award;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/award")
@Controller
public class AwardController {

    @Resource
    AwardService awardService;
    @ResponseBody
    @RequestMapping(value={"/addaward"},method=RequestMethod.POST)
    public String addAward(HttpServletRequest request){
        System.out.println("award redceived !");
        String cardid = request.getParameter("cardid");
        String username = request.getParameter("username");
        String type = request.getParameter("type");
        String itemList = request.getParameter("award");
        JSONArray jsonArr = JSONArray.parseArray(itemList);
        for(int i = 0;i<jsonArr.size();i++){

            System.out.println(jsonArr.getString(i));
            Award award = new Award();
            award.setCardid(cardid);
            award.setUsername(username);
            award.setType(type);
            award.setItemname(jsonArr.getString(i));
            awardService.addAward(award);
        }

        return "success";

    }

    @ResponseBody
    @RequestMapping(value={"/findall"},method=RequestMethod.POST)
    public List<Award> findAll(){
        return awardService.findAll();
    }


    @ResponseBody
    @RequestMapping(value={"/findbyid"},method=RequestMethod.POST)
    public Award findById(int id){
        return awardService.findById(id);

    }
    @ResponseBody
    @RequestMapping(value={"/findbycardid"},method = RequestMethod.POST)
    public List<Award> findByCardId(String cardid){
        return awardService.findByCardId(cardid);
    }

    @ResponseBody
    @RequestMapping(value={"/updateaward"},method=RequestMethod.POST)
    public String uptdateAward(Award award){
        awardService.update(award);
        return "success";


    }
    @ResponseBody
    @RequestMapping(value={"/deleteaward"},method=RequestMethod.POST)
    public String deleteById(int id){
        awardService.deleteById(id);
        return "success";
    }
}
