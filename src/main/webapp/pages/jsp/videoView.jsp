<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="common/_includes.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>三味书屋</title>
    <link rel="stylesheet" href="/static/css/style.css"/>
    <link rel="stylesheet" href="/static/css/flowplayer.minimalist.css"/>
</head>
<body>

<jsp:include page="/pages/jsp/common/header.jsp"></jsp:include>

<div class="container clearfix">
	<div class="ml10 f25">课程名：${resource.courseName}</div>
	<div class="sideLeft">
		<div class="flowplayer">
		   <video autoplay>
		      <source type="${resource.fileType}" src="${resource.resourceUrl}"></source>
		   </video>
		</div>
	</div>
	<div class="sideRight">
		<span class="provider">提供者：${resource.creatName}</span>
		<div class="outline">
			<span class="in_block left bold">简介：</span>
			${resource.outline}
		</div>
		<div class="relatedResource">
			<span class="in_block left bold">作者还上传了：</span>
			<c:forEach var="rr" items="${relatedResource}">
				<div class="scroll">
					<ul>
						<li>
							 <a href="/view?id=${rr.id}">
										${rr.courseName}
							 </a>
						</li>
					</ul>
				</div>
			</c:forEach>
		</div>
		<div class="buy">
			<span class="buySpan">时常：&nbsp;&nbsp;&nbsp;价格：</span>
			<a href="orderadd?resourceId=${resource.id}" target="_blank"
			class="goumaiBtn" style="color:#fff">立即购买</a>
		</div>
	</div>
</div>

<jsp:include page="/pages/jsp/common/footer.jsp"></jsp:include>

<script type="text/javascript" src="/static/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="/static/js/flowplayer.min.js"></script>
</body>
<script>
</script>
</html>
