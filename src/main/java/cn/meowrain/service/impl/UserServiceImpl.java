package cn.meowrain.service.impl;

import cn.meowrain.mapper.UserMapper;
import cn.meowrain.pojo.User;
import cn.meowrain.service.UserService;
import cn.meowrain.utils.Md5Util;
import cn.meowrain.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;
    @Override
    public User findByUserName(String username) {
        return userMapper.findByUserName(username);
    }

    @Override
    public void register(String username, String password) {
        String encryptPassword = Md5Util.getMD5String(password);
        userMapper.register(username,encryptPassword);
    }

    @Override
    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    @Override
    public void updateAvatarUrl(String avatarUrl) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer)map.get("id");
        userMapper.updateAvatarUrl(avatarUrl,id);
    }

    @Override
    public void updatePwd(String newPwd) {
        String encryptNewPwd = Md5Util.getMD5String(newPwd);
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer)map.get("id");
        userMapper.updatePwd(encryptNewPwd,id);
    }


}
