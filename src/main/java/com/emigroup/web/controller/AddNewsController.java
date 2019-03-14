package com.emigroup.web.controller;

import com.emigroup.web.service.NewsService;
import com.emigroup.web.vo.News;
import com.emigroup.web.vo.Paper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RequestMapping("/addnews")
@Controller
public class AddNewsController {
    @Resource
    NewsService newsService;
    @ResponseBody
    @RequestMapping(value={"/addnews"},method=RequestMethod.POST)
    public String addNews(Model model, News news){
        newsService.addNews(news);
        System.out.println(news.getText());
        return "success";

    }


    @ResponseBody
    @RequestMapping(value={"/findall"},method=RequestMethod.POST)
    public List<News> findAll(){
        Comparator invertDictOrderForNews = new Comparator<News>() {//倒序
            @Override
            public int compare(News o1, News o2) {

                String []o1_candidate = o1.getDate().split("-");
                String []o2_candidate = o2.getDate().split("-");
                String o12str = "";
                for(int i = 0;i<o1_candidate.length;i++){
                    o12str+=o1_candidate[i];
                }
                String o22str = "";
                for(int i = 0;i<o2_candidate.length;i++){
                    o22str+=o2_candidate[i];
                }


                // TODO Auto-generated method stub
                if(o12str.compareTo(o22str)<0)
                    return 1;
                    //注意！！返回值必须是一对相反数，否则无效。jdk1.7以后就是这样。
                    //		else return 0; //无效o1_candidate
                else return -1;
            }
        };
        List<News> newsList = newsService.findAll();
        Collections.sort(newsList,invertDictOrderForNews);
        
        return newsList;
    }


    @ResponseBody
    @RequestMapping(value={"/findbyid"},method=RequestMethod.POST)
    public News findById(int id){
        return newsService.findById(id);

    }
    @ResponseBody
    @RequestMapping(value={"/updatenews"},method=RequestMethod.POST)
    public String updateNews(News news){
        newsService.update(news);
        return "success";


    }
    @ResponseBody
    @RequestMapping(value={"/deletenews"},method=RequestMethod.POST)
    public String deleteById(int id){
        newsService.deleteById(id);
        return "success";
    }

    @ResponseBody
    @RequestMapping(value={"/latestnews"},method=RequestMethod.POST)
    public News latestNews(){
        return newsService.findLatest();
    }


}
