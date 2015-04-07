$(function() {
	$(".card_kefu_right_l b").hover(function(){
		$(this).find(".card_span_normal").stop().animate({top:'0'},{duration:200});
		$(this).find(".img_dis_none").css("opacity","0")
	})
	
	$(".card_tips").mouseover(function(){
		$(".letter_center").addClass("letter_add");
		$(".card_open").slideDown();
	})
	$(".card_tips").mouseleave(function(){
		$(".letter_center").removeClass("letter_add");
		$(".card_open").slideUp();
	})
	$('.letter_box ul li:last-child').css('border-bottom','0');
	//微信二维码
	$('.ewm_show').mouseover(function(){
		$('.ewm_hide').show();	
	})
	$('.ewm_show').mouseleave(function(){
		$('.ewm_hide').hide();	
	})
	//关闭代金券浮框
	$('.left_djq_close').click(function(){
		$('.left_djq').hide();
	})
	$(".card_kefu_right_l b").hover(function(){
		$(this).find(".card_span_normal").stop().animate({top:'0'},{duration:200});
		$(this).find(".img_dis_none").css("opacity","0")
	},
	function(){
		$(this).find(".card_span_normal").stop().animate({top:'20'},{duration:"fast"});
		$(this).find(".card_span_normal").animate({top:'20'},{duration:0});
		$(this).find(".img_dis_none").css("opacity","1")
	});

    //调整学员评价样式
    $('.css_user_feedback').find("img").each(function(){
        $(this).parent().css("float","left");
        $(this).parent().next().css("verticalAlign","top");
        $(this).parent().next().css("padding-left","10px");
        $(this).parent().parent().next().find('td:last-child').css("width","888px");
        var user_name = $(this).parent().next().html();
        var feedback = $(this).parent().parent().next().find('td:last-child').html();
        $(this).parent().next().html('<b style="font-weight:bold;font-size:14px;line-height:30px;float:left;width:888px;">'+user_name+'</b><br/>'+feedback);
        $(this).parent().parent().next().find('td:last-child').html("");
    });
    
	$.fn.manhuatoTop = function(options) {
		var defaults = {			
			showHeight : 150,
			speed : 1000
		};
		var options = $.extend(defaults,options);
		$("body").prepend("<div id='totop'><a>回顶部</a></div>");
		var $toTop = $(this);
		var $top = $("#totop");
		var $ta = $("#totop a");
		$toTop.scroll(function(){
			var scrolltop=$(this).scrollTop();		
			if(scrolltop>=options.showHeight){				
				$top.show();
			}
			else{
				$top.hide();
			}
		});	
		$ta.hover(function(){ 		
			$(this).addClass("cur");	
		},function(){			
			$(this).removeClass("cur");		
		});	
		$top.click(function(){
			$("html,body").animate({scrollTop: 0}, options.speed);	
		});
	}
	
	dropMenu(".drop-menu-effect");
	$(".submenu li:last-child").css("border-bottom", "none");
	$('.grade_bott img:nth-child(5n+5)').css('margin-right', '0');
	
	$(".grade_pic ul li:nth-child(n+6)").css('margin-bottom', '0');
	$(".grade_pic ul li:nth-child(5n+5)").css('margin-right', '0');
	$('.author_pic_box:nth-child(4n+4)').css('margin-right', '0');
	$('.last_dev_left ul li:last-child').css('border-bottom', '0');
	//名师团队
	$('.con_hover').mouseover(function() {
		$(this).find('.con_hov_show').show();
		$(this).find('.con_hov_hide').hide();
		$(this).find('.author_name_hov').stop().animate({
			top : '0'
		}, {
			duration : 500
		}).css('height', '173px');
		$(this).find('.author_name_hov').addClass('ath_bg_black');
		$(this).find('.author_name_hov p').css('marginTop', '10px');
	});
	$('.con_hover').mouseleave(function() {
		$('.con_hov_show').hide();
		$('.con_hov_hide').show();
		$('.author_name_hov').stop().animate({
			top : '119px'
		}, {
			height : '54px'
		}, {
			duration : 500
		});
		$('.author_name_hov').removeClass('ath_bg_black');
		$(this).find('.author_name_hov p').css('marginTop', '');
	});
	//免费课程
	$(this).find('.new_class_show').mouseover(function() {
		$(this).find('.new_mask_jl').show();
	})
	$('.new_class_show').mouseleave(function() {
		$('.new_mask_jl').hide();
	})
	//逐题精讲课程
	$(this).find('.she_mask').mouseover(function() {
		$(this).find('.new_zt_mask').hide();
		$(this).find('.new_zt_mask1').show();
	});
	$('.she_mask').mouseleave(function() {
		$('.new_zt_mask').show();
		$('.new_zt_mask1').hide();
	});
	$(window).manhuatoTop({
		showHeight : 300,//设置滚动高度时显示
		speed : 800
	//返回顶部的速度以毫秒为单位
	});
	$('#add_click').click(function() {
		$('.add_box').fadeToggle(500);
	});
	$.ajax({
		type : "POST",
		url : "/user/calendar/indexshowlogin",
		success : function(examList) {
			if (examList != "") {
				examList_arr = examList.split("||");
				setIndexCalendar(examList_arr[0], examList_arr[1],
						examList_arr[2]);
			}
		}
	});
	dMenu(".menu-effect");
	dM(".menu-eff");
	$("#focusIndex1").show();
	$("#focusBar li").css("width",$(window).width());
	$("#focusBar li").mouseover(function(){stopFocusAm();}).mouseout(function(){starFocustAm();});
	starFocustAm();
});


