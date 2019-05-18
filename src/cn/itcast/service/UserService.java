package cn.itcast.service;

import cn.itcast.domain.LoginUser;
import cn.itcast.domain.PageBean;
import cn.itcast.domain.User;

import java.util.Map;

public interface UserService {
    LoginUser login(LoginUser loginUser);

    PageBean<User> findUserByPage(String currentPage, String rows, Map<String, String[]> condition);

    void addUser(User user);

    void delUser(String id);

    User findUserById(String id);

    void updateUser(User user);

    void delServlet(String[] users);

    void addLoginUser(LoginUser user);
}
