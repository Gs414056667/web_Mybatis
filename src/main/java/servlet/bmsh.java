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
 * @date 2020/10/22-17:19
 */
@WebServlet("/bmsh")
public class bmsh extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* 处理响应乱码问题:字节流需getBytes("UTF-8") */
        response.setContentType("text/html;charset=UTF-8");
        /* 处理post请求乱码问题 */
        request.setCharacterEncoding("UTF-8");
        String code = request.getParameter("code");
        String type = request.getParameter("type");
        System.out.println(type);
        SqlSession sqlSession = MybatisUtils.getSession();
        FormMapper mapper = sqlSession.getMapper(FormMapper.class);
        if(type.equals("agree") ){
            String bmshyj = request.getParameter("bmshyj");
            Map<String, Object> map = new HashMap<>();
            map.put("bmshyj",bmshyj);
            map.put("code",code);
            int i = mapper.bmsh_agree(map);
            if(i>0){
                response.getWriter().write("yes");
            }else{
                response.getWriter().write("no");
            }
            sqlSession.commit();
            sqlSession.close();
        }else{
            String bmshyj = request.getParameter("bmshyj");
            Map<String, Object> map = new HashMap<>();
            map.put("bmshyj",bmshyj);
            map.put("code",code);
            int i = mapper.bmsh_disagree(map);
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
