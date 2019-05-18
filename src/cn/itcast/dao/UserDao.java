package cn.itcast.dao;

import cn.itcast.domain.LoginUser;
import cn.itcast.domain.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
    LoginUser login(LoginUser loginUser);

    int findTotalCount(Map<String, String[]> condition);

    List<User> findUserList(int start, int rows, Map<String, String[]> condition);

    void addUser(User user);

    void delUser(int parseInt);

    User findUserById(int id);

    void updateUser(User user);

    void addLoginUser(LoginUser user);
}
