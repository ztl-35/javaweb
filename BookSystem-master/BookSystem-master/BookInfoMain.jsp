<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="BookInfoSystem.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
table#tb {  
    font-family: verdana,arial,sans-serif;  
    font-size:11px;  
    color:#333333;  
    border-width: 1px;  
    border-color: #999999;  
    border-collapse: collapse;  
}  
table#tb th {  
    background:#b5cfd2 url('cell-blue.jpg');  
    border-width: 1px;
    padding: 8px;  
    border-style: solid;  
    border-color: #999999;  
}  
table#tb td {
    background:#dcddc0 url('cell-grey.jpg');
    border-width: 1px;
    padding: 8px;
	font-family:楷体;
	font-size:12px;
    border-style: solid;
    border-color: #999999;  
}
#p1{
	margin-left:150px;
}
#btndelete{
	margin-right:20px;
}
</style>
<script type="text/javascript" src="Jquery/jquery-1.11.1.js"></script>
<script type="text/javascript">
$(function(){
	$("input[type='checkbox'][name='selectIt']:first").click(function(){
		$("input[type='checkbox'][name='selectIt']").not(":first").prop("checked",$(this).prop("checked"));
	});
/* 	$("#selectIt1").click(function() {
         $("input[type='checkbox'][name='selectIt']").each(function(index, element) {
             $("input[name='selectIt']").eq(index).attr("checked",$("input:[id='selectIt1']:checked").val()); 
        });
    });
*/
	$("input[type='button'][name='modifyIt']").click(function(){
		var id = $(this).parent().parent().children().eq(0).children().eq(0).val();
		location.href = "modifyBook.jsp?id="+id;
	});
	$("#btndelete").click(function(){
		var ids="";
		var i=0;
		$("input[type='checkbox'][name='selectIt']").each(function(index, element) {
           // if(index>0){
				if($(this).prop("checked")){
					var id = $(this).parent().parent().children().eq(0).children().eq(0).val();
					ids +=id;
					i++;
					if(!$(this).is(":last")){
						ids+=",";
					}
				}
			//}
        });
		var answer=confirm("你确定要删除这"+i+"本图书吗？");
		if(!answer){
			return;
		}
		location.href = "delete?ids="+ids;
	});
	$("#btnadd").click(function(){
		location.href = "addBook.jsp";
	});
	$("#btnsearch").click(function(){
		location.href = "searchBook.jsp";
	});
});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书信息管理系统</title>
</head>
<body>
<table id="tb">
<tr><th>序号</th><th>图书名称</th><th>作者</th><th>价格</th><th>出版社</th><th>修改</th><th><input type="checkbox" id="selectIt1"/></th></tr>
<%
	BookDAO bookDao = new BookDAO();
	ArrayList<Book> arrayList = new ArrayList<Book>();
	arrayList = bookDao.getBooks();
	for(int i=0;i<arrayList.size();i++){
		Book book = arrayList.get(i);
%>
<tr><td><%=i+1%><input type="hidden" name="id" value=<%=book.getId()%>></td><td><%=book.getBookName() %></td><td><%=book.getAuthor() %></td><td><%=book.getBookPrice() %></td><td><%=book.getBookPublisher() %></td><td><input type="button" name="modifyIt" value="修改"></td><td><input type="checkbox" name="selectIt"/></td></tr>
<%
	}
%>

</table>
<p id="p1">
<input type="button" value="删除图书" id="btndelete"/>
<input type="button" value="添加图书" id="btnadd"/>
<input type="button" value="搜索图书" id="btnsearch"/>
</p>
</body>
</html>