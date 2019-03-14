package com.emigroup.web.dao;

import com.emigroup.web.vo.Award;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AwardMapper {
    @Insert("insert into award(username,cardid,type,itemname,conference)VALUES(#{username},#{cardid},#{type},#{itemname},#{conference})")
    int insert(@Param("username") String username, @Param("cardid") String cardid,@Param("type") String type,@Param("itemname")String itemname, @Param("conference") String conference);

    @Select("select * from award")
    List<Award> findAll();

    @Select("select * from award where id=#{id}")
    Award findById(@Param("id")int id);

    @Delete("delete from award wher id =#{id}")
    int deleteById(@Param("id")int id);

    @Update("update ward set username=#{username},cardid=#{cardid},type=#{type},itemname=#{itemname},conference=#{conference} where id=#{id}")
    int update(@Param("id")int id,@Param("username") String username, @Param("cardid") String cardid,@Param("type") String type,@Param("itemname")String itemname, @Param("conference") String conference);

    @Select("select * from award where cardid=#{cardid}")
    List<Award> findByCardId(@Param("cardid")String cardid);



}
