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
				<strong>注册</strong>
					(已有账号？<a href="#">请登录)</a>
			</div>
			<div class="mt15">
				<form id="registerForm" action="/registered" method="post">
					<ul class="register">
						<li><span>我是：</span>
							<input type="radio" name="userType" value="1" />学生
							<input type="radio" name="userType" value="2" />老师
						</li>
						<li><span>昵称：</span><input class="register_input" name="name" type="text" /></li>
						<li><span>邮箱：</span><input class="register_input" name="email" type="email"/></li>
						<li><span>密码：</span><input class="register_input" name="password" type="password" /></li>
						<li><span>确认密码：</span><input class="register_input" name="confirm_password" type="password"/></li>
						<li><span>性别：</span>
								<input type="radio" name="sex" value="m" />男
								<input type="radio" name="sex" value="w" />女
						</li>
						<li>
							<span></span><input type="submit" id="submitBtn" value="立即注册" class="sbt"/>
						</li>
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
	$("#registerForm").validate({
		timely: 1,
		rules: {
			userType:{
				required: function (element) {
	                var boxes = $('[name=userType]');
	                if (boxes.filter(':checked').length == 0) {
	                    return true;
	                }
	                return false;
	            },
			},
			name:{
				required: true
			},
			email: {
				required: true,
				email: true,
				remote: {
				    url: "/checkemail",     //后台处理程序
				    data: {                     //要传递的数据
				    	email:function (){   //不能使用email:$('[name=email]').val()，不然提交的参数有问题
				    		return $('[name=email]').val();
				    	}
				    }
				}
			},
			password: {
				required: true,
				minlength: 6
			},
			confirm_password: {
				required: true,
				minlength: 6,
				equalTo: '[name=password]'
			}
		},
		messages: {
			userType:{
				required:"请选择学生或老师"	
			},
			name:{
				required: "昵称必填"
			},
			email: {
				required: "邮箱必填",
				email: "请填写正确的邮箱",
				remote:"该邮箱已被注册！"
			},
			password: {
				required: "密码必填",
				minlength: "密码至少6个字符",
			},
			confirm_password: {
				required: "密码必填",
				minlength: "密码至少6个字符",
				equalTo:"两次输入密码不符"
			}
		},
		errorPlacement: function(error, element){
			error.appendTo(element.parent("li")); 
		},
		valid: function(form){
	        form.submit();
	    }
	});
});
</script>
</html>