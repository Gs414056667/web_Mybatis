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

import static java.lang.Integer.parseInt;

@WebServlet("/addform")
public class addform extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* 处理响应乱码问题:字节流需getBytes("UTF-8") */
        response.setContentType("text/html;charset=UTF-8");
        /* 处理post请求乱码问题 */
        request.setCharacterEncoding("UTF-8");
        String username=request.getParameter("username");
        String Code=request.getParameter("Code");
        int status= parseInt(request.getParameter("status"));
        String jgmc = request.getParameter("jgmc");
        String txdx=request.getParameter("txdx");
        String dwwz=request.getParameter("dwwz");
        String dzxx=request.getParameter("dzxx");
        String frdb=request.getParameter("frdb");
        String em=request.getParameter("em");
        String lxr = request.getParameter("lxr");
        String gd=request.getParameter("gd");
        String sj=request.getParameter("sj");
        String cz=request.getParameter("cz");
        String jgsx=request.getParameter("jgsx");
        String jgjj = request.getParameter("jgjj");
        String jsxq=request.getParameter("jsxq");
        int qsxqnf=parseInt(request.getParameter("qsxqnf"));
        int jzxqnf=parseInt(request.getParameter("jzxqnf"));
        String xqgs = request.getParameter("xqgs");
        String gjz=request.getParameter("gjz");
        String yjlx=request.getParameter("yjlx");
        String xkfl=request.getParameter("xkfl");
        String xqjsssly = request.getParameter("xqjsssly");
        String xqjsyyhy=request.getParameter("xqjsyyhy");
        String jsxqhzms=request.getParameter("jsxqhzms");
        String ntrzj=request.getParameter("ntrzj");
        String szdy=request.getParameter("szdy");
        Map<String,Object> map = new HashMap<>();
        map.put("code",Code);map.put("status",status);map.put("jgmc",jgmc);map.put("txdx",txdx);map.put("dwwz",dwwz);
        map.put("dzxx",dzxx);map.put("frdb",frdb);map.put("em",em);map.put("lxr",lxr);map.put("gd",gd);map.put("sj",sj);
        map.put("cz",cz);map.put("jgsx",jgsx);map.put("jgjj",jgjj);map.put("jsxq",jsxq);map.put("qsxqnf",qsxqnf);
        map.put("jzxqnf",jzxqnf);map.put("xqgs",xqgs);map.put("gjz",gjz);map.put("yjlx",yjlx);map.put("xkfl",xkfl);
        map.put("xqjsssly",xqjsssly);map.put("xqjsyyhy",xqjsyyhy);map.put("jsxqhzms",jsxqhzms);map.put("ntrzj",ntrzj);
        map.put("szdy",szdy);
        SqlSession sqlSession = MybatisUtils.getSession();
        FormMapper mapper = sqlSession.getMapper(FormMapper.class);
        int addform = mapper.addform(map);
        if(addform>0){
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
