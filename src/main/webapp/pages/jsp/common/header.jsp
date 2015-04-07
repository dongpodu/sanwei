<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<div class="header clearfix">
	<div class="header_logo"><a href="#"></a></div>
	<form action="/search">
		<div class="header_search">
			<input name="key" type="text" placeholder="老师、课程或课件">
		</div>
		<div class="hsb">
			<input type="submit" value="">
		</div>
	</form>
	<div class="hpi">
		<c:if test="${!empty sessionScope.session_user}">
			<a href="#" class="h34 inblock fl">${sessionScope.session_user.name}</a>
			<a href="/logout" class="ml20 w20 h34 inblock fl">
				<img alt="退出" src="/static/img/esc.png">
			</a>
		</c:if>
		<c:if test="${empty sessionScope.session_user}">
			<a href="/login" class="h34 inblock fl">登录</a>
			<a href="/register" class="ml20 h34 fl">
				注册
			</a>
		</c:if>
	</div>		
</div>
