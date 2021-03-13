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
 * @date 2020/10/30-19:49
 */
@WebServlet("/general_inquiry")
public class general_inquiry extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* 处理响应乱码问题:字节流需getBytes("UTF-8") */
        response.setContentType("text/html;charset=UTF-8");
        /* 处理post请求乱码问题 */
        request.setCharacterEncoding("UTF-8");
        SqlSession sqlSession = MybatisUtils.getSession();

        String lj = request.getParameter("lj");
        String jsc = request.getParameter("jsc");
        String value = request.getParameter("value");
        String lx = request.getParameter("lx");

        String[] ljs = lj.split(",");
        String[] jscs = jsc.split(",");
        String[] values = value.split(",");
        String[] lxs = lx.split(",");
        String str;
        if(lxs[0].equals("like")){
             str = jscs[0]+" "+"like"+" '%"+values[0]+"%' ";
        }else {
            str = jscs[0]+"="+"'"+values[0]+"' ";
        }
        System.out.println(str);
        for (int i=1;i<ljs.length;i++)
            {
                if(lxs[i].equals("like")){
                    str+=ljs[i]+" "+jscs[i]+" "+" like"+" '%"+values[i]+"%' ";
                }else{
                    str+=ljs[i]+" "+jscs[i]+"="+"'"+values[i]+"' ";
                }
            }
        System.out.println(str);

        FormMapper mapper = sqlSession.getMapper(FormMapper.class);
        List<Form> formList = mapper.general_inquiry(str);
        System.out.println(str+"---------------");

        Gson gson = new Gson();
        response.setContentType("text/html;charset=utf-8");
        String json = gson.toJson(formList);
        System.out.println(json);
        response.getWriter().write(json);
        sqlSession.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
