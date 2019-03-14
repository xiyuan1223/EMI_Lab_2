package com.emigroup.web.service;

import com.emigroup.web.dao.NewsMapper;
import com.emigroup.web.vo.News;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class NewsService {
    @Resource
    NewsMapper newsMapper;

    public int addNews(News news){
        return newsMapper.insert(news.getTitle(),news.getAbs(),news.getDate(),news.getText());
    }

    public int deleteById(int  id){
        return newsMapper.deleteById(id);
    }

    public int update(News news){
        return newsMapper.update(news.getId(),news.getTitle(),news.getAbs(),news.getDate(),news.getText());
    }

    public List<News> findAll(){
        return newsMapper.findAll();
    }

    public News findById(int id){
        return newsMapper.findById(id);
    }
    public News findLatest(){return newsMapper.findLatest();}



}
