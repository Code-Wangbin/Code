package cn.itcast.dao.Impl;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.LoginUser;
import cn.itcast.domain.User;
import cn.itcast.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
   
    public LoginUser login(LoginUser loginUser) {
        String sql = "select * from login where username = ? and password = ?";
        LoginUser user = null;
        try{
            user = template.queryForObject(sql,new BeanPropertyRowMapper<LoginUser>(LoginUser.class),
                    loginUser.getUsername(),loginUser.getPassword());
        }catch (Exception e){
        }
        return user;
    }

    @Override

    public int findTotalCount(Map<String, String[]> condition) {
        String sql = "select count(*) from user where 1=1 ";
        ArrayList<Object> list = new ArrayList<>();
        Set<String> set = condition.keySet();
        StringBuilder sb = new StringBuilder(sql);
        for (String key : set) {
            if ("currentPage".equals(key) || "rows".equals(key)){
                continue;
            }
            String value = condition.get(key)[0];
            if (value != null || !"".equals(value)){
                sb.append(" and "+key+" like ? ");
                list.add("%"+value+"%");
            }
        }
        return template.queryForObject(sb.toString(),Integer.class,list.toArray());
    }

    @Override

    public List<User> findUserList(int start, int rows, Map<String, String[]> condition) {
        String sql = "select * from user where 1=1 ";
        ArrayList<Object> list = new ArrayList<>();
        Set<String> set = condition.keySet();
        StringBuilder sb = new StringBuilder(sql);
        for (String key : set) {
            if ("currentPage".equals(key) || "rows".equals(key)){
                continue;
            }
            String value = condition.get(key)[0];
            if (value != null || !"".equals(value)){
                sb.append(" and "+key+" like ? ");
                list.add("%"+value+"%");
            }
        }
        sb.append(" limit ?,?");
        list.add(start);
        list.add(rows);
        return template.query(sb.toString(),new BeanPropertyRowMapper<User>(User.class),list.toArray());
    }

    @Override

    public void addUser(User user) {
        String sql = "insert into user values(null,?,?,?,?,?,?)";
        template.update(sql,user.getName(),user.getGender(),user.getAge(),user.getAge(),user.getQq(),user.getEmail());
    }

    @Override

    public void delUser(int id) {
        String sql = "delete from user where id = ?";
        template.update(sql,id);
    }

    @Override

    public User findUserById(int id) {
        String sql = "select * from user where id = ?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),id);
    }

    @Override

    public void updateUser(User user) {
        String sql = "update user set gender = ?,age = ?,address = ?,qq = ?,email = ? where id = ?";
        template.update(sql,user.getGender(),user.getAge(),user.getAddress(),user.getQq(),
                user.getEmail(),user.getId());
    }

    @Override

    public void addLoginUser(LoginUser user) {
        String sql = "insert into login values(?,?,?)";
        template.update(sql,user.getName(),user.getUsername(),user.getPassword());
    }

}
