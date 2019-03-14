package com.emigroup.web.dao;

import com.emigroup.web.vo.Paper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PaperMapper { //mapper 是接口形式
    @Select("SELECT * FROM PAPER")
    List<Paper> findAll();

    @Select("SELECT * FROM PAPER WHERE ID=#{ID}")
    Paper findById(int id);


    @Delete("delete from paper where id=#{id}")
    int deleteById(int id);

    @Insert("INSERT INTO PAPER(title,author,journal,date,place,type,cardid,codepath,pdfpath) VALUES(#{title}, #{author},#{journal},#{date},#{place},#{type},#{cardid},#{codepath},#{pdfpath})")
    int insert(@Param("title") String title, @Param("author") String author,
               @Param("journal") String journal,@Param("date") String date,
               @Param("place")String place,@Param("type") String type,@Param("cardid")String cardid,@Param("codepath")String codepath,@Param("pdfpath")String pdfpath);


    @Update("update Paper  set title=#{title}," +
            "author=#{author},journal=#{journal},date=#{date}," +
            "place=#{place},type=#{type},cardid=#{cardid},codepath=#{codepath},pdfpath=#{pdfpath}" +
            " where id=#{id}")
    int update(@Param("id") int id,@Param("title") String title, @Param("author") String author,
               @Param("journal") String journal,@Param("date") String date,
               @Param("place")String place,@Param("type") String type,@Param("cardid")String cardid,@Param("codepath")String codepath,@Param("pdfpath")String pdfpath);
};

