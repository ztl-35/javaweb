<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>考研参考书</title>
<script type="text/javascript" src="js/jquery.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="styles/base.css" />
<link rel="stylesheet" href="styles/course.css" />
<link rel="stylesheet" type="text/css" href="styles/loginstyle.css">
<script src="js/bootstrap.js"></script>
<script src="scripts/base.js"></script>
<script src="scripts/index.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	/*首先判断session中是否有值，如果没有，就隐藏整个HTML的body标签，其实body内容已经显示，只不过设置为hidden  */
	document.getElementById("myBody").style.visibility="hidden";
	var user="<%=session.getAttribute("USER")%>"; 
   　　if(user=="null"){
	   alert("您好,请先在首页登录!");
	   location.href = "http://localhost:8080/Experience"
   　　}else{
	   document.getElementById("myBody").style.visibility="visible";
   　　}
   
	$(".delete").on('click',function(e){
		var bookId = $(e.target).closest("tr").find("#bookId").text();
        /* alert(bookId); */
        $.ajax({
            url:"http://localhost:8080/Experience/DelBookServlet",
            type:"get",
            datatype:"json",
            data:{
                "bookId" : bookId
            },
            success : function(msg){
                if(msg){
                    alert("删除成功");
                    location.href="http://localhost:8080/Experience/QueryBookServlet"
                }
                else{
                    alert("删除失败");
                }
            },
            error:function(msg){  
                alert('ajax请求出现错误...');  
            }
        });
	});
	
	var oldBook;
    $(".updata").on("click",function(e){
        var book = $(e.target).closest("tr");
        oldBook = book;
        $("#UpdataDiv").css("display","block");
        $("#container").css("display","none");
        
        var updataDiv = $("#UpdataForm");
        updataDiv.find("input[name='bookId']").val(book.find("#bookId").text());
        updataDiv.find("input[name='bookName']").val(book.find("#bookName").text());
        updataDiv.find("input[name='bookWriter']").val(book.find("#bookWriter").text());
        updataDiv.find("input[name='bookRank']").val(book.find("#bookRank").text());
        updataDiv.find("input[name='bookClassification']").val(book.find("#bookClassification").text());
        updataDiv.find("input[name='bookDescription']").val(book.find("#bookDescription").text());

     /*    $(".subjectId").val(book.find("#subjectId").text()); */
        updataDiv.find("select[name='subjectId']").val(book.find("#subjectId").text());
        
        
    });

    $(".submit").on("click",function(e){
        var book = $(e.target).closest("#UpdataForm");
        $.ajax({
            url:"http://localhost:8080/Experience/UpdateBookServlet",
            type:"get",
            datatype:"json",
            data:{
                "bookId":$("input[name='bookId']").val(),
                "bookName":$("input[name='bookName']").val(),
                "bookWriter":$("input[name='bookWriter']").val(),
                "bookRank":$("input[name='bookRank']").val(),
                "bookClassification":$("input[name='bookClassification']").val(),
                "bookDescription":$("input[name='bookDescription']").val(),
                "subjectId":$("select[name='subjectId']").val(),
            },
            success:function(msg){
                if(msg){
                    oldBook.find("#bookId").text(book.find("input[name='bookId']").val());
                    oldBook.find("#bookName").text(book.find("input[name='bookName']").val());
                    oldBook.find("#bookWriter").text(book.find("input[name='bookWriter']").val());
                    oldBook.find("#bookRank").text(book.find("input[name='bookRank']").val());
                    oldBook.find("#bookClassification").text(book.find("input[name='bookClassification']").val());
                    oldBook.find("#bookDescription").text(book.find("input[name='bookDescription']").val());
                    oldBook.find("#subjectId").text(book.find("select[name='subjectId']").val());
                    $("#UpdataDiv").css("display","none");
                    alert("更新成功");
                    location.href = "http://localhost:8080/Experience/QueryBookServlet?subjectId="+book.find("select[name='subjectId']").val();
                }
                else{
                    alert("更新失败");
                }
            }
        });
    }); 
});
</script>
</head>
<body id="myBody">
<div class="container" id="container">
	<!-- 全部课程start -->
	<div class="route clearfix">
		<div class="teacher"></div>
		<a href="http://localhost:8080/Experience/">返回首页</a>
		<h1>全部参考书</h1>
		
		<br>
		<a href="http://localhost:8080/Experience/AddBook.html" style="display: ${USER.user_flag == 1 ? 'none' : ''};">新增参考书</a>
		<hr />
		<div class="dir first clearfix">
		<h4>科目：</h4>
			<ul>
				<li class="all current"><a href="http://localhost:8080/Experience/QueryBookServlet?subjectId=100">全部课程</a></li>
				<li id="math"><a href="http://localhost:8080/Experience/QueryBookServlet?subjectId=1">数学</a></li>
				<li id="English"><a href="http://localhost:8080/Experience/QueryBookServlet?subjectId=2">英语</a></li>
				<li id="politics"><a href="http://localhost:8080/Experience/QueryBookServlet?subjectId=3">政治</a></li>
				<li> 课程类型：1代表数学，2代表英语，3代表政治</li>

			</ul>
		</div>
		<hr />
	</div>
	<p />