var autocourse_id;
$(document).on('click', '.addShopCart', function(){
	var price_id = $(this).attr('priceid');
	var user_id  = $(this).attr('userid');

    var valid_time = $(this).attr('valid_time');

	if(!user_id) {
		location.href = "/login";
	}

    if(!valid_time){
        valid_time = 0;
    }

	var loopShopcart = 0;
	var priceAll = 0;
	$.ajax({
		url:'/shopcart/add',
		data:{'price_id':price_id, 'user_id':user_id,'valid_time':valid_time},
		type:'POST',
		async:'true',
        dataType:'json',
		success:function (cartlist) {
            if(cartlist.error == 1){
                alert("您的购物车中已存在抢购商品");
            }else if(cartlist.error == 2){
                alert("您已经参与过抢购活动");
            }else if(cartlist.error == 3){
                alert("对不起，该商品已下架");
            }else if(cartlist.error == 0){
                $('.shop_cart').show();
                //列出所有添加购物车的商品
                cartlist = cartlist.list;
                $('.shop_box_left_top').html('');
                $('.headerShop').remove();
                $.each(cartlist, function(key, value) {
                    $('.shop_box_left_top').append('<span class="shop_cart_per"><span class="shop_per_left">'+value.price_name+'</span><span class="shop_per_right"><a href="javascript:void(0)" class="delShopcart" priceid="'+value.price_id+'"></a></span></span>');
                    $('.headerShopFooter').before('<li class="headerShop"><b>'+value.price_name+'</b><a href="javascript:void(0)" class="delShopcart" priceid="'+value.price_id+'"></a></li>');

                    loopShopcart++;
                    priceAll += parseFloat(value.discount_price);
                });

                //变更header上的数字
                if($('#shopcarmess').text() == '您的购物车还没有课程'){
                    $('#shopcarmess').html('您的购物车中共有<u class="shopcartNum">'+loopShopcart+'</u>门课程');
                }else{
                    $('.shopcartNum').html(loopShopcart);
                }

                $('.shopcartmoney').html(priceAll);
                $('.shoping_car').attr('class','shoping_car');
            }
		}
	});
});

$('.toShopCart').click(function(){
	location.href = "/shopcart";
});

$('.shoping_car').click(function(){
	$('.shop_cart').fadeToggle(500);

	if($('#shoping_cars').attr('class') == 'a_left_bg shoping_car'){
		$('.shoping_car').attr('class','shoping_car');
	}else{
		$('.shoping_car').attr('class','a_left_bg shoping_car');
	}
	return false; 
});

$('body').click(function(it){	
	if(it.target.localName == 'div' || it.target.localName == 'article' || it.target.localName == 'section' || it.target.localName == 'footer' || it.target.localName == 'p' || it.target.localName == 'span'){
		$('.shop_cart').fadeOut(500);
		$('.shoping_car').attr('class','a_left_bg shoping_car');
	}
});


//删除购物车中商品
$(document).on('click', '.delShopcart', function(){
	var price_id = $(this).attr('priceid');

	var loopShopcart = 0;
	var priceAll = 0;
	$.ajax({
		url:'/shopcart/delone',
		data:{'price_id':price_id},
		type:'POST',
		success:function (cartlist) {
			$('.shop_cart').show();
			//列出所有添加购物车的商品
			cartlist = eval("("+cartlist+")");
			$('.shop_box_left_top').html('');
			$('.headerShop').remove();
			$.each(cartlist, function(key, value) {
            	$('.shop_box_left_top').append('<span class="shop_cart_per"><span class="shop_per_left">'+value.price_name+'</span><span class="shop_per_right"><a href="javascript:void(0)" class="delShopcart" priceid="'+value.price_id+'"></a></span></span>');
            	$('.headerShopFooter').before('<li class="headerShop"><b>'+value.price_name+'</b><a href="javascript:void(0)" class="delShopcart" priceid="'+value.price_id+'"></a></li>');

            	loopShopcart++;
            	priceAll += parseInt(value.discount_price);
            });

			//变更header上的数字
			if(loopShopcart > 0){
				$('.shopcartNum').html(loopShopcart);
			}else{
				$('#shopcarmess').html('您的购物车还没有课程');
				$('.a_left_js').remove();
			}
			$('.shopcartNum').html(loopShopcart);
			$('.shopcartmoney').html(priceAll);
		}
	});
});

