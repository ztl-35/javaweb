<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<head>
<meta charset="UTF-8">
<title>查询公告信息</title>
	<!-- 视口设置 -->
	<meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no">
	<!-- 引入bootstrap的css文件 -->
	<link rel="stylesheet" type="text/css" href="js/bootstrap/css/bootstrap.css">
	<link rel="stylesheet"  type="text/css" href="styles/style.css">
	<link rel="stylesheet" type="text/css" href="styles/base.css">
	<link rel="stylesheet" href="styles/index.css">
	<!-- 引入jquery的js文件 -->
<script type="text/javascript" src="js/jquery.js"></script>
<!-- 引入angularjs的js文件 -->
<script type="text/javascript" src="js/angularjs/angular.js"></script>
<!-- 引入layer.js插件 -->
<script type="text/javascript" src="js/layer/layer.js"></script>
<!-- 引入全局配置js -->
<script type="text/javascript" src="js/global.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	/*首先判断session中是否有值，如果没有，就隐藏整个HTML的body标签，其实body内容已经显示，只不过设置为hidden  */
	document.getElementById("myBody").style.visibility="hidden";
	var myDiv = document.getElementsByClassName("myDiv");
	if(myDiv.length==3){
		myDiv[0].style.visibility="hidden";
		myDiv[1].style.visibility="hidden";
		myDiv[2].style.visibility="hidden";
	}
	else if(myDiv.length==2)
	{
		myDiv[0].style.visibility="hidden";
		myDiv[1].style.visibility="hidden";
	}
	else
	{
		myDiv[0].style.visibility="hidden";
	}
	
 	var user="<%=session.getAttribute("USER")%>"; 
