<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="Myvisit.*,myuser.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="Link_biaoge.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<p style="margin-left:200px">
在线用户信息
</p>
<a href="Main.jsp">主页面</a>&nbsp;&nbsp;<a href="Visitor.jsp">访客页面</a>&nbsp;&nbsp;<a href="Login.jsp">登录页面</a>&nbsp;&nbsp;<a href="History.jsp">历史访客页面</a>
<br></br>
<table id="tb">
  <tr>
    <th width="104">ID</th>
    <th width="116">用户名</th>
    <th width="165">来访时间</th>
    <th width="164">IP地址</th>
    <th width="124">URL</th>
  </tr>
<%
	HashMap<String,Visitor> map = (HashMap<String,Visitor>)application.getAttribute("ONLINE_USER");
	System.out.println(map);
	Set<String> set = map.keySet();
	Iterator<String> it = set.iterator();//我先看看
	int i=0;
	while(it.hasNext()){
		String key = it.next();
		Visitor visitor = map.get(key);
		Date date = visitor.getVisitTime();
		String url = "";
		if(visitor.getComefrom()!=null){
			url = visitor.getComefrom();
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String vTime = simpleDateFormat.format(date);
		i++;
		userDAO uDao = new userDAO();
		String username = uDao.getNameById(visitor.getUserID());
%>
  <tr>
    <td><%=i %></td>
    <td><%=username%></td>
    <td><%=vTime %></td>
    <td><%=visitor.getIp() %></td>
    <td><%=url %></td>
  </tr>
<%
	}
%>
</table>
</body>
</html>