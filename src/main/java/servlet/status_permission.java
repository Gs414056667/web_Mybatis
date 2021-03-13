package servlet;

import Utils.MybatisUtils;
import dao.FormMapper;
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
 * @date 2020/10/24-17:20
 */

@WebServlet("/status_permission")
public class status_permission extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* 处理响应乱码问题:字节流需getBytes("UTF-8") */
        response.setContentType("text/html;charset=UTF-8");
        /* 处理post请求乱码问题 */
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        String status = request.getParameter("status");
        SqlSession sqlSession = MybatisUtils.getSession();
        FormMapper mapper = sqlSession.getMapper(FormMapper.class);
        if(status.equals("1")) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", id);
            map.put("status","0");
            System.out.println("正在关闭权限:" + id);

            int i = mapper.status_permission(map);
            if(i>0){
                response.getWriter().write("yes");
            }else{
                response.getWriter().write("no");
            }
            sqlSession.commit();
            sqlSession.close();
        }else{
            Map<String, Object> map = new HashMap<>();
            map.put("id", id);
            map.put("status","1");
            System.out.println("正在开启权限:" + id);

            int i = mapper.status_permission(map);
            if(i>0){
                response.getWriter().write("yes");
            }else{
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