　　 if(user=="null"){
 		alert("您好,请先在首页登录!");
 		location.href = "http://localhost:8080/Experience"
　　}else{
 		document.getElementById("myBody").style.visibility="visible";
 		if(myDiv.length==3){
 			myDiv[0].style.visibility="visible";
 			myDiv[1].style.visibility="visible";
 			myDiv[2].style.visibility="visible";
 		}
 		else if(myDiv.length==2)
 		{
 			myDiv[0].style.visibility="visible";
 			myDiv[1].style.visibility="visible";
 		}
 		else
 		{
 			myDiv[0].style.visibility="visible";
 	    }　　
 	}
   
	$(".delete").on('click',function(e){
		var noticeId=$(e.target).closest("span").find("label.noticeId").html();
        $.ajax({
            url:"http://localhost:8080/Experience/DelNoticeServlet",
            type:"get",
            datatype:"json",
            data:{
                "noticeId" : noticeId
            },
            success : function(msg){
                if(msg){
                    alert("删除成功");
                    location.href="http://localhost:8080/Experience/QueryNoticeServlet"
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
	  $(".updata").on("click",function(e){
		  var notice = $(e.target).closest("span");
		  
	    	var noticeId = notice.find("label.noticeId").html();
	    	var noticeHeader = notice.find("label.noticeHeader").html();
	    	var noticeContent = notice.find("label.noticeContent").html();
	        $("#UpdataDiv").css("display","block");
	        $("#container").css("display","none");
	        
	        
	        var updataDiv = $("#UpdataForm");
	        updataDiv.find("input[name='noticeId']").val(noticeId);
	        updataDiv.find("input[name='noticeHeader']").val(noticeHeader);
	        updataDiv.find("textarea[name='noticeContent']").val(noticeContent); 
	    });

	    $(".submit").on("click",function(e){
	    	/* alert($("textarea[name='noticeContent']").val()); */
	        $.ajax({
	            url:"http://localhost:8080/Experience/UpdateNoticeServlet",
	            type:"get",
	            datatype:"json",
	            data:{
	                "noticeId":$("input[name='noticeId']").val(),
	                "noticeHeader":$("input[name='noticeHeader']").val(),
	                "noticeContent":$("textarea[name='noticeContent']").val(),
	            },
	            success:function(msg){
	                if(msg){
	                    alert("更新成功");
	                    location.href="http://localhost:8080/Experience/QueryNoticeServlet"
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
		         <!-- 左侧区域开始 -->
		<div class="col-xs-3">
			<!-- <div class="col-xs-3 " id="myMenu">        -->
			<ul class="nav nav-tabs nav-stacked affix-top" data-spy="affix" data-offset-top="380" style=" margin-top:0px;">
					<li><a href="http://localhost:8080/Experience/">返回首页</a></li>
					<li class="active">
						<a href="http://localhost:8080/Experience/QueryNoticeServlet">公告</a>
					</li>
					<li>
						<a href="http://localhost:8080/Experience/QueryArticleServlet">经验帖</a>       
					</li>
					<li>
						<a href="http://localhost:8080/Experience/QueryQuestionAnswerServlet">问答区</a>
					</li>
					<li style="display: ${USER.user_flag == 1 ? 'none' : 'inline'};">
						<a href="AddNotice.html">发布公告</a>       
					</li>
			</ul>
	   </div>
	   
		<div class="container">	
			<div class="row">
					<!-- 左侧区域开始 -->
			  <div class="col-xs-9">
					<!-- 左侧区域结束 -->
					<!-- 右侧区域开始 -->
					<c:forEach items="${NOTICELIST}" var="p">
					<span>
					<div class="panel panel-primary">
						<div class="panel-heading">
							<!-- <h4 class="panel-title"></h4> -->
								<!-- 基本下拉菜单 -->
								<div class="dropdown">
									<button type="button" class="menu" data-toggle="dropdown" style = "display:inline">
										<label class="noticeId" style="display: none;">${p.noticeId}</label>
										
										<label class="noticeHeader">${p.noticeHeader}</label>
										<!-- <span class="caret"></span> -->
									</button>
								</div>
							
						</div>
						<div class="panel-collapse collapse in well myDiv">
							<div class="panel-body">
								<label class="noticeContent">${p.noticeContent}</label>
								<h5>${p.noticeDate}</h5>
							</div>
							<div style="display: ${USER.user_flag == 1 ? 'none' : 'inline'};">
								<input type="button" value="删除" class="btn btn-primary delete">
								<input type="button" value="编辑" class="btn btn-primary updata">
							</div>
						</div>
						
					</div>
					</span>
		 			</c:forEach>
				</div>
		
		
				
				<div class="text-center">
					<ul class="pagination">
						<li class="disablepage"><a href="http://localhost:8080/Experience/QueryNoticeServlet?currentNoticePage=1">首页</a></li>
						<li class="disablepage"><a href="http://localhost:8080/Experience/QueryNoticeServlet?currentNoticePage=${currentNoticePage==1?1:currentNoticePage-1}">&lt;&lt;上一页</a></li>
						<li><a>第${currentNoticePage }页/共${totalPage }页</a></li>
						<li class="nextPage"><a href="http://localhost:8080/Experience/QueryNoticeServlet?currentNoticePage=${currentNoticePage==totalPage?totalPage:currentNoticePage+1}">下一页&gt;&gt;</a></li>
						<li class="disablepage"><a href="http://localhost:8080/Experience/QueryNoticeServlet?currentNoticePage=${totalPage }">尾页</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="col-md-8" id="UpdataDiv" style=" display: none;">
		<h3 class="page-header">修改公告</h3>
		<!-- 表单开始 -->
		<form class="form-horizontal" id="UpdataForm">
			<div class="form-group">
				<label for="" class="col-sm-2">标题</label>
				<div class="col-sm-8">
				<input type="hidden" name="noticeId">
					<input type="text" placeholder="请输入标题" name="noticeHeader" class="form-control" 
					ng-model="noticeHeader">
				</div>				
			</div>	
			<div class="form-group">
				<label for="" class="col-sm-2">内容</label>
				<div class="col-sm-8" >
					<textarea placeholder="请输入" rows="8" name="noticeContent" class="content" ng-model="noticeContent" ></textarea>
				</div>				
			</div>

			<div class="form-group">				
				<div class="col-sm-8 col-sm-offset-2">
					<input type="button" value="修改公告" class="btn btn-primary submit">
				</div>				
			</div>
		</form>
		<!-- 表单结束 -->
	</div>

</body>
</html>