//清空购物车
$('.clearShopcart').click(function(){
	$.ajax({
		url:'/shopcart/clear',
		type:'POST',
		success:function (clear) {
			if(clear) {
				$('.shop_box_left_top').html('');
				$('.headerShop').remove();
			}

			//变更header上的数字
			$('#shopcarmess').html('您的购物车还没有课程');
			$('.a_left_js').remove();
			$('.shopcartmoney').html('0');
		}
	});
});

//收藏课程
$('.collect_price').click(function() {
	var o         = $(this);
	var isCollect = $(this).attr('iscollection');
	var price_id  = $(this).attr('priceid');
	var path = '';

	if(isCollect == '1') {
		path = '/user/collection/cancel';
	} else {
		path = '/user/collection/add';
	}

	$.ajax({
		url : path,
		data: {'price_id' : price_id},
		type: 'POST',
		success:function (msg) {
			if(msg == 'success') {
				//修改文字
				if(isCollect == '1') {
					o.attr('iscollection', '0');
					o.html('收藏课程');
                    					} else {
					o.attr('iscollection', '1');
					o.html('取消收藏');
				}
			}else{
                window.location.href="/message?type=notlogin";
            }
		}
	});
});

//收藏课程
$('.follow_teacher').click(function() {
    if(ajax_apply_bool == 0){
        return;
    }
    ajax_apply_bool = 0;
	var o          = $(this);
	var isFollow   = $(this).attr('isfollow');
	var teacher_id = $(this).attr('teacherid');
	var path = '';

	if(isFollow == '1') {
		path = '/user/follow/cancel';
	} else {
		path = '/user/follow/add';
	}

	$.ajax({
		url : path,
		data: {'teacher_id' : teacher_id},
		type: 'POST',
		success:function (collect) {
            ajax_apply_bool = 1;
			if(isFollow == '1') {
				o.attr('isfollow', '0');
				o.html('<img src="/resource/images/canl_gz0.png" />');
			} else {
				o.attr('isfollow', '1');
				o.html('<img src="/resource/images/canl_gz1.png" />');
			}
		}
	});
});

$('.apply_course').click(function() {
	var path = '';
	var course_id = $(this).attr('course_id');
	var msg = '';
	var o          = $(this);
    autocourse_id = course_id;
	path = '/publecture/applyCourse';
	$.ajax({
		url : path,
		data: {'course_id' : course_id},
		type: 'POST',
		success:function (status) {
            if(status == 'exceed'){
                msg="超出报名人数";
                alert(msg);
            }else if(status == 'denied'){
                msg="报名资格不足";
                alert(msg);
            }else if(status == 'login'){
                window.location.href = '/login?course='+autocourse_id;
            }else if(status == 'applyed'){
                return false;
            }else{
                var s=status.indexOf('|');
                var course_id =  status.substring(0,s);
                var peonumers = status.substring(s+1);
                o.after('<a href="javascript:void(0)" class="btn_bg_input btn_bm_bg" onclick="enterroom('+autocourse_id+')">进入讲座现场</a>');
                o.remove();
                $("#"+autocourse_id).text (peonumers);
                msg="报名成功";
                alert(msg);
            }
		}
	});
});


var timerFID;

function nextPage() {
	changeFocus(true);
}
function prePage() {
	changeFocus(false);
}

