<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/album/addAlbum">
<table id="tb">
<tr><td>相册名称</td><td><input type="text" name="album.albumName"></td></tr>
<tr><td>相册</td><td><input type="file" name="image"></td></tr>
<tr><td colspan="2">
<div align="center">
  <input type="submit" value="创建">
</div></td></tr>
</table>
</form>
</body>
</html>