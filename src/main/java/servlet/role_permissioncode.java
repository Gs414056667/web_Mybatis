package servlet;

import Utils.MybatisUtils;
import dao.FormMapper;
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
 * @date 2020/10/21-17:26
 */
@WebServlet("/role_permissioncode")
public class role_permissioncode extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* 处理响应乱码问题:字节流需getBytes("UTF-8") */
        response.setContentType("text/html;charset=UTF-8");
        /* 处理post请求乱码问题 */
        request.setCharacterEncoding("UTF-8");
        String permissioncode = request.getParameter("permissioncode");
        String role = request.getParameter("role");

        SqlSession sqlSession = MybatisUtils.getSession();
        Map<String, Object> map = new HashMap<>();
        map.put("permissioncode",permissioncode);
        map.put("role",role);
        FormMapper mapper = sqlSession.getMapper(FormMapper.class);
        int i = mapper.role_permissioncode(map);
        sqlSession.commit();
        sqlSession.close();

        sqlSession=MybatisUtils.getSession();
        UserMapper mapper1 = sqlSession.getMapper(UserMapper.class);
        int i1 = mapper1.updaterolepermissioncode(map);
        if(i>0&&i1>0){
            response.getWriter().write("yes");
        }else{
            response.getWriter().write("no");
        }
        sqlSession.commit();
        sqlSession.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
