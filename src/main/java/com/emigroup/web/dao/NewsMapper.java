package com.emigroup.web.dao;

import com.emigroup.web.vo.News;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NewsMapper {
    @Insert("INSERT INTO news(title,abs,date,text) VALUES(#{title}, #{abs},#{date},#{text})")
    int insert(@Param("title") String title, @Param("abs") String abs,@Param("date") String date,
               @Param("text") String text);

    @Select("SELECT * FROM News")
    List<News> findAll();

    @Select("SELECT * FROM NEWS WHERE ID=#{ID}")
    News findById(int id);
    @Select("Select * from News where id=(select max(id) from news)")
    News findLatest();

    @Delete("delete from news where id=#{id}")
    int deleteById(int id);

    @Update("update news  set title=#{title}," +
            "abs=#{abs},date=#{date},text=#{text}" +
            " where id=#{id}")
    int update(@Param("id") int id,@Param("title") String title, @Param("abs") String abs,@Param("date") String date,
               @Param("text") String text);


}
