package com.emigroup.web.service;

import com.emigroup.web.dao.AwardMapper;
import com.emigroup.web.dao.NewsMapper;
import com.emigroup.web.vo.Award;
import com.emigroup.web.vo.News;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class AwardService {
    @Resource
    AwardMapper awardMapper;

    public int addAward(Award award){
        return awardMapper.insert(award.getUsername(),award.getCardid(),award.getType(),award.getItemname(),award.getConference());
    }

    public int deleteById(int  id){
        return awardMapper.deleteById(id);
    }

    public int update(Award award){
        return awardMapper.update(award.getId(),award.getUsername(),award.getCardid(),award.getType(),award.getItemname(),award.getConference());
    }

    public List<Award> findAll(){
        return awardMapper.findAll();
    }

    public Award findById(int id){
        return awardMapper.findById(id);
    }
    public List<Award> findByCardId(String cardid){return awardMapper.findByCardId(cardid);}
}
