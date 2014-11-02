package com.util;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;

/**
 * 通信类
 * 把后台的数据已json的方式发往前台
 */
public class SendData {

	public static void send(Object obj) {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
		    /*设置字符集*/
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json;charset=utf-8");
			response.setHeader("pragma", "no-cache");
			response.setHeader("cache-control", "no-cache");
			PrintWriter out = response.getWriter();
			// JsonConfig jsonConfig = new JsonConfig(); // 建立配置文件
			// jsonConfig.setIgnoreDefaultExcludes(false); // 设置默认忽略
			// jsonConfig.setExcludes(new String[] { "courses","classes" });
			/* 将查询结果转换为JSON数据格式 */
			JSONArray json = null;
			json = JSONArray.fromObject(obj);
			// System.out.println(json);
			/* 将数据返回到客户端 */
			out.println(json.toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
