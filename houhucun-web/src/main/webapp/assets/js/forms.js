// form表单校验
    $(function(){
		$("#registerform").Validform({
			tiptype:2,
			usePlugin:{
				datepicker:{},//日期控件校验;
				passwordstrength:{
					minLen:6,//设置密码长度最小值，默认为0;
					maxLen:18,//设置密码长度最大值，默认为30;
					trigger:function(obj,error){
						//该表单元素的keyup和blur事件会触发该函数的执行;
						//obj:当前表单元素jquery对象;
						//error:所设密码是否符合验证要求，验证不能通过error为true，验证通过则为false;					
						//console.log(error);
						if(error){
							obj.parent().find(".Validform_checktip").show();
							obj.parent().find(".passwordStrength").hide();
						}else{
							obj.parent().find(".passwordStrength").show();	
						}
					}
				}
			}
		});
    });
    // #register_tab 父级id
    // #register_tab .register_tabBar span 控制条
    // #register_tab .register_tabCon 内容区
    // click 事件 点击切换，可以换成mousemove 移动鼠标切换
    // 1	默认第2个tab为当前状态（从0开始）


/*模拟下拉菜单*/
jQuery.Huiselect = function(divselectid,inputselectid) {
	var inputselect = $(inputselectid);
	$(divselectid+" cite").click(function(){
		var ul = $(divselectid+" ul");
		ul.slideToggle();
	});
	$(divselectid+" ul li a").click(function(){
		var txt = $(this).text();
		$(divselectid+" cite").html(txt);
		var value = $(this).attr("selectid");
		inputselect.val(value);
		$(divselectid+" ul").hide();
	});
	$(document).click(function(){$(divselectid+" ul").hide();});
};

/*hover*/
jQuery.Huihover =function(obj) {
	$(obj).hover(function(){$(this).addClass("hover");},function(){$(this).removeClass("hover");});
};