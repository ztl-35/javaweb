<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>经验帖</title>
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

	function changeImage(value){
		var pic = document.getElementById("image"+value);
		pic.src = "imgs/timg2.jpg";
		}
	function changeImage2(value){
		var pic = document.getElementById("image2"+value);
		pic.src = "imgs/star2.png";
		}
$(document).ready(function(){
	$(".delete").on('click',function(e){
		var articleId=$(e.target).closest("span").find("label.articleId").html();
		/* alert(articleId); */
        $.ajax({
            url:"http://localhost:8080/Experience/DelArticleServlet",
            type:"get",
            datatype:"json",
            data:{
                "articleId" : articleId
            },
            success : function(msg){
                if(msg){
                    alert("删除成功");
                    location.href="http://localhost:8080/Experience/QueryArticleServlet"
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
		  var article = $(e.target).closest("span");
		  
	    	var articleId = article.find("label.articleId").html();
	    	var articleTitle = article.find("label.articleTitle").html();
	    	var articleContent = article.find("label.articleContent").html();
/* 	    	var noticeId = $("label.noticeId").html();
	    	var noticeHeader = $("label.noticeHeader").html();
	    	var noticeContent = $("label.noticeContent").html(); */
	        $("#UpdataDiv").css("display","block");
	        $("#container").css("display","none");
	        
	        
	        var updataDiv = $("#UpdataForm");
	        updataDiv.find("input[name='articleId']").val(articleId);
	        updataDiv.find("input[name='articleTitle']").val(articleTitle);
	        updataDiv.find("textarea[name='articleContent']").val(articleContent); 
	    });

	    $(".submit").on("click",function(e){
	    	/* alert($("textarea[name='articleContent']").val()); */
	        $.ajax({
	            url:"http://localhost:8080/Experience/UpdateArticleServlet",
	            type:"get",
	            datatype:"json",
	            data:{
	                "articleId":$("input[name='articleId']").val(),
	                "articleTitle":$("input[name='articleTitle']").val(),
	                "articleContent":$("textarea[name='articleContent']").val(),
	            },
	            success:function(msg){
	                if(msg){
	                    alert("更新成功");
	                    location.href="http://localhost:8080/Experience/QueryArticleServlet"
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
<body>
	<!-- 网页头部开始 -->
	<div ng-include="'inc/header.jsp'"></div>
	<!-- 网页头部结束 -->
           
<div class="container" id="container">	
	         <!-- 左侧区域开始 -->
	<div class="col-xs-3">
		<!-- <div class="col-xs-3 " id="myMenu">        -->
		<ul class="nav nav-tabs nav-stacked affix-top" data-spy="affix" data-offset-top="380" style=" margin-top:0px;">
				<li><a href="http://localhost:8080/Experience/">返回首页</a></li>
				<li >
					<a href="http://localhost:8080/Experience/QueryNoticeServlet">公告</a>
				</li>
				<li class="active">
					<a href="http://localhost:8080/Experience/QueryArticleServlet">经验帖</a>       
				</li>
				<li>
					<a href="http://localhost:8080/Experience/QueryQuestionAnswerServlet">问答区</a>
				</li>
				<li>
					<a href="AddArticle.html">发布经验</a>       
				</li>
		</ul>
   </div>
   
<!-- 经验贴  -->
<div class="container">	
	<div class="row">
	  <div class="col-xs-9">
<c:forEach items="${ARICLELIST}" var="p">
	<!-- 右侧区域开始 -->
	<span>
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h4 class="panel-title">
				<!-- 基本下拉菜单 -->
				<div class="dropdown">
					<button type="button" class="menu" data-toggle="dropdown" style = "display:inline">
						<label class="articleId" style="display: none;">${p.articleId}</label>
						<label class="articleTitle">${p.articleTitle}</label>
						<span class="caret"></span>
					</button>
				</div>
			</h4>
		</div>
		<div class="panel-collapse collapse in well" >
			<div class="panel-body">
				<label class="articleContent">${p.articleContent}</label>
				<h5>${p.articleDate}</h5>
			</div>
			<div class="panel-body">
				<div align="left" style="float: left">
					<a href="AddComment.jsp?currentArticlePage=${currentArticlePage}&articleId=${p.articleId }"><input type="button" value="发表评论" class="btn btn-primary addComment"></a>
					<a href="http://localhost:8080/Experience/QueryArticleServlet?currentArticlePage=${currentArticlePage}&articleId=${p.articleId }"><input type="button" value="展开评论" class="btn btn-primary"></a>
					<a href="http://localhost:8080/Experience/QueryArticleServlet?currentArticlePage=${currentArticlePage}"><input type="button" value="收起评论" class="btn btn-primary"></a>
				</div>
				<div align="right" style="float: right;">
					<input type="image" src="imgs/star.png" id="image2${p.articleId}" width="30px" height="30px" onclick="changeImage2(${p.articleId})">
				</div>
				<div align="right" style="float: right; display: ${USER.user_flag == 1 ? 'none' : ''};">
					<input type="button" value="编辑" class="btn btn-primary updata">
					<input type="button" value="删除" class="btn btn-primary delete">
					&nbsp;&nbsp;&nbsp;&nbsp;
				</div>
				<div align="right" style="float: right;">
					<input type="image" src="imgs/timg.jpg" id="image${p.articleId}" width="30px" height="30px" onclick="changeImage(${p.articleId})">
				&nbsp;&nbsp;&nbsp;&nbsp;
				</div>
				
			</div>
		</div>
		
<!-- 评论列表 -->
		<div class="${p.articleId }Comment">
			<c:forEach items="${COMMENTLIST }" var="clist">
			
			<span class="panel-collapse collapse in well" style="display: ${clist.articleId == p.articleId ? '' : 'none'};">
				<a href="http://localhost:8080/Experience/DelCommentServlet?currentArticlePage=${currentArticlePage}&articleId=${p.articleId }&commentId=${clist.commentId}">
					<input type="hidden" class="commentId" value="${clist.commentId}">
				</a>
					评论内容：<label class="commentContent">${clist.commentContent}</label><br><hr>
					评论人：<label class="userName">${clist.userName}</label>
					评论时间：<label class="commentDate">${clist.commentDate}</label>
			<span style="float: right; display: ${USER.user_name == clist.userName ? '' : (USER.user_flag == 1 ? 'none' : '')};">
				<a href="http://localhost:8080/Experience/DelCommentServlet?currentArticlePage=${currentArticlePage}&articleId=${p.articleId }&commentId=${clist.commentId}">
					<input type="button" value="删除" class="btn btn-primary deleteComment">
				</a>
			</span>
			</span>
			</c:forEach>
		</div>
<!-- 评论表结束 -->

	</div>
	</span>
 </c:forEach>
		</div>
	</div>
</div>
<!-- 分页 -->
<div class="text-center">
	<ul class="pagination">
		<li class="disablepage"><a href="http://localhost:8080/Experience/QueryArticleServlet?currentArticlePage=1">首页</a></li>
		<li class="disablepage"><a href="http://localhost:8080/Experience/QueryArticleServlet?currentArticlePage=${currentArticlePage==1?1:currentArticlePage-1}">&lt;&lt;上一页</a></li>
		<li><a>第${currentArticlePage }页/共${totalPage }页</a></li>
		<li class="nextPage"><a href="http://localhost:8080/Experience/QueryArticleServlet?currentArticlePage=${currentArticlePage==totalPage?totalPage:currentArticlePage+1}">下一页&gt;&gt;</a></li>
		<li class="disablepage"><a href="http://localhost:8080/Experience/QueryArticleServlet?currentArticlePage=${totalPage }">尾页</a></li>
	</ul>
</div>
<!-- 分页结束 -->

<!-- 修改经验贴 -->
</div>
			<div class="col-md-8" id="UpdataDiv" style=" display: none;">
				<h3 class="page-header">修改经验</h3>
				<!-- 表单开始 -->
				<form class="form-horizontal" id="UpdataForm">
					<div class="form-group">
						<label for="" class="col-sm-2">标题</label>
						<div class="col-sm-8">
						<input type="hidden" name="articleId">
							<input type="text" placeholder="请输入标题" name="articleTitle" class="form-control" 
							ng-model="articleTitle">
						</div>
					</div>	
					<div class="form-group">
						<label for="" class="col-sm-2">内容</label>
						<div class="col-sm-8" >
							<textarea placeholder="请输入" rows="8" name="articleContent" class="content" ng-model="articleContent" ></textarea>
						</div>				
					</div>

					<div class="form-group">				
						<div class="col-sm-8 col-sm-offset-2">
							<input type="button" value="修改经验" class="btn btn-primary submit">
						</div>				
					</div>
				</form>
				<!-- 表单结束 -->
			</div>
</body>
</html>