<div class="col-xs-8">	
<table  class="table table-striped table-bordered">
			<tr>
				<th >id号</th>
				<th >书名</th>
				<th >作者</th>
				<th >难度</th>
				<th >类型</th>
				<th >描述</th>
				<th >课程</th>
				<th style="display: ${USER.user_flag == 1 ? 'none' : ''};">操作</th>
			</tr>
	<c:forEach items="${BOOKLIST}" var="p">
			<tr>
				<td id="bookId">${p.bookId}</td>
				<td id="bookName">${p.bookName}</td>
				<td id="bookWriter">${p.bookWriter}</td>
				<td id="bookRank">${p.bookRank}</td>
				<td id="bookClassification">${p.bookClassification}</td>
				<td id="bookDescription">${p.bookDescription}</td>
				<td id="subjectId">${p.subjectId}</td>
				
				<td style="display: ${USER.user_flag == 1 ? 'none' : ''};">
				
				<div style="width:50%;padding:0;margin:0;float:left;box-sizing:border-box;">
					<input type="button" value="修改" class="updata" style="display:inline;">
					</div>
					<div style="width:50%;padding:0;margin:0;float:left;box-sizing:border-box;">
				    <input type="button" value="删除" class="delete">
				    </div>
				</td>
			</tr>
	</c:forEach>	
</table>


<div class="text-center">
	<ul class="pagination">
		
		<li class="disablepage"><a href="http://localhost:8080/Experience/QueryBookServlet?currentPage=1">首页</a></li>
		<li class="disablepage"><a href="http://localhost:8080/Experience/QueryBookServlet?currentPage=${currentPage==1?1:currentPage-1}">&lt;&lt;上一页</a></li>
		<li><a>第${currentPage }页/共${totalPage }页</a></li>
		<li class="nextPage"><a href="http://localhost:8080/Experience/QueryBookServlet?currentPage=${currentPage==totalPage?totalPage:currentPage+1}">下一页&gt;&gt;</a></li>
		<li class="disablepage"><a href="http://localhost:8080/Experience/QueryBookServlet?currentPage=${totalPage }">尾页</a></li>
	</ul>
</div>
</div>
</div>
<!-- 修改书本信息 -->
<div class="container" style=" display: none;" id="UpdataDiv">
	<h1>修改参考书信息</h1>
	<br>
				<form class="form-horizontal" action="Updata" method="post" id="UpdataForm">
				<div class="form-group">
					<div class="col-sm-8">
						<input type="hidden" name="bookId"><br>
					</div>
				</div>
					<div class="form-group">
						<label for="" class="col-sm-2">参考书名称</label>
						<div class="col-sm-8">
							<input type="text" placeholder="请输入书名" name="bookName" class="form-control" ng-model="bookName">
						</div>				
					</div>
					<div class="form-group">
						<label for="" class="col-sm-2">作者</label>
						<div class="col-sm-8">
							<input type="text" placeholder="请输入作者" name="bookWriter" class="form-control" ng-model="bookWriter">
						</div>				
					</div>	
					<div class="form-group">
						<label for="" class="col-sm-2">属性</label>
						<div class="col-sm-8">
							<input type="text" placeholder="请输入参考书属性" name="bookRank" class="form-control" ng-model="bookRank">
						</div>				
					</div>	
					<div class="form-group">
						<label for="" class="col-sm-2">类别</label>
						<div class="col-sm-8">
							<input type="text" placeholder="请输入参考书类别" name="bookClassification" class="form-control" ng-model="bookClassification">
						</div>				
					</div>	
					<div class="form-group">
						<label for="" class="col-sm-2">描述</label>
						<div class="col-sm-8">
							<input type="text" placeholder="请输入参考书简介" name="bookDescription" class="form-control" ng-model="bookDescription">
						</div>				
					</div>
					<div class="form-group">
						<label for="" class="col-sm-2">选择所属科目</label>
						<div class="col-sm-8">
							<select id="subjectId" name="subjectId" class="subjectId">	
								<option value ="1">数学</option>
 							    <option value ="2">英语</option>
 							    <option value ="3">政治</option>
 							<!--     <option value ="4">专业课</option> --> 
						</select>	
						</div>	
					</div>	
					<div class="form-group">				
						<div class="col-sm-8 col-sm-offset-2">
							<input type="button" value="修改参考书信息" class="submit"">
						</div>				
					</div>
</form>
      <!-- <form action="Updata" method="post" id="UpdataForm" class="form-horizontal">
	            书本ID：：<input type="text" name="bookId"><br>
	            书本名：<input type="text" name="bookName"><br>
	            作者：<input type="text" name="bookWriter"><br>
	            书本难度：<input type="text" name="bookRank"><br>
	            书本类型：<input type="text" name="bookClassification"><br>
	            书本描述：<input type="text" name="bookDescription"><br>
	            选择类目：<input type="text" name="subjectId"><br>
          <input type="button" value="提交" class="submit">
      </form> -->
</div>


</body>
</html>