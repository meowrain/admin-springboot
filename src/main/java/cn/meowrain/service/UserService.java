package cn.meowrain.service;

import cn.meowrain.pojo.User;


public interface UserService {

    User findByUserName(String username);

    void register(String username, String password);

    void update(User user);


    void updateAvatarUrl(String avatarUrl);

    void updatePwd(String newPwd);
}
