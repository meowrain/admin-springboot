package cn.meowrain.mapper;

import cn.meowrain.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {
    @Select("select * from category")
    List<Category> getCategories();

    @Insert("insert into category(category_name,category_alias,create_user,create_time,update_time) values (#{categoryName},#{categoryAlias},#{createUser},now(),now())")
    void insertCategory(Category category);

    @Update("update category set category_name = #{categoryName},category_alias=#{categoryAlias},update_time=#{updateTime} where id = #{id}")
    void updateCategory(Category category);


    @Select("select * from category where id = #{id}")
    Category getCategoryById(@Param("id") Integer id);

    @Delete("delete from category where id = #{id}")
    void deleteCategoryById(@Param("id") Integer id);
}
