package com.emigroup.web.dao;

import com.emigroup.web.vo.Research;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface ResearchMapper {
    @Insert("INSERT INTO research(title,abs,type,preImage,text,cardid,codepath,pdfpath) VALUES(#{title}, #{abs},#{type},#{preImage},#{text},#{cardid},#{codepath},#{pdfpath})")
    int insert(@Param("title") String title, @Param("abs") String abs,@Param("type") String type,@Param("preImage")String preImage,
               @Param("text") String text,@Param("cardid")String cardid,@Param("codepath")String codepath,@Param("pdfpath")String pdfpath);

    @Select("SELECT * FROM research")
    List<Research> findAll();

    @Select("SELECT * FROM research WHERE ID=#{ID}")
    Research findById(int id);

    @Delete("delete from research where id=#{id}")
    int deleteById(int id);

    @Update("update research  set title=#{title}," +
            "abs=#{abs},type=#{type},preImage=#{preImage},text=#{text},cardid=#{cardid},codepath=#{codepath},pdfpath={pdfpath}" +
            " where id=#{id}")
    int update(@Param("id") int id,@Param("title") String title, @Param("abs") String abs,@Param("type") String type,@Param("preImage") String preImage,
               @Param("text") String text,@Param("cardid")String cardid,@Param("codepath")String codepath,@Param("pdfpath")String pdfpath);

}
