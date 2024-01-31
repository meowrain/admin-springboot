package cn.meowrain.mapper;

import cn.meowrain.pojo.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Select("select * from user where username = #{username}")
    User findByUserName(@Param("username") String username);

    @Insert("insert into user(username,password,create_time,update_time)" +
            " values (#{username},#{password},now(),now())")
    void register(@Param("username") String username, @Param("password") String password);

    @Update("update user set nickname = #{nickname},email = #{email},update_time = #{updateTime} where id = #{id}")
    void update(User user);

    @Update("update user set user_pic = #{avatarUrl},update_time=now() where id = #{id}")
    void updateAvatarUrl(@Param("avatarUrl") String avatarUrl,@Param("id") Integer id);

    @Update("update user set password = #{password},update_time=now() where id = #{id}")
    void updatePwd(@Param("password") String newPwd,@Param("id") Integer id);

}
