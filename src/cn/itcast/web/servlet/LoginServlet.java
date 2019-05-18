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

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String verifycode = request.getParameter("verifycode");
        HttpSession session = request.getSession();
        String CHECKCODE_SERVER = (String)session.getAttribute("CHECKCODE_SERVER");

        if (!CHECKCODE_SERVER.equalsIgnoreCase(verifycode)){
            request.setAttribute("error","验证码错误！");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
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

        if (user == null){
            request.setAttribute("error","用户名或密码错误！");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }

        session.setAttribute("loginUser",user);
        response.sendRedirect(request.getContextPath()+"/index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