var currentFocusI=1;
var changeingFocus = false;
function changeFocus(dir,nextI) {
	if(currentFocusI == nextI){
		return;	
	}
	
	if(currentFocusI > nextI){
		dir = false;	
	}else{
		dir = true;
	}
	
	
	if(nextI == 0){
		dir = true;	
		var nextI = dir?currentFocusI+1:currentFocusI-1;
		nextI = nextI>$("#focusBar li").length?1:(nextI<1?$("#focusBar li").length:nextI);
	}
	
	$('.ban_item_box a').each(function(){
		$(this).removeClass("a_active");
	});
	
	$('#nav_'+nextI).addClass("a_active");
	
	
	if($("#focusBar li").length <= 1) return;
	if(changeingFocus) return;
	changeingFocus = true;
	
	$("#focusIndex"+nextI).stop(false,true);
	$("#focusIndex"+nextI+" .focusL").stop(false,true);
	$("#focusIndex"+nextI+" .focusR").stop(false,true);
	
	$("#focusIndex"+currentFocusI).css("width",$(window).width());
	$("#focusIndex"+nextI).css("width",$(window).width());
	if(dir) {
		$("#focusIndex"+nextI).css("left",$(window).width());
		$("#focusIndex"+nextI+" .focusL").css("left",$(window).width()/2);
		$("#focusIndex"+nextI+" .focusR").css("left",$(window).width()/2);
		$("#focusIndex"+currentFocusI).show();
		$("#focusIndex"+nextI).show();
		
		$("#focusIndex"+currentFocusI+" .focusL").animate({left: -($(window).width()/2+1000)},600,'easeInExpo');
		$("#focusIndex"+currentFocusI+" .focusR").animate({left: -($(window).width()/2+1000)},600,'easeInExpo',function(){
				$("#focusIndex"+nextI+" .focusL").animate({left: -500},600,'easeInOutCirc');
				$("#focusIndex"+nextI+" .focusR").animate({left: -500},600,'easeInOutCirc');
				
				$("#focusIndex"+currentFocusI).animate({left: -$(window).width()},600,'easeOutExpo');
				$("#focusIndex"+nextI).animate({left: 0},600,'easeOutExpo',function(){
						$("#focusIndex"+currentFocusI).hide();
						currentFocusI = nextI;
						changeingFocus = false;
				});
		});
	} else {
		$("#focusIndex"+nextI).css("left",-$(window).width());
		$("#focusIndex"+nextI+" .focusL").css("left",-($(window).width()/2+1000));
		$("#focusIndex"+nextI+" .focusR").css("left",-($(window).width()/2+1000));
		$("#focusIndex"+currentFocusI).show();
		$("#focusIndex"+nextI).show();
		
		$("#focusIndex"+currentFocusI+" .focusR").animate({left: $(window).width()/2},600,'easeInExpo');
		$("#focusIndex"+currentFocusI+" .focusL").animate({left: $(window).width()/2},600,'easeInExpo',function(){
				$("#focusIndex"+nextI+" .focusL").animate({left: -500},600,'easeInOutCirc');
				$("#focusIndex"+nextI+" .focusR").animate({left: -500},600,'easeInOutCirc');
				
				$("#focusIndex"+currentFocusI).animate({left: $(window).width()},600,'easeOutExpo');
				$("#focusIndex"+nextI).animate({left: 0},600,'easeOutExpo',function(){
						$("#focusIndex"+currentFocusI).hide();
						currentFocusI = nextI;
						changeingFocus = false;
				});
		});
	}
}

function starFocustAm(){
	timerFID = setInterval("timer_tickF()",6000);
}

function stopFocusAm(){
	clearInterval(timerFID);
}

function timer_tickF() {
	changeFocus(true,0);
}

function set_Tab(name, cursel, n) {
	for (i = 1; i <= 3; i++) {
		var menu = document.getElementById(name + i);
		var con = document.getElementById("con_" + name + "_" + i);
		menu.className = i == cursel ? "hover1" : "";
		con.style.display = i == cursel ? "block" : "none";
	}
}

function dropMenu(obj) {
	$(obj).each(function() {
		var theSpan = $(this);
		var theMenu = theSpan.find(".submenu");
		var tarHeight = theMenu.height();
		theMenu.css({
			height : 0,
			opacity : 0
		});
		theSpan.hover(function() {
			$(this).addClass("selected");
			theMenu.stop().show().animate({
				height : tarHeight,
				opacity : 0.9
			}, 400);
		}, function() {
			$(this).removeClass("selected");
			theMenu.stop().animate({
				height : 0,
				opacity : 0
			}, 400, function() {
				$(this).css({
					display : "none"
				});
			});
		});
	});
}


function Zoom(obj, width, height) {
	var img = new Image();
	img.src = obj.src;
	var scale = Math.max(width / img.width, height / img.height);
	var newWidth = img.width * scale;
	var newHeight = img.height * scale;
	var div = obj.parentNode;
	obj.width = newWidth;
	obj.height = newHeight;
	div.style.width = width + "px";
	div.style.height = height + "px";
	div.style.overflow = "hidden";
	obj.style.marginLeft = (width - newWidth) / 2 + "px";
	obj.style.marginTop = (height - newHeight) / 2 + "px";
}


window.onload = function() {
	var picker = new Pikaday({
		field : document.getElementById('datepicker'),
		firstDay : 1,
		minDate : new Date('2000-01-01'),
		maxDate : new Date('2020-12-31'),
		yearRange : [ 2000, 2020 ],
		format : 'YYYY-MM-DD'
	})
}
var expert_tween_current = 1;
var expert_groups = Math.ceil(4.125);
var expert_tween_next;

