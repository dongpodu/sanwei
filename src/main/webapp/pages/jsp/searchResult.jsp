<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="common/_includes.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>三味书屋</title>

	<link href="/static/css/index.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="/static/css/style.css" type="text/css" />
    
	<script type="text/javascript" src="/static/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="/static/js/pikaday.js" ></script>
	<script type="text/javascript" src="/static/js/tweenmax.min.js"></script>
	<script type="text/javascript" src="/static/js/expertTween.js"></script>
	<script type="text/javascript" src="/static/js/index.js"></script>
	<script src="/static/js/jquery.easing.1.3.js"></script>
	<script src="/static/js/jquery.isloading.min.js"></script>
	<script src="/static/js/feedback.js"></script>
	<!--[if IE]>
	<script src="/static/js/html5.js"></script>
	<![endif]-->
</head>
<body>
<jsp:include page="/pages/jsp/common/header.jsp"></jsp:include>

<div class="">
	<jsp:include page="/pages/jsp/common/common.jsp"></jsp:include>
	<div class="the_pilot_box feedback_survey" fbackid="3">
		<section class="the_pilot">
			<h1 class="author_title" style="border-bottom:1px solid #0aaa7a;">
				<a style="color:#0aaa7a;">搜寻结果</a>
			</h1>
			<article class="new_class">
			<c:if test="${empty resourceList}">
				<span>对不起，你要找的结果不存在！</span>
			</c:if>
			<c:if test="${!empty resourceList}">
				<c:forEach var="resource" items="${resourceList}">
	            	<span class="new_class_per">
						<span class="new_class_per_top"></span>
						 <span class="new_class_per_pic new_class_show">
							<a href="/view?id=${resource.id}">
								<c:if test="${empty resource.coverUrl}">
									<img src="/static/img/defaultCover.jpg" alt="${resource.courseName}">
								</c:if>
								<c:if test="${!empty resource.coverUrl}">
									<img src="${resource.coverUrl}" alt="${resource.courseName}">
								</c:if>
							</a>
							<span class="ath_name author_name_hov ath_bg_black" style="top: 119px; height: 173px;">
									<p style="margin-top: 10px;">提供者：${resource.creatName}</p>
									<b class="con_hov_hide">
										<font>课程名：${resource.courseName}</font>
									</b>
							</span>
						 </span>
					</span>
				</c:forEach>
			</c:if>
			</article>
		</section>
	</div>
	<jsp:include page="/pages/jsp/common/navigator.jsp"></jsp:include>
</div>
<jsp:include page="/pages/jsp/common/footer.jsp"></jsp:include>
</body>
</html>