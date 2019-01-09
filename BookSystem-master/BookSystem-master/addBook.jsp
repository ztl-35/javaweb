<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
table#tb_addBook {  
    font-family: verdana,arial,sans-serif;  
    font-size:11px;  
    color:#333333;  
    border-width: 1px;  
    border-color: #999999;  
    border-collapse: collapse;  
}
table#tb_addBook th {  
    background:#b5cfd2 url('cell-blue.jpg');  
    border-width: 1px;
    padding: 8px;  
    font-family:黑体;
	font-size:12px;
    border-style: solid;  
    border-color: #999999;  
}
table#tb_addBook td {
    background:#dcddc0 url('cell-grey.jpg');
    border-width: 1px;
    padding: 8px;
	font-family:楷体;
	font-size:12px;
    border-style: solid;
    border-color: #999999;  
}
</style>
<script type="text/javascript" src="../Jquery/jquery-1.11.1.js"></script>
<script type="text/javascript">
$(function(){
	$("intut[type='submit']").click(function(){
		var bookName=document.getElementsByName("bookName").item(0).value;
		var str = bookName.replace(/\s+/g,"");
		if(str.length==0){
			//选中这些空格符，并且光标定位
			document.getElementsByName("bookName").item(0).setSelectionRange(0, document.getElementsByName("bookName").item(0).length-1);
			document.getElementsByName("bookName").item(0).focus();
			alert("输入的图书名不能为空!");
			return false;
		}
		var re = /^[0-9]+.?[0-9]*$/;
		if(!re.test(document.getElementsByName("bookPrice").item(0).value)){
			alert("价格必须为数字!");
			document.getElementsByName("bookPrice").item(0).select();
			return false;
		}
		var Author=document.getElementsByName("Author").item(0).value;
		var str = Author.replace(/\s+/g,"");
		if(str.length==0){
			//选中这些空格符，并且光标定位
			document.getElementsByName("Author").item(0).setSelectionRange(0, document.getElementsByName("Author").item(0).length-1);
			document.getElementsByName("Author").item(0).focus();
			alert("输入的图书作者不能为空!");
			return false;
		}
		var bookPublisher=document.getElementsByName("bookPublisher").item(0).value;
		var str = bookName.replace(/\s+/g,"");
		if(str.length==0){
			//选中这些空格符，并且光标定位
			document.getElementsByName("bookPublisher").item(0).setSelectionRange(0, document.getElementsByName("bookPublisher").item(0).length-1);
			document.getElementsByName("bookPublisher").item(0).focus();
			alert("输入的图书生产商不能为空!");
			return false;
		}
	});
});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post" action="addBookServlet">
<table id="tb_addBook">
<tr><th colspan="2">增加图书信息</th></tr>
<tr><td>图书名称</td><td><input type="text" name="bookName"></td></tr>
<tr><td>图书作者</td><td><input type="text" name="Author"></td></tr>
<tr><td>图书价格</td><td><input type="text" name="bookPrice"></td></tr>
<tr><td>图书生产商</td><td><input type="text" name="bookPublisher"></td></tr>
<tr><td colspan="2"><div align="center"><input type="submit" value="提交信息"></div></td></tr>
</table>
</form>
</body>
</html>