var free_tween_current = 1;
var free_groups = Math.ceil(1);
var free_tween_next;
function dMenu(obj) {
	$(obj).each(function() {
		var tSpan = $(this);
		var tMenu = tSpan.find(".subdh_new");
		var tHeight = tMenu.height();
		tMenu.css({
			height : 0,
			opacity : 0
		});
		tSpan.hover(function() {
			tMenu.stop().show().animate({
				height : tHeight,
				opacity : 0.9
			}, 400);
		}, function() {
			tMenu.stop().animate({
				height : 0,
				opacity : 0
			}, 400, function() {
				$(this).css({
					display : "none"
				});
			});
		});
	});
}


function dM(obj) {
	$(obj).each(function() {
		var tS = $(this);
		var tM = tS.find(".subdh");
		var tH = tM.height();
		tM.css({
			height : 0,
			opacity : 0
		});
		tS.hover(function() {
			tM.stop().show().animate({
				height : tH,
				opacity : 0.9
			}, 400);
		}, function() {
			tM.stop().animate({
				height : 0,
				opacity : 0
			}, 400, function() {
				$(this).css({
					display : "none"
				});
			});
		});
	});
}

//倒计时
var server_time = '1415883968';
var this_start = '1415883600';
var this_end = '1415890800';
var next_start = '1415970000';
var js_time = Math.floor((new Date()).getTime() / 1000);
var basic_time_error = 0;
basic_time_error = js_time - server_time;

$('.can_snapping').each(function() {
	$(this).hide();
});
$('.can_not_snapping').each(function() {
	$(this).show();
});
$('#snapping_now').hide();
$('#guide_dialog').show();
yh_time_count();

function yh_time_count() {
	var yh_time_now = Math.floor((new Date()).getTime() / 1000)
			- basic_time_error;

	if ((yh_time_now >= this_start && yh_time_now <= this_end)
			|| yh_time_now >= next_start) {
		$('.can_snapping').each(function() {
			$(this).show();
		});
		$('.can_not_snapping').each(function() {
			$(this).hide();
		});
		$('#snapping_now').show();
		$('#guide_dialog').hide();
	} else {
		var yh_error = 0;
		if (yh_time_now < this_start) {
			yh_error = this_start - yh_time_now;
		}
		if (yh_time_now > this_end) {
			yh_error = next_start - yh_time_now;
		}
		var h = Math.floor(yh_error / 3600);
		var m = Math.floor((yh_error - h * 3600) / 60);
		var s = yh_error - h * 3600 - m * 60;

		var hv1 = h < 10 ? 0 : String(h).slice(0, 1);
		var hv2 = h < 10 ? h : String(h).slice(1);
		var mv1 = m < 10 ? 0 : String(m).slice(0, 1);
		var mv2 = m < 10 ? m : String(m).slice(1);
		var sv1 = s < 10 ? 0 : String(s).slice(0, 1);
		var sv2 = s < 10 ? s : String(s).slice(1);

		$('#h_1').html(hv1);
		$('#h_2').html(hv2);
		$('#m_1').html(mv1);
		$('#m_2').html(mv2);
		$('#s_1').html(sv1);
		$('#s_2').html(sv2);

		setTimeout(yh_time_count, 1000);
	}
}

$(".choseExam").click(function(){
	var id = $(this).attr('id');

	//移除所有选中
	$('a[name*="class_"]').each(function() {
		$(this).removeClass('current');
	});
	$('span[name*="class_"]').each(function() {
		$(this).hide();
	});
	

	//添加选中
	$('span[name*="parent_"]').each(function() {
		if($(this).attr('id') == id) {
			$(this).addClass('current');
			$(this).show();
		} else {
			$(this).removeClass('current');
			$(this).hide();
		}
	});
});

//确认按钮
$("#add_close").click(function() {
	var examid   = $('span[name*="parent_"][class*="current"]').attr('id');
    if(examid == undefined){
        $("#add_infor_ts").html("*请选择考试科目");
        return;
    }
	var examtime = $('.cal_rili').val();
    if(examtime == ""){
        $('#add_infor_ts').html("*请选择考试时间");
        return;
    }

    var today = new Date();
    var today_timestamp = today.getTime()/1000;

    var exam_day = new Date(Date.parse(examtime));
    var exam_day_timestamp = exam_day.getTime()/1000;

    if(exam_day_timestamp - today_timestamp < 10*86400){
        $('#add_infor_ts').html("*考试时间距今不足10天");
        return;
    }


	$.ajax({
        type : "POST",
        url  : "/user/calendar/indexshow",
        data : {
            'examid'   : examid,
            'examtime' : examtime
        },
        success:function(examList) {
                            $('#calendar_link').attr('href',"/user/calendar/index/"+examid+":"+examtime);
            
            $("#add_infor_ts").html("");
            $('.add_box').hide();
            setIndexCalendar(examList,examid,examtime);
        }
    });
});

