<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>三味书屋</title>
    <link rel="stylesheet" href="/static/css/style.css"/>
</head>
<body>

<jsp:include page="/pages/jsp/common/header.jsp"></jsp:include>

<div class="container">
	<div class="register_left"></div>
	<div class="register_right">
		<div class="p10">
			<strong>登录</strong>
		</div>
		<div class="mt80">
			<form id="loginForm" action="/logined" method="post">
				<ul class="register">
					<li><span>邮箱：</span><input class="register_input" type="email" name="email"/></li>
					<li><span>密码：</span><input class="register_input" type="password" name="password"/></li>
					<li><span></span><input type="submit" id="submitBtn" value="登陆" class="sbt"/></li>
				</ul>
			</form>
		</div>
	</div>
</div>
</body>

<script type="text/javascript" src="/static/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="/static/js/jquery.validate.js"></script>
<script>
$(function() {
	$("#loginForm").validate({
		timely: 2,
		rules: {
			email: {
				required: true,
				email: true,
				remote: {
				    url: "/checkemailexists",    
				    data: {                     
				    	email:function (){
				    		return $('[name=email]').val();
				    	}
				    }
				}
			},
			password: {
				required: true,
				remote: {
				    url: "/checkpwd",    
				    data: {                     
				    	email:function (){return $('[name=email]').val();},
				    	password:function (){return $('[name=password]').val();}
				    }
				}
			},
		},
		messages: {
			email: {
				required: "邮箱必填",
				email: "请填写正确的邮箱",
				remote:"邮箱账户不存在"
			},
			password: {
				required: "密码必填",
				remote:"邮箱或密码不正确"
			}
		},
		valid: function(form){
	        form.submit();
	    }
	});
});
</script>
</html>