<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
		var questionId=$(e.target).closest("span").find("label.questionId").html();
        $.ajax({
            url:"http://localhost:8080/Experience/DelQuestionAnswerServlet",
            type:"get",
            datatype:"json",
            data:{
                "questionId" : questionId
            },
            success : function(msg){
                if(msg){
                    alert("删除成功");
                    location.href="http://localhost:8080/Experience/QueryQuestionAnswerServlet"
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
		  	var question = $(e.target).closest("span");
		  
	    	var questionId = question.find("label.questionId").html();
	    	var questionOwner = question.find("label.questionOwner").html();
	    	var questionContent = question.find("label.questionContent").html();
	    	/* alert(questionId+questionOwner+questionContent); */
	    	
	        $("#UpdataDiv").css("display","block");
	        $("#container").css("display","none");
	        
	        
	        var updataDiv = $("#UpdataForm");
	        updataDiv.find("input[name='questionId']").val(questionId);
	        updataDiv.find("input[name='questionOwner']").val(questionOwner);
	        updataDiv.find("textarea[name='questionContent']").val(questionContent); 
	    });

	    $(".submit").on("click",function(e){
	    	/* alert($("textarea[name='questionContent']").val()); */
	        $.ajax({
	            url:"http://localhost:8080/Experience/UpdateQuestionAnswerServlet",
	            type:"get",
	            datatype:"json",
	            data:{
	                "questionId":$("input[name='questionId']").val(),
	                "questionOwner":$("input[name='questionOwner']").val(),
	                "questionContent":$("textarea[name='questionContent']").val(),
	            },
	            success:function(msg){
	                if(msg){
	                    alert("更新成功");
	                    location.href="http://localhost:8080/Experience/QueryQuestionAnswerServlet"
	                }
	                else{
	                    alert("更新失败");
	                }
	            }
	        });
	    }); 
	 
});
</script>
<head>
<meta charset="UTF-8">
<title>问答区</title>
	<!-- 视口设置 -->
	<meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no">
	<!-- 引入bootstrap的css文件 -->
	<link rel="stylesheet" type="text/css" href="js/bootstrap/css/bootstrap.css">
	<link rel="stylesheet"  type="text/css" href="styles/style.css">
	<link rel="stylesheet" type="text/css" href="styles/base.css">
	<link rel="stylesheet" href="styles/index.css">

</head>
<body>
<div class="container" id="container">	
   <div class="col-xs-3">
		<!-- <div class="col-xs-3 " id="myMenu">        -->
		<ul class="nav nav-tabs nav-stacked affix-top" data-spy="affix" data-offset-top="380" style=" margin-top:0px;">
				<li><a href="http://localhost:8080/Experience/">返回首页</a></li>
				<li >
					<a href="http://localhost:8080/Experience/QueryNoticeServlet">公告</a>
				</li>
				<li>
					<a href="http://localhost:8080/Experience/QueryArticleServlet">经验帖</a>       
				</li>
				<li class="active">
					<a href="http://localhost:8080/Experience/QueryQuestionAnswerServlet">问答区</a>
				</li>
				<li>
					<a href="AddQuestion.html">发布提问</a>       
				</li>
		</ul>
   </div>
   
	<div class="container">	
		<div class="row">
				<!-- 左侧区域开始 -->
		  <div class="col-xs-9">
				<!-- 左侧区域结束 -->
				<!-- 右侧区域开始 -->
