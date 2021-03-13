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
 * @date 2020/10/21-17:48
 */
@WebServlet("/updaterole")
public class updaterole extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* 处理响应乱码问题:字节流需getBytes("UTF-8") */
        response.setContentType("text/html;charset=UTF-8");
        /* 处理post请求乱码问题 */
        request.setCharacterEncoding("UTF-8");
        String username=request.getParameter("username");
        System.out.println("成功进入servlet！！！");
        String role=request.getParameter("role");
        SqlSession sqlSession = MybatisUtils.getSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        String permissioncode = mapper.getpermissioncode(role);//获取角色的权限类型
        System.out.println(permissioncode);
        sqlSession.close(); //关闭连接  否则会死锁
        sqlSession = MybatisUtils.getSession();
        Map<String, Object> map = new HashMap<>();
        map.put("username",username);
        map.put("role",role);
        map.put("permissioncode",permissioncode);
        UserMapper mapper1 = sqlSession.getMapper(UserMapper.class);
        System.out.println("正在修改权限！！！");
        int i = mapper1.updaterole(map);
        sqlSession.commit();
        sqlSession.close();
        if (i>0){
            response.getWriter().write("yes");
        }else
        {
            response.getWriter().write("no");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