function setIndexCalendar(examList,examid,examtime){
    var myDate   = new Date();
    var month    = new Array();

    var timeArray = examtime.split('-');
    month[1] = '一';
    month[2] = '二';
    month[3] = '三';
    month[4] = '四';
    month[5] = '五';
    month[6] = '六';
    month[7] = '七';
    month[8] = '八';
    month[9] = '九';
    month[10] = '十';
    month[11] = '十一';
    month[12] = '十二';

    if(examList) {
        el = eval("("+examList+")");
        $('.intel_ban').html('');

        var currentMonth = myDate.getMonth() + 1;
        var imgpath = '';
        if(examid == 1) {
            imgpath = '/resource/images/newindex/z_toefl.png';
        } else if(examid == 2) {
            imgpath = '/resource/images/newindex/z_ielts.png';
        } else if(examid == 3) {
            imgpath = '/resource/images/newindex/z_gre.png';
        } else if(examid == 4) {
            imgpath = '/resource/images/newindex/z_gmat.png';
        } else if(examid == 5) {
            imgpath = '/resource/images/newindex/z_sat.png';
        }

        var html = '<span class="intel_ban_mth"><font><a>今天</a><i>'+currentMonth+'.'+myDate.getDate()+'</i><span class="tad_jian"></span><span class="time_dian1"></span></font></span>';

        $.each(el, function(k, v){
            html += '<span class="intel_ban_per"><span class="intel_ban_pic"><a href="'+v.url+'" target="_blank"><img src="'+v.file+'" /></a></span><span class="intel_ban_class"><a href="'+v.url+'" target="_blank">'+ v.teachername+'<br />'+v.title+'</a><span class="bk_jian_icon"></span><span class="time_dian"></span></span><span class="intel_ban_time"><font>'+v.start+'</font><b>-</b><font>'+v.end+'</font></span></span>';
        });

        html += '<span class="intel_ban_text"><font><img src="'+imgpath+'" /><span class="time_dian2"></span></font><i>'+timeArray[0]+'.'+timeArray[1]+'.'+timeArray[2]+'</i></span>';

        $('.intel_ban').html(html);
    }
}


//产品对比
var isopen=false;

//进入讲座现场
function enterroom(id){
    $.ajax({
        url : '/publecture/enterroom',
        data: {'course_id' : id},
        type: 'POST',
        success:function (data) {
            var myObject=JSON.parse(data);
            if(myObject.errorCode == 1){
                alert('您的课程开始时间是'+myObject.time);
                return false;
            }else{
                window.location.href == '/publive?course_id='+id;
            }
        }
    });
}

//关闭div层
function hidediv(){
	$('div.floatdiv:visible').hide(); 
	isopen=false;
}

//显示div层
function showdiv(){
	$('div.floatdiv:hidden').show(); 
	isopen=true;
}

//点击图标删除此行
function delplan(obj){
	var parent=obj.parentNode;
	var id=parent.id;
    if($.cookie('planid') != undefined){
        var planids = $.cookie('planid');
        var planids_arr = planids.split("|");
        if(planids_arr.length > 1){
            for(var i=0;i< planids_arr.length;i++){
                var plan_info = planids_arr[i].split("@");
                if(plan_info[0] != id){
                    $.cookie('planid',planids_arr[i]);
                }
            }
        }else{
            $.removeCookie('planid');
        }
    }
	parent.parentNode.removeChild(parent); 
	//去除选中的
	var checkid="planid"+id.substr(2,id.length);
	document.getElementById(checkid).checked=false;
}


function actionplan(obj){
	var planid   = obj.parentNode.getAttribute("planid"); 
	var planname = obj.parentNode.getAttribute("alt");

    $('#selectedplan').html("");
    if($.cookie('planid') != undefined){
        var planids = $.cookie('planid');
        var planids_arr = planids.split("|");
        for(var i=0;i< planids_arr.length;i++){
            var plan_info = planids_arr[i].split("@");
            $("#selectedplan").append("<li style='cursor:pointer;' id="+plan_info[0]+"><span>"+plan_info[1]+"</span><img src=http://www.smartstudy.com/resource/images/duibi.png onclick=delplan(this) style=cursor:pointer;></img></li>");
        }
    }

	if(isopen==false){
		showdiv();
	}
	if(checkplanid(planid)){
		addplan(planid,planname);
	}else{
		return false;
	}
}

//选中一个元素添加到对比
function addplan(planid,planname){
		if($("#selectedplan li").length > 1){
			alert("最多两个课程");
			return false;
		}

		$("#selectedplan").append("<li style='cursor:pointer;' id="+planid+"><span>"+planname+"</span><img src=http://www.smartstudy.com/resource/images/duibi.png onclick=delplan(this) style=cursor:pointer;></img></li>");

        if($.cookie('planid') != undefined){
            var now_cookie =  $.cookie('planid');
            $.cookie('planid', now_cookie+"|"+planid+"@"+planname);
        }else{
            $.cookie('planid',planid+"@"+planname);
        }
}

