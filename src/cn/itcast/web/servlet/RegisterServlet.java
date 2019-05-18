package cn.itcast.web.servlet;

import cn.itcast.domain.LoginUser;
import cn.itcast.service.Impl.UserServiceImpl;
import cn.itcast.service.UserService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String verifycode = request.getParameter("verifycode");
        HttpSession session = request.getSession();
        String CHECKCODE_SERVER = (String)session.getAttribute("CHECKCODE_SERVER");

        if (!CHECKCODE_SERVER.equalsIgnoreCase(verifycode)){
            request.setAttribute("error","验证码错误！");
            request.getRequestDispatcher("/register.jsp").forward(request,response);
            return;
        }

        Map<String,String[]> map = request.getParameterMap();
        LoginUser loginUser = new LoginUser();
        try{
            BeanUtils.populate(loginUser,map);
        }catch (Exception e){
            e.printStackTrace();
        }

        UserService service = new UserServiceImpl();
        LoginUser user = service.login(loginUser);

        if (user != null){
            request.setAttribute("error","用户名已存在，请重新输入");
            request.getRequestDispatcher("/register.jsp").forward(request,response);
            return;
        }

        service.addLoginUser(loginUser);

        request.getSession().setAttribute("success","注册成功");
        response.sendRedirect(request.getContextPath()+"/login.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
