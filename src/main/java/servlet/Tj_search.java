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
 * @date 2020/10/26-20:06
 */
@WebServlet("/Tj_search")
public class Tj_search extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* 处理响应乱码问题:字节流需getBytes("UTF-8") */
        response.setContentType("text/html;charset=UTF-8");
        /* 处理post请求乱码问题 */
        request.setCharacterEncoding("UTF-8");
        /* 处理响应乱码问题:字节流需getBytes("UTF-8") */
        response.setContentType("text/html;charset=UTF-8");
        /* 处理post请求乱码问题 */
        request.setCharacterEncoding("UTF-8");
        String tjzd = request.getParameter("tjzd");
        System.out.println("正在通过获取全部表");
        SqlSession sqlSession = MybatisUtils.getSession();
        FormMapper mapper = sqlSession.getMapper(FormMapper.class);
        if(tjzd.equals("机构属性")){
            List<Map<String, Object>> map = mapper.Tj_search_jgsx(tjzd);
            Gson gson = new Gson();
            String s = gson.toJson(map);

            response.getWriter().write(s);
            System.out.println(s);
        }else if(tjzd.equals("所在地域")){
            List<Map<String, Object>> map = mapper.Tj_search_szdy(tjzd);
            Gson gson = new Gson();
            String s = gson.toJson(map);

            response.getWriter().write(s);
            System.out.println(s);
        }else if(tjzd.equals("科技活动类型")){
            List<Map<String, Object>> map = mapper.Tj_search_yjlx(tjzd);
            Gson gson = new Gson();
            String s = gson.toJson(map);

            response.getWriter().write(s);
            System.out.println(s);
        }



        sqlSession.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
