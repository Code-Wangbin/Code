package cn.itcast.service.Impl;

import cn.itcast.dao.UserDao;
import cn.itcast.dao.Impl.UserDaoImpl;
import cn.itcast.domain.LoginUser;
import cn.itcast.domain.PageBean;
import cn.itcast.domain.User;
import cn.itcast.service.UserService;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();
    @Override
    public LoginUser login(LoginUser loginUser) {
        return dao.login(loginUser);
    }

    @Override
    public PageBean<User> findUserByPage(String _currentPage, String _rows, Map<String, String[]> condition) {
        PageBean<User> pb = new PageBean<>();
        //int currentPage ; 
        int currentPage = Integer.parseInt(_currentPage);
        pb.setCurrentPage(currentPage);

        //int rows;//每页显示的记录数
        int rows = Integer.parseInt(_rows);
        pb.setRows(rows);

        //int totalCount; 
        int totalCount = dao.findTotalCount(condition);
        pb.setTotalCount(totalCount);

        //int totalPage ; 
        int totalPage = (int)Math.ceil(totalCount * 1.0 / rows);
        pb.setTotalPage(totalPage);

        //List<T> list ;
        int start = (currentPage - 1) *rows;
        List<User> list = dao.findUserList(start,rows,condition);
        pb.setList(list);

        return pb;
    }

    @Override
    public void addUser(User user) {
        dao.addUser(user);
    }

    @Override
    public void delUser(String id) {
        dao.delUser(Integer.parseInt(id));
    }

    @Override
    public User findUserById(String id) {
        return dao.findUserById(Integer.parseInt(id));
    }

    @Override
    public void updateUser(User user) {
        dao.updateUser(user);
    }

    @Override
    public void delServlet(String[] users) {
        for (String user : users) {
            dao.delUser(Integer.parseInt(user));
        }
    }

    @Override
    public void addLoginUser(LoginUser user) {
        dao.addLoginUser(user);
    }
}
