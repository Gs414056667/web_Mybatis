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

@WebServlet("/check_instcode")
public class check_instcode extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* 处理响应乱码问题:字节流需getBytes("UTF-8") */
        response.setContentType("text/html;charset=UTF-8");
        /* 处理post请求乱码问题 */
        request.setCharacterEncoding("UTF-8");
        String instcode = request.getParameter("instcode");
        SqlSession sqlSession = MybatisUtils.getSession();
        FormMapper mapper = sqlSession.getMapper(FormMapper.class);
        boolean b = mapper.check_instcode(instcode);
        if(b&&instcode!=""){
            System.out.println(instcode+"已经存在");
            response.getWriter().write("yes");
        }else {
            System.out.println(instcode+"不存在");
            response.getWriter().write("no");
        }
        sqlSession.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          doPost(request,response);
    }
}
