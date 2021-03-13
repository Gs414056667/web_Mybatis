package servlet;

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
import java.util.Map;

/**
 * @author GaoShuai
 * @date 2020/11/5-11:23
 */
@WebServlet("/TopN")
public class TopN extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* 处理响应乱码问题:字节流需getBytes("UTF-8") */
        response.setContentType("text/html;charset=UTF-8");
        /* 处理post请求乱码问题 */
        request.setCharacterEncoding("UTF-8");
        String tjzd = request.getParameter("tjzd");
        String N = request.getParameter("N");
        System.out.println(tjzd);
        System.out.println(N);
        SqlSession sqlSession = MybatisUtils.getSession();
        FormMapper mapper = sqlSession.getMapper(FormMapper.class);
        if(tjzd.equals("ip")){
            List<Map<String, Object>> list = mapper.TopN_ip(N);
            Gson gson = new Gson();
            String s = gson.toJson(list);
            response.getWriter().write(s);
            System.out.println(s);
        }else if(tjzd.equals("流量")){
            List<Map<String, Object>> list = mapper.TopN_traffic(N);
            Gson gson = new Gson();
            String s = gson.toJson(list);
            response.getWriter().write(s);
            System.out.println(s);
        }else if(tjzd.equals("类型")){
            List<Map<String, Object>> list = mapper.TopN_type(N);
            Gson gson = new Gson();
            String s = gson.toJson(list);
            response.getWriter().write(s);
            System.out.println(s);
        }
        sqlSession.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
