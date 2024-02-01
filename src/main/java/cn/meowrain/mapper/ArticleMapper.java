package cn.meowrain.mapper;

import cn.meowrain.pojo.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleMapper {
    @Insert("insert into article(title,content,cover_img,state,create_user,category_id,create_time,update_time) values (#{title},#{content},#{coverImg},#{state},#{createUser},#{categoryId},now(),now())")
    void postNewArticle(Article article);


    @Update("update article set title=#{title},content=#{content},cover_img=#{coverImg},state=#{state},create_user=#{createUser},update_time=#{updateTime} where id = #{id}")
    void updateArticle(Article article);


    @Select("select * from article where id = #{id}")
    Article getArticleDetailById(@Param("id") Integer id);

    @Select("select * from article")
    List<Article> getAllArticle();

    @Delete("delete from article where id = #{id}")
    void deleteArtcileById(@Param("id") Integer id);
}
