<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>三味书屋</title>

    <link rel="stylesheet" href="/static/css/style.css"/>
    <link rel="stylesheet" type="text/css" href="/static/css/flexpaper.css" />
    <script type="text/javascript" src="/static/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="/static/js/flexpaper.js"></script>
	<script type="text/javascript" src="/static/js/flexpaper_handlers.js"></script>
</head>
<body>
<jsp:include page="/pages/jsp/common/header.jsp"></jsp:include>

<div class="container">
	<div class="ml10 f25">${resource.courseName}</div>
	<div class="sideLeft">
		<div id="documentViewer" class="flexpaper_viewer"></div>
	</div>
	<div class="sideRight">
		<span class="provider">提供者：</span>
		<div class="relatedResource">
		</div>
	</div>
</div>
<script type="text/javascript">   
	
	var searchServiceUrl = "";
	
	$('#documentViewer').FlexPaperViewer( {
		config : {
			//DOC :"C://pdf/ddd.pdf",
			SwfFile:'${resource.resourceUrl}',
			Scale : 1, //初始化缩放比例
			ZoomTransition : 'easeOut',  //Flexpaper中缩放样式
			ZoomTime : 0.5,  //从一个缩放比例变为另外一个缩放比例需要花费的时间
			ZoomInterval : 0.1, //缩放比例的间隔
			FitPageOnLoad : true, //初始化得时候自适应页面，与使用工具栏上的适应页面按钮同样的效果
			FitWidthOnLoad : true, //初始化的时候自适应页面宽度，与工具栏上的适应宽度按钮同样的效果。
			FullScreenAsMaxWindow : false,
			ProgressiveLoading : false,//当设置为true的时候，展示文档时不会加载完整个文档，而是逐步加载
			MinZoomSize : 0.2,
			MaxZoomSize : 5,
			SearchMatchAll : false,
			SearchServiceUrl : searchServiceUrl,
			RenderingOrder : "flash,html",  //渲染顺序
			ViewModeToolsVisible : false,
			ZoomToolsVisible : true,
			NavToolsVisible : false,
			CursorToolsVisible : false,
			SearchToolsVisible : true,
			key : "",
			DocSizeQueryService : "swfsize.jsp?doc=1111.pdf",
			jsDirectory : '/static/js/',
			localeDirectory : '/static/locale/',
			cssDirectory : '/static/css/',
			JSONDataType : 'jsonp',
			WMode : 'window',
			localeChain: 'en_Zh'
		}}
	);
</script>

<jsp:include page="/pages/jsp/common/footer.jsp"></jsp:include>
</body>
</html>
