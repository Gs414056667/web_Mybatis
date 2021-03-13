package servlet;

import Utils.MybatisUtils;
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

/**
 * @author GaoShuai
 * @date 2020/10/19-19:20
 */
@WebServlet("/updatepsw")
public class updatepsw extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* 处理响应乱码问题:字节流需getBytes("UTF-8") */
        response.setContentType("text/html;charset=UTF-8");
        /* 处理post请求乱码问题 */
        request.setCharacterEncoding("UTF-8");
        String username=request.getParameter("username");
        String psw=request.getParameter("password");
        String type=request.getParameter("type");
        if(type.equals("reset")){
            psw = "654321";
        }
        SqlSession sqlSession = MybatisUtils.getSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        Map<String, Object> map = new HashMap<>();
        map.put("username",username);
        map.put("psw",psw);
        int i = mapper.updatepsw(map);
        if (i>0){
            response.getWriter().write("yes");
        }else
        {
            response.getWriter().write("no");
        }
        sqlSession.commit();
        sqlSession.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
