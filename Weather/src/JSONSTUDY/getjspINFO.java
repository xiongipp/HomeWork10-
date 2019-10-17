package JSONSTUDY;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/getjspINFO")
public class getjspINFO extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
	    request.setCharacterEncoding("UTF-8");
	    PrintWriter out = response.getWriter();
		String cityname;
		cityname=request.getParameter("cityname");
		//得到xml字符串;
		String xml=Test.getTodayT(cityname);
		Map<Integer,Map<String,String>> test;
		test=getSearchinfoUtils.getWeatherINFO(xml);
		
		Map<String, String> cityInfMap = test.get(0);
        System.out.println("城市名称:"+cityInfMap.get("cityName"));
        Map<String, String> day1 = test.get(1);
        System.out.println("今天是："+day1.get("ymd")+day1.get("week"));
        System.out.println("天气情况：");
        System.out.println("最高温度:"+day1.get("high"));
        System.out.println("最低温度:"+day1.get("low"));
        System.out.println("温馨提示："+day1.get("notice"));
		//开始拼接网页。。
		out.println("<HTML>");		
		out.println("  <BODY>");
		out.print("<label>今天是： "); out.print(day1.get("ymd")+day1.get("week"));out.print("</label>");
		out.print("<br>");
		out.print("<label>查询城市为： "); out.print(cityInfMap.get("cityName"));out.print("</label>");
		out.print("<br>");
		out.print("<label>最高温度: "); out.print(day1.get("high"));out.print("</label>");
		out.print("<br>");
		out.print("<label>最低温度: "); out.print(day1.get("low"));out.print("</label>");
		out.print("<br>");
		out.print("<label>温馨提示： "); out.print(day1.get("notice"));out.print("</label>");
		out.println("  <BODY>");		
		out.println("</HTML>");
		out.flush();
		out.close();
		doGet(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}
