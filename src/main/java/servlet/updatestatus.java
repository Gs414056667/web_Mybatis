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
 * @date 2020/10/19-20:43
 */
@WebServlet("/updatestatus")
public class updatestatus extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* 处理响应乱码问题:字节流需getBytes("UTF-8") */
        response.setContentType("text/html;charset=UTF-8");
        /* 处理post请求乱码问题 */
        request.setCharacterEncoding("UTF-8");
        String username=request.getParameter("username");
        String status=request.getParameter("status");
        SqlSession sqlSession = MybatisUtils.getSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        Map<String, Object> map = new HashMap<>();
        if(status.equals("1")) {
            map.put("username", username);
            map.put("status",0);
            int i = mapper.updatestatus(map);
            if (i > 0) {
                response.getWriter().write("yes");
            } else {
                response.getWriter().write("no");
            }
            sqlSession.commit();
            sqlSession.close();
        }else{
            map.put("username", username);
            map.put("status",1);
            int i = mapper.updatestatus(map);
            if (i > 0) {
                response.getWriter().write("yes");
            } else {
                response.getWriter().write("no");
            }
            sqlSession.commit();
            sqlSession.close();

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
