package servlet;

import POJO.Form;
import Utils.MybatisUtils;
import com.google.gson.Gson;
import dao.FormMapper;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author GaoShuai
 * @date 2020/10/19-10:45
 */
@WebServlet("/getAll")
public class getAll extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* 处理响应乱码问题:字节流需getBytes("UTF-8") */
        response.setContentType("text/html;charset=UTF-8");
        /* 处理post请求乱码问题 */
        request.setCharacterEncoding("UTF-8");

        System.out.println("正在通过获取全部表");

        SqlSession sqlSession = MybatisUtils.getSession();
        FormMapper mapper = sqlSession.getMapper(FormMapper.class);
        List<Form> formList = mapper.getAllform();

        Gson gson=new Gson();
        String json=gson.toJson(formList);
        response.getWriter().write(json);
        System.out.println(json);
        sqlSession.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
