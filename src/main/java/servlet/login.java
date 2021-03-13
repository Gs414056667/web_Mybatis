package servlet;

import POJO.Users;
import Utils.MybatisUtils;
import com.google.gson.Gson;
import dao.UserMapper;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/login")
public class login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* 处理响应乱码问题:字节流需getBytes("UTF-8") */
        response.setContentType("text/html;charset=UTF-8");
        /* 处理post请求乱码问题 */
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username+password);
        SqlSession sqlSession = MybatisUtils.getSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        Map<String,Object> map = new HashMap<>();
        map.put("username",username);
        map.put("password",password);
        Users user  = mapper.login(map);
        System.out.println(user.getPermissioncode());
        if (user !=null)
        {
            Gson gson = new Gson();
            response.setContentType("text/html;charset=utf-8");
            String json = gson.toJson(user);
            System.out.println(json);
            response.getWriter().write(json);
        }
        else
        {
            Users u=new Users();
            u.setUsername("null");
            u.setStatus(500);
            Gson gson = new Gson();
            response.setContentType("text/html;charset=utf-8");
            String json = gson.toJson(u);
            response.getWriter().write(json);
        }
        sqlSession.close();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