<c:forEach items="${QALIST}" var="p">
			<!-- 左侧区域结束 -->
			<!-- 右侧区域开始 -->
			<span>
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h4 class="panel-title">
						<!-- 基本下拉菜单 -->
						<div class="dropdown">
							<button type="button" class="menu" data-toggle="dropdown" style = "display:inline">
								<label class="questionId" style="display: none;">${p.questionId}</label>
								提问人：<label class="questionOwner">${p.questionOwner}</label>
								<span class="caret"></span>
							</button>
						</div>
					</h4>
				</div>
				<div class="panel-collapse collapse in well" >
					<div class="panel-body">
						提问内容：<label class="questionContent">${p.questionContent}</label>&nbsp;&nbsp;&nbsp;&nbsp;
						提问时间：<label>${p.questionDate}</label>
					</div>
					<div class="panel-body">
						<div align="left" style="float: left">
							<a href="AddQuestionAnswer.jsp?questionId=${p.questionId}&currentQuestionAnswerPage=${currentQuestionAnswerPage}"><input type="button" value="回复提问" class="btn btn-primary addComment"></a>
							<a href="http://localhost:8080/Experience/QueryQuestionAnswerServlet?currentQuestionAnswerPage=${currentQuestionAnswerPage}&questionId=${p.questionId }"><input type="button" value="展开回复" class="btn btn-primary"></a>
							<a href="http://localhost:8080/Experience/QueryQuestionAnswerServlet?currentQuestionAnswerPage=${currentQuestionAnswerPage}"><input type="button" value="收起回复" class="btn btn-primary"></a>
						</div>
				<div align="right" style="float: right;">
					<input type="image" src="imgs/star.png" id="image2${p.questionId}" width="30px" height="30px" onclick="changeImage2(${p.questionId})">
				</div>						
						<div align="right" style="float: right; display: ${USER.user_flag == 1 ? 'none' : ''};">
							<input type="button" value="编辑" class="btn btn-primary updata">
							<input type="button" value="删除" class="btn btn-primary delete">
							&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
						
				<div align="right" style="float: right;">
					<input type="image" src="imgs/timg.jpg" id="image${p.questionId}" width="30px" height="30px" onclick="changeImage(${p.questionId})">
				&nbsp;&nbsp;&nbsp;&nbsp;
				</div>						
						
					</div>
				</div>
				<div class="${p.questionId }Comment">
					<c:forEach items="${ANSWERLIST }" var="answerlist">
					
				<span class="panel-collapse collapse in well" style="display: ${answerlist.questionId == p.questionId ? '' : 'none'};">
						回复内容：<label class="answerContent">${answerlist.answerContent}</label><br><hr>
						回复人：<label class="userName">${answerlist.userName}</label>
						回复时间：<label class="answerDate">${answerlist.answerDate}</label>
					<span style="float: right; display: ${USER.user_name == answerlist.userName ? '' : (USER.user_flag == 1 ? 'none' : '')};">
						<a href="http://localhost:8080/Experience/DelAnswerServlet?currentQuestionAnswerPage=${currentQuestionAnswerPage}&questionId=${p.questionId}&answerId=${answerlist.answerId}">
							<input type="button" value="删除" class="btn btn-primary deleteComment">
						</a>
					</span>
				</span>
					</c:forEach>
				</div>
				
			</div>
			</span>
 </c:forEach>




<!-- 设置下一页 -->
<div class="text-center">
	<ul class="pagination">
		<li class="disablepage"><a href="http://localhost:8080/Experience/QueryQuestionAnswerServlet?currentQuestionAnswerPage=1">首页</a></li>
		<li class="disablepage"><a href="http://localhost:8080/Experience/QueryQuestionAnswerServlet?currentQuestionAnswerPage=${currentQuestionAnswerPage==1?1:currentQuestionAnswerPage-1}">&lt;&lt;上一页</a></li>
		<li><a>第${currentQuestionAnswerPage }页/共${totalPage }页</a></li>
		<li class="nextPage"><a href="http://localhost:8080/Experience/QueryQuestionAnswerServlet?currentQuestionAnswerPage=${currentQuestionAnswerPage==totalPage?totalPage:currentQuestionAnswerPage+1}">下一页&gt;&gt;</a></li>
		<li class="disablepage"><a href="http://localhost:8080/Experience/QueryQuestionAnswerServlet?currentQuestionAnswerPage=${totalPage }">尾页</a></li>
	</ul>
</div>

		  </div>
		</div>
	</div>
</div>


<!-- 修改公告 -->
 <div class="col-md-8" id="UpdataDiv" style=" display: none;">
	<h3 class="page-header">修改提问</h3>
	<form class="form-horizontal" id="UpdataForm">
		<div class="form-group">
			<label for="" class="col-sm-2">提问者</label>
			<div class="col-sm-8">
			<input type="hidden" name="questionId">
				<input type="text" placeholder="请输入标题" name="questionOwner" class="form-control">
			</div>				
		</div>	
		<div class="form-group">
			<label for="" class="col-sm-2">内容</label>
			<div class="col-sm-8" >
				<textarea placeholder="请输入" rows="8" name="questionContent" class="content"></textarea>
			</div>				
		</div>

		<div class="form-group">				
			<div class="col-sm-8 col-sm-offset-2">
				<input type="button" value="修改提问" class="btn btn-primary submit">
			</div>				
		</div>
	</form>
</div>

</body>
</html>