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
 * @date 2020/10/25-19:43
 */
@WebServlet("/Fuzzy_query")
public class Fuzzy_query extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* 处理响应乱码问题:字节流需getBytes("UTF-8") */
        response.setContentType("text/html;charset=UTF-8");
        /* 处理post请求乱码问题 */
        request.setCharacterEncoding("UTF-8");
        String jgmc = request.getParameter("jgmc");
        String szdy = request.getParameter("szdy");
        String lxr = request.getParameter("lxr");
        String gjz = request.getParameter("gjz");
        String yjlx = request.getParameter("yjlx");
        String ntrzj = request.getParameter("ntrzj");
        System.out.println("正在进行检索！！------->"+jgmc);
        System.out.println("正在进行检索！！------->"+szdy);
        System.out.println("正在进行检索！！------->"+lxr);
        System.out.println("正在进行检索！！------->"+gjz);
        System.out.println("正在进行检索！！------->"+yjlx);
//        Map<String, Object> map = new HashMap<>();
//        map.put("jgmc",jgmc);map.put("szdy",szdy);map.put("lxr",lxr);map.put("gjz",gjz);
//        map.put("yjlx",yjlx);map.put("ntrzj",ntrzj);
        SqlSession sqlSession = MybatisUtils.getSession();
        FormMapper mapper = sqlSession.getMapper(FormMapper.class);
        List<Form> formList = mapper.Fuzzy_query(jgmc,szdy,lxr,gjz,yjlx,ntrzj);

        Gson gson=new Gson();
        String json=gson.toJson(formList);
        response.getWriter().write(json);
        System.out.println(json);

        System.out.println("检索成功！！");

        sqlSession.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
