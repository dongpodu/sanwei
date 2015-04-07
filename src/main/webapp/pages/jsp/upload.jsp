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
	<div class="upload">
	<div class="upload_title">上传课件</div>
	<div>
		<form action="/saveResource" id="resourceForm" method="post">
		<table class="tableStyle">
			<tr>
				<td class="right">课件或视频：</td>
				<td></td>
				<td>
				<input type="radio" name="resourceType" value="1">课件
				<input type="radio" name="resourceType" value="2">视频
				</td>
			</tr>
			<tr>
				<td class="right">课程类别：</td>
				<td></td>
				<td>
				<select name="primaryCategory" onchange="switchPrimary($(this));">
					<option>初中</option>
					<option>高中</option>
					<option>IT</option>
					<option>考试考级</option>
				</select>
				<select name="sencondCategory" onchange="switchSecond($(this));">
					<option>初一</option>
					<option>初二</option>
					<option>初三</option>
				</select>
				<select name="thirdCategory">
					<option>语文</option>
					<option>数学</option>
					<option>英语</option>
					<option>物理</option>
					<option>化学</option>
					<option>生物</option>
					<option>政治</option>
					<option>历史</option>
					<option>地理</option>
					<option>其他</option>
				</select>
				</td>
			</tr>
			<tr>
				<td>课程名称：</td>
				<td></td>
				<td><input class="uploadInput" name="courseName"/></td>
			</tr>
			<tr>
				<td>课程价格：</td>
				<td></td>
				<td><input class="uploadInput" name="price"/></td>
			</tr>
			<tr>
				<td>课程简介：</td>
				<td></td>
				<td><textarea name="outline" rows="8" cols="50"></textarea></td>
			</tr>
			<tr>
				<td>课件：</td>
				<td></td>
				<td>
				<span class="uploadBtn">上传</span>
				<input id="kejianfile" name="file" class="uploadFile" type="file"/>
				<input name="resourceUrl" class="hidden"/>
				</td>
			</tr>
			<tr>
				<td>封面：</td>
				<td></td>
				<td>
					<span class="uploadBtn">上传</span>
					<input id="fengmianfile" name="file" class="uploadFile" type="file"/>
					<input name="coverUrl" class="hidden"/>
				</td>
			</tr>
		</table>
		<p class="pr20 right"><input type="submit" class="sbt tc" value="提交"></p>
		</form>
	</div>
	</div>
</div>

<jsp:include page="/pages/jsp/common/footer.jsp"></jsp:include>
<script type="text/javascript" src="/static/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="/static/js/jquery.ui.widget.js"></script>
<script type="text/javascript" src="/static/js/jquery.iframe-transport.js"></script>
<script type="text/javascript" src="/static/js/jquery.fileupload.js"></script>
<script type="text/javascript" src="/static/js/jquery.validate.js"></script>
<script type="text/javascript">
$(function(){

//文件上传
$('#kejianfile,#fengmianfile').fileupload({
    url:"/upload",
    dataType: 'JSON',
    maxNumberOfFiles : 1, //上传文件数
    maxFileSize: 500*1024, //上传文件大小
    progressall: function (e, data) {
    	var progressDiv = $('#progress');
    	if(!progressDiv || progressDiv.length==0){
    		$(this).parent('td').append('<div id="progress" style="width:100%;background:grey;"><div class="bar"></div></div>');
    	}
        var progress = parseInt(data.loaded / data.total * 100, 10);
        $('#progress .bar').css(
            'width',
            progress + '%'
        );
    },
    done: function (e, data) {
    	$(this).siblings('input.hidden').val(data.result.path);
    	$(this).siblings('a').remove();
    	$(this).siblings('div').remove();
    	$(this).siblings('label').remove();  //移除错误提示
    	$(this).parents('td').append('<a class="uploadok" target="_blank" href='+data.result.path+'>上传成功</a>');
    }
});

//表单验证
$("#resourceForm").validate({
	timely: 2,
	ignore: "",
	rules: {
		resourceType:{
			required: true
		},
		courseName: {
			required: true,
			maxlength:200
		},
		price: {
			required: true,
			number:true,
			min:0 
		},
		outline: {
			maxlength:5000
		},
		resourceUrl: {
			required: true
		}
	},
	messages: {
		resourceType:"请选择课件还是视频",
		courseName: {
			required: "课程名必填",
			maxlength:"课程名不能超过200"
		},
		price: {
			required: "价格必填",
			number:"价格只能是数字",
			min:"请输入大于0或等于0的数字"
		},
		outline: {
			maxlength:"大纲字数不能超过5000"
		},
		resourceUrl: {
			required: "请上传课件"
		}
	},
	errorPlacement: function(error, element){
		error.appendTo(element.parent("td")); 
	},
	valid: function(form){
        form.submit();
    }
});


})


var r1 = {
		"小学":['小一','小二','小三','小四','小五','小六'],
		"初中":['初一','初二','初三'],
		"高中":['高一','高二','高三'],
		"IT":['开发','设计','应用','数据库','其他'],
		"考试考级":['认证','考研','留学','社考','其他']
		};

var r2 = {
		"小学":['数学','语文','英语','其他'],
		"初中":['语文','数学','英语','物理','化学','生物','政治','历史','地理','其他'],
		"高中":['语文','数学','英语','物理','化学','生物','政治','历史','地理','其他'],
		
		"IT-开发":['移动开发','应用软件开发','web开发','数据库开发','游戏开发','嵌入式开发','硬件开发','其他'],
		"IT-设计":['photoshop','3dmax','cad','其他'],
		"IT-应用":['操作系统','办公软件','网络技术','其他'],
		"IT-其他":['认证','SEO','用户体验','其他'],
		
		"考试考级-认证":['四级','六级','专业英语','计算机等级','其他'],
		"考试考级-考研":['政治','英语','数学','专业课','MBA','其他'],
		"考试考级-留学":['托福','雅思','SAT','GMAT','GRE','申请','德语','日语','法语','其他'],
		"考试考级-社考":['公务员','会计','司法','自考','金融','医药','建筑','其他'],
		"考试考级-其他":['其他']
		
		};
		
		
function switchPrimary(obj){
	if(obj){
		var objVal = obj.val();
		var secondCatory = r1[objVal];
		var secondSelect = $('[name=sencondCategory]');
		secondSelect.html('');
		$.each(secondCatory,function(i,n){
			secondSelect.append('<option>'+n+'</option>');
		});
		secondSelect.trigger('change');
	}
};

function switchSecond(obj){
	if(!obj){
		return;
	}
	var primaryCategory = $('[name=primaryCategory]').val();
	var thirdCatory = r2[primaryCategory];
	if(!thirdCatory){
		var objVal = obj.val();
		thirdCatory = r2[primaryCategory+'-'+objVal];
	}
	var thirdSelect = $('[name=thirdCategory]');
	thirdSelect.html('');
	$.each(thirdCatory,function(i,n){
		thirdSelect.append('<option>'+n+'</option>');
	});
}

</script>
</body>
</html>