function checkplanname(planname){
	var boolplanname = true;
	$("#selectedplan span").each(function(){
		var liplanname = $(this).text();
		if(liplanname == planname){
			boolplanname = false;
		}
	})
	return boolplanname;
}

function checkplanid(planid){
    var boolplanid = true;
    $("#selectedplan li").each(function(){
        var liplanid = $(this).attr('id');
        if(liplanid == planid){
            boolplanid = false;
        }
    })
    return boolplanid;
}

$('.compareplan_submit').click(function(){
	var price = [];
	
	$('#selectedplan li').each(function(i) {
		var id = $(this).attr('id');
		if(id) {
			price[i] = id;
		}
	});
    if(price.length > 1){
        var path = '/'+price[0]+'-'+price[1];
        window.open("/expertclass" + path);
    }else{
        alert("请选择要进行对比的课程！");
    }
});

function buy_official_book_amazon(bookid){
    var path="";
    switch(bookid){
        case 13:
            path="http://www.amazon.cn/托福考试官方指南-美国教育考试服务中心/dp/B008U4JLUI";
            break;
        case 15:
            path="http://www.amazon.cn/剑桥雅思考试全真试题4-剑桥大学考试委员会外语考试部/dp/B00CUHRN4M";
            break;
        case 16:
            path="http://www.amazon.cn/新东方•剑桥雅思考试全真试题集5/dp/B001TK2OV8";
            break;
        case 17:
            path="http://www.amazon.cn/新东方•剑桥雅思考试全真试题集6-Cambridge-University-Press/dp/B00139O27O";
            break;
        case 18:
            path="http://www.amazon.cn/新东方•剑桥雅思考试全真试题集7-剑桥大学考试委员会/dp/B002CQV5T0";
            break;
        case 19:
            path="http://www.amazon.cn/新东方•剑桥雅思考试全真试题集8/dp/B004W0Y65Y";
            break;
        case 20:
            path="http://www.amazon.cn/新东方•剑桥雅思考试全真试题集9-剑桥大学考试委员会/dp/B00BIVBRU2";
            break;
        case 21:
            path="http://www.amazon.cn/新东方-GRE考试官方指南-美国教育考试服务中心/dp/B009FRNR4A";
            break;
        case 27:
            path="http://www.amazon.cn/The-Official-Guide-for-GMAT-Review-Graduate-Management-Admission-Council/dp/1118109791";
            break;
        case 29:
            path="http://www.amazon.cn/The-Official-SAT-Study-Guide-2nd-edition-The-College-Board/dp/0874478529";
            break;
    }
    window.open(path);
}
function buy_official_book_dangdang(bookid){
    var path="";
    switch(bookid){
        case 13:
            path="http://product.dangdang.com/22844192.html";
            break;
        case 15:
            path="http://product.dangdang.com/23254566.html";
            break;
        case 16:
            path="http://product.dangdang.com/9213257.html";
            break;
        case 17:
            path="http://product.dangdang.com/20012577.html";
            break;
        case 18:
            path="http://product.dangdang.com/20612336.html";
            break;
        case 19:
            path="http://product.dangdang.com/21054780.html";
            break;
        case 20:
            path="http://product.dangdang.com/23197829.html";
            break;
        case 21:
            path="http://product.dangdang.com/22879892.html";
            break;
        case 27:
            path="http://product.dangdang.com/22811765.html";
            break;
        case 29:
            path="http://product.dangdang.com/1184843206.html";
            break;
    }
    window.open(path);
}

