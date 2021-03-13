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

@WebServlet("/check_inst")
public class check_inst extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* 处理响应乱码问题:字节流需getBytes("UTF-8") */
        response.setContentType("text/html;charset=UTF-8");
        /* 处理post请求乱码问题 */
        request.setCharacterEncoding("UTF-8");
        String inst = request.getParameter("inst");
        SqlSession sqlSession = MybatisUtils.getSession();

        FormMapper mapper = sqlSession.getMapper(FormMapper.class);
        boolean b = mapper.check_inst(inst);
        if(b&&inst!=""){
            System.out.println(inst+"已经存在");
            response.getWriter().write("yes");
        }else {
            System.out.println(inst+"不存在");
            response.getWriter().write("no");
        }
        sqlSession.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
