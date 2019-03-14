package com.emigroup.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.emigroup.web.service.AwardService;
import com.emigroup.web.service.CvService;
import com.emigroup.web.service.PaperService;
import com.emigroup.web.vo.Award;
import com.emigroup.web.vo.Cv;
import com.emigroup.web.vo.Paper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.sun.net.httpserver.HttpContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


@RequestMapping("/cv")
@Controller
public class UploadCvController {
    @Resource
    CvService cvService;
    @Resource
    AwardService awardService;
    @Resource
    PaperService paperService;
    @ResponseBody
    @RequestMapping(value={"/addcv"},method=RequestMethod.POST)
    public String addcv(@RequestParam("imgfile") MultipartFile file, @RequestParam("name") String name, @RequestParam("position") String position,
                        @RequestParam("describe") String describe,@RequestParam("email")String email,@RequestParam("interest")String interest,@RequestParam("office")String office,@RequestParam("award")String award,@RequestParam("cardid")String cardid, HttpServletRequest request){
        System.out.println("name: = "+name);
        System.out.println("position: = "+position);
        System.out.println("describe: = "+describe);
        System.out.println("email: = "+email);
        System.out.println("interest: = "+interest);
        System.out.println("office: = "+office);
        System.out.println("award: = "+award);

            cvService.insert(file,name,position,describe,email,interest,office,award,cardid,request);

    return "success";
    }

    @ResponseBody
    @RequestMapping(value={"/getcv"},method=RequestMethod.POST)
    public List<Cv> getcv(){
        return cvService.findAll();
    }

    @ResponseBody
    @RequestMapping(value={"/getbyid"},method=RequestMethod.POST)
    public Cv getCvById(int id){
        System.out.println(cvService.findById(id).getImgPath());
        return cvService.findById(id);
    }

    @ResponseBody
    @RequestMapping(value={"/updatecv"},method=RequestMethod.POST)
    public String updateNews(Cv cv){
        cvService.update(cv);
        return "success";
    }

    @ResponseBody
    @RequestMapping(value={"/deletecv"},method=RequestMethod.POST)
    public String deleteById(int id){
        cvService.deleteById(id);
        return "success";
    }


    public JSONObject findPub(){
        Comparator invertDictOrder = new Comparator<Integer>() {//倒序
            @Override
            public int compare(Integer o1, Integer o2) {
                // TODO Auto-generated method stub
                if((int)o1<(int)o2)
                    return 1;
                    //注意！！返回值必须是一对相反数，否则无效。jdk1.7以后就是这样。
                    //		else return 0; //无效
                else return -1;
            }
        };
        JSONObject jsonObject = new JSONObject();
        ArrayList<Integer> yearList = new ArrayList<Integer>(paperService.findYear());
        Collections.sort(yearList,invertDictOrder);
        Comparator invertDictOrderForPaper = new Comparator<Paper>() {//倒序
            @Override
            public int compare(Paper o1, Paper o2) {
                // TODO Auto-generated method stub
                if(o1.getId()<o2.getId())
                    return 1;
                    //注意！！返回值必须是一对相反数，否则无效。jdk1.7以后就是这样。
                    //		else return 0; //无效
                else return -1;
            }
        };
        List<Paper> paperList = paperService.findPaper();
        Collections.sort(paperList,invertDictOrderForPaper);
//        for(int i = 0;i<paperList.size();i++){
//
////        System.out.println(paperList.get(i).getId());
//        }
        jsonObject.put("yearList",yearList);

        jsonObject.put("paperList",paperList);
        return jsonObject;



    }
    @ResponseBody
    @RequestMapping(value={"/getdetailbyid"},method=RequestMethod.POST)
    public JSONObject getdetail(@RequestParam("id") int id, HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        Comparator invertDictOrderForPaper = new Comparator<Paper>() {//倒序
            @Override
            public int compare(Paper o1, Paper o2) {
                // TODO Auto-generated method stub
                if(o1.getDate().compareTo(o2.getDate())<0)
                    return 1;
                    //注意！！返回值必须是一对相反数，否则无效。jdk1.7以后就是这样。
                    //		else return 0; //无效
                else return -1;
            }
        };
        Cv user = cvService.findById(id);
        String cardid = user.getCardid().trim();
        List<Award> awards = awardService.findAll();
        List<Paper> papers = paperService.findPaper();
        Collections.sort(papers,invertDictOrderForPaper);
        List<Paper> paperlist = new ArrayList<Paper>();
        List<String>awardlist = new ArrayList<String>();
        List<String> activitylist = new ArrayList<String>();
        List<String> invtallist = new ArrayList<String>();
        for(int i = 0;i<papers.size();i++){
            if(papers.get(i).getCardid()!=null&&papers.get(i).getCardid().trim().contains(cardid)){
                paperlist.add(papers.get(i));
            }
        }
        for(int i=0;i<awards.size();i++){
            if(awards.get(i).getCardid().trim().contains(cardid)){

                if(awards.get(i).getType().equals("0")){
                    awardlist.add(awards.get(i).getItemname());
                }
                else if(awards.get(i).getType().equals("1")){
                    activitylist.add(awards.get(i).getItemname());
                }
                else if(awards.get(i).getType().equals("2")){
                    invtallist.add(awards.get(i).getItemname());
                }
            }
        }
        jsonObject.put("user",user);
        jsonObject.put("paperlist",paperlist);
        jsonObject.put("awardlist",awardlist);
        jsonObject.put("activitylist",activitylist);
        jsonObject.put("invtallist",invtallist);

        return jsonObject;
    }

    @ResponseBody
    @RequestMapping(value={"/test"},method=RequestMethod.POST)
    public String testJson(HttpServletRequest request){
        System.out.println("hello");
        System.out.println(request.getParameter("award"));
        return "successful";
    }


    //    public String testJson(@RequestParam(value="awards",required = true)String[] awards,@RequestParam(value="idnumber")String idnumber,@RequestParam(value="type")String type){
//        System.out.println("hello");
//        System.out.println(awards);
//
//        return "successful";
//
//
//    }


}
