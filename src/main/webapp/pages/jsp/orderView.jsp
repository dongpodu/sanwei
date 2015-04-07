<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="common/_includes.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>三味书屋</title>

<link rel="stylesheet" href="/static/css/style.css" />
<link rel="stylesheet" href="/static/css/cart.css" />
<script type="text/javascript" src="/static/js/jquery-1.11.1.min.js"></script>
</head>
<body>
	<jsp:include page="/pages/jsp/common/header.jsp"></jsp:include>

	<div class="container gouwu">
		<div class="gouwu-top">
			<h1>我的购物车</h1>
			<ul>
				<li>1.我的购物车</li>
				<li class="ml20">2.提交订单</li>
				<li class="ml20">3.进行支付</li>
			</ul>
		</div>

		<div class="gouwu-con">
			<div class="th">
				<div class="th1">
					<input type="checkbox" id="checkall">全选
				</div>
				<div class="th2">课程名称</div>
				<div class="th3">课程简介</div>
				<div class="th4">价格</div>
				<div class="th7">操作</div>
			</div>
			<c:forEach var="item" items="${orderItems}">
				<div class="th-nr">
					<div class="th-nr1">
						<input type="checkbox" name="itemId" value="${item.id}">
					</div>
					<div class="th-nr2">
						<a href="${item.resource.resourceUrl}">${item.resource.courseName}</a>
					</div>
					<div class="th-nr3">
						${item.resource.outline}</div>
					<div class="th-nr4">￥${item.price}</div>
					<div class="th-nr7">
						<a href="/orderdelete?id=${item.id}"
							onclick="return window.confirm('您确认要删除吗?');">删除</a>
					</div>
					<div class="clear"></div>
				</div>
			</c:forEach>
			<div class="gouwu-b">
				<div class="gouwu-b2">
					<p>
						总计<span>￥5.0</span>
					</p>
				</div>
			</div>
		</div>
		<div class="shop">
			<a href="#" onclick="goSettlement() " class="jiesuan">去 结 算</a>
		</div>
	</div>

	<script type="text/javascript">
		
	</script>

	<jsp:include page="/pages/jsp/common/footer.jsp"></jsp:include>
</body>
</html>