$(document).ready(function(){
    $(".sec_n3 div").click(function(){
        var thisinput=$(this);
        var thisul=$(this).parent().find("ul");
        if(thisul.css("display")=="none"){
            if(thisul.height()>200){thisul.css({height:"200"+"px","overflow-y":"scroll" })};
            thisul.fadeIn("100");
            thisul.hover(function(){},function(){thisul.fadeOut("100");})
            thisul.find("li").click(function(){thisinput.html($(this).text());thisul.fadeOut("100");}).hover(function(){$(this).addClass("hover");},function(){$(this).removeClass("hover");});
        }else{
            thisul.fadeOut("fast");
        }
    });
    $('.sec_n2').mouseleave(function(){
        $(this).find('ul').fadeOut("fast");
    });
    $('.fk_sub').click(function(){
        var surveylist = '';
        $(".main2 input[name='survey_list']").each(function(){
            if($(this).attr("checked")){
                surveylist += $(this).attr('id') + ',';
            }
        });
        if(surveylist && feedbackpost == 1){
            feedbackpost = 2;
            $.post('/home/survey', {surveycode:surveycode,surveylist: surveylist}, function (data) {
                feedbackpost = 1;
            });
        }else{
            if(surveytitle){
                alert('请选择反馈建议');
                return false;
            }
        }
        $('.main').animate({right:'-220px'},500);
        $('.fk_tips_small_2').show();
    })
    $('.fk_sub_close').click(function(){
        $('.fk_tp_box').hide();
        $('.fk_tips_small_2').hide();
        $('.fk_tips_small_4').hide();
        expanded = !expanded;
        selectareabool = true;
    })
    $('.fk_sub_close_done').click(function(){
        clearall();
    })
    $('.fk_small_box').click(function(){
        feedbackareaid = '';
        $('.main').animate({right:'-220px'},500);
        $('.fk_tp_box').show();
        $('.fk_tips_small_2').hide();
        feedbacktypeid = 2;
    })
    $('.fk_small_box_juti').click(function(){
        $('.main').animate({right:'-220px'},500);
        $('.fk_tips_small_3').show();
        $('.fk_tips_small_2').hide();
        selectareabool = false;
        feedbacktypeid = 1;
        setTimeout( function(){
            $('.fk_tips_small_3').hide();
            feedback_bool = 1;
        }, 1500 );
    })

    $('.fk_bq_span img').click(function(){
        $('.fk_click').remove();
        var html = '<a class="fk_click"><img src="/resource/images/feedback/xh_hover.png" /></a>';
        $(this).parent().parent().append(html);
        $('.buzhou1').show();
    })
    $('#feedback_type ul').click(function(){
        $('.buzhou2').show();
    })
});
function savefeedback(){
    $('#des_tishi').text('');
    $('#email_des').text('');
    $('#phone_des').text('');
    var score = $('.fk_click').parent().find('img').attr('value');
    var category = getidByText('feedback_type_v');
    var content = $('#feed_back_des').val();
    var email = $('#feed_back_email').val();
    var phone = $('#feed_back_phone').val();
    if(content.length <= 0 || content == fb_des){
        $('#des_tishi').text('请输入描述内容');
        return;
    }
    if(!regemail(email) && !regphone(phone)){
        if(!regphone(phone)){
            $('#phone_des').text('请输入正确的手机号');
        }
        if(!regemail(email)){
            $('#email_des').text('请输入正确的邮箱');
        }
        return;
    }else{
        if(!regphone(phone)){
            phone = '';
        }
        if(!regemail(email)){
            email = '';
        }
    }

    var browser_resolution = screen.width+'*'+screen.height;
    if(feedbackpost == 1){
        feedbackpost = 2;
        $.post('/feedback/add', {surveycode:surveycode,type:feedbacktypeid,score:score,category:category,content:content,phone:phone,email:email,area:feedbackareaid,load_time:fb_load_time,browser_resolution:browser_resolution}, function (data) {
            feedbackpost = 1;
            resetfeedback();
        });
    }

}

function getidByText(id){
    var text = $('#'+id).text();
    var result = '';
    $('#'+id).next().children().each(function(){
        if($(this).text() == text){
            result = $(this).attr("value");
        }
    });
    return result;
}

function regemail(mail){
    var reg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
    if(reg.test(mail)){
        return true;
    }else{
        return false;
    }
}
function regphone(phone){
    var reg = /^1\d{10}$/;
    if(reg.test(phone)){
        return true;
    }else{
        return false;
    }
}
function resetfeedback(){
    $('.fk_tp_box').hide();
    $('.fk_click').remove();
    $('#feedback_type_v').text('请选择...');
    $('#feed_back_des').val('请在此输入您的描述');
    $('#feed_back_email').val('');
    $('#feed_back_phone').val('');
    $('.buzhou1').hide();
    $('.buzhou2').hide();
    $('.fk_tips_small_4').show();
}
function cleardes(){
    var descon = $('#feed_back_des').val();
    if(descon == fb_des){
        $('#feed_back_des').val('');
    }
}
function regdes(){
    var descon = $('#feed_back_des').val();
    if(descon.length == 0){
        $('#feed_back_des').val(fb_des);
    }
}
function gocontinue(){
    $('.fk_tips_small_4').hide();
    $('.fk_tips_small_2').show();
}
function clearall(){
    $('.fk_tips_small_4').hide();
    selectareabool = true;
    expanded = !expanded;
}

var expanded = true;
var selectareabool = true;
var feedbacktypeid = '';
var feedbackareaid = '';
var fb_load_time = "0";
var fb_starttime = Date.parse(new Date())/1000;
var fb_des = '请在此输入您的描述';

var feedbackpost = 1;
var surveytitle = "";
var surveycode = "53sn47bz";
$(function(){
    fb_load_time = Date.parse(new Date())/1000 - fb_starttime;
    $('.bar').click(function(){
        if(selectareabool){
            $('.fk_tips_small_2').show();
        }
    });